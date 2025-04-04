package com.arpankundu.journalApp.services;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import com.arpankundu.journalApp.configuration.JwtAuthenticationFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.arpankundu.journalApp.exceptionHandler.BadCredentialsException;
import com.arpankundu.journalApp.exceptionHandler.EmailNotVerifiedException;
import com.arpankundu.journalApp.exceptionHandler.RegistrationRelatedException;
import com.arpankundu.journalApp.exceptionHandler.UserAlreadyExistsException;
import com.arpankundu.journalApp.exceptionHandler.UserNotFoundException;
import com.arpankundu.journalApp.models.ChangePassword;
import com.arpankundu.journalApp.models.ForgotPassword;
import com.arpankundu.journalApp.models.MailOTP;
import com.arpankundu.journalApp.models.Role;
import com.arpankundu.journalApp.models.Users;
import com.arpankundu.journalApp.repository.UserRepo;

@Service
public class UserService {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserRepo userRepo;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtService jwtService;

	@Autowired
	UtilityService utilityService;

	@Autowired
	MailOTPService mailOTPService;

	@Autowired
	EmailService emailService;

	@Autowired
	JwtAuthenticationFilter jwtAuthenticationFilter;

	public Users register(Users user) {
		String email = user.getEmail();
		if (!mailOTPService.verifiedEmails.contains(email))
			throw new EmailNotVerifiedException("Email verification required.");

		if (userRepo.findUsersByEmail(email) != null)
			throw new UserAlreadyExistsException("User with this email already exists.");

		user.setUsername(utilityService.extractUsernameFromEmail(user));
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRole(Role.ROLE_USER);
		try {
			Users users = userRepo.save(user);
			if (users != null) {
				emailService.welcomeEmail(email);
				return users;
			}
			throw new RegistrationRelatedException("User registration failed");
		} catch (Exception e) {
			throw new RuntimeException("User registration failed", e);
		}
	}


	public String verify(Users user, HttpServletResponse response) {
		clearCookies(response);
		String username = utilityService.extractUsernameFromEmail(user);
		try {
			Authentication authentication =
					authenticationManager.authenticate(
							new UsernamePasswordAuthenticationToken(
									username,
									user.getPassword()
							)
					);
			if (authentication.isAuthenticated()) {
				String token = jwtService.generateToken(username);
				String refreshToken = jwtService.generateRefreshToken(username);
				setCookie(response, "Access-Token", token, 60 * 60); // 1 hour
				setCookie(response, "Refresh-Token", refreshToken, 7 * 24 * 60 * 60); // 7 days
				System.out.println("Refresh token: " + refreshToken);
				System.out.println("Access Token: " + token);
				return "Login Successful!!";
			}
		} catch (Exception e) {
			throw new BadCredentialsException("Invalid username or password.");
		}
		throw new BadCredentialsException("Invalid username or password.");
	}

	public void setCookie(HttpServletResponse response, String name, String value, int maxAge) {
		Cookie cookie = new Cookie(name, value);
		cookie.setHttpOnly(true); // Prevent JavaScript access
		cookie.setSecure(true); // Send only over HTTPS
		cookie.setPath("/"); // Available throughout the app
		cookie.setMaxAge(maxAge); // Expiry time in seconds
		cookie.setAttribute("SameSite", "Strict"); // CSRF protection
		response.addCookie(cookie);
	}

	public void clearCookies(HttpServletResponse response) {
		Cookie clearAccessToken = new Cookie("Access-Token", "");
		clearAccessToken.setMaxAge(0); // Expire immediately
		clearAccessToken.setHttpOnly(true);
		clearAccessToken.setPath("/");

		Cookie clearRefreshToken = new Cookie("Refresh-Token", "");
		clearRefreshToken.setMaxAge(0);
		clearRefreshToken.setHttpOnly(true);
		clearRefreshToken.setPath("/");

		response.addCookie(clearAccessToken);
		response.addCookie(clearRefreshToken);
	}

	public Users getLoggedInUser(String username) {
		Users user = userRepo.findUsersByUsername(username);
		if (user == null)
			throw new UserNotFoundException("User not found!!");
		return user;
	}


	public String OTPVerifiedLogin(MailOTP mailOTP) {
		try {
			String username = utilityService.extractUsernameFromEmail(mailOTP.getEmail());
			Users user = userRepo.findUsersByUsername(username);
			if (user != null)
				return jwtService.generateToken(username);
			return "Provide the valid email!!";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "Some issues aries!!";
		}
	}

	public boolean changePassword(ChangePassword request,HttpServletRequest req , HttpServletResponse res) {
		String newPassword = passwordEncoder.encode(request.getNewPassword());
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Users user = userRepo.findUsersByUsername(username);
		if (!passwordEncoder.matches(request.getOlderPassword(), user.getPassword()))
			return false;
		if (!request.getNewPassword().equals(request.getConfirmPassword()))
			return false;
		try {
			user.setPassword(newPassword);
			Users updatedUser= userRepo.save(user);
				if(updatedUser!=null){
					String refreshToken=jwtAuthenticationFilter.extractRefreshTokenFromCookies(req);
					String accessToken=jwtAuthenticationFilter.extractTokenFromCookies(req);
					refreshToken=null;
					accessToken=null;
					setCookie(res, "Access-Token", accessToken, 0);
					setCookie(res, "Refresh-Token", refreshToken, 0);
					return true;
				}
				return false;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public boolean setPassword(ChangePassword request, String email) {
		if (!request.getNewPassword().equals(request.getConfirmPassword()))
			return false;
		if (mailOTPService.verifiedEmails.contains(email)) {
			Users user = userRepo.findUsersByEmail(email);
			user.setPassword(passwordEncoder.encode(request.getNewPassword()));
			userRepo.save(user);
			return true;
		}
		return false;
	}

	public boolean resetPassword(ForgotPassword request) {
		if (!request.getPassword().equals(request.getConfirmPassword()))
			return false;
		if (mailOTPService.verifiedEmails.contains(request.getEmail())) {
			Users user = userRepo.findUsersByEmail(request.getEmail());
			user.setPassword(passwordEncoder.encode(request.getPassword()));
			userRepo.save(user);
			return true;
		}
		return false;
	}

	public String refreshButton(HttpServletRequest request,HttpServletResponse response) {
		try {
			String refreshToken = jwtAuthenticationFilter.extractRefreshTokenFromCookies(request);
			if (!jwtService.isRefreshTokenExpired(refreshToken)) {
				String username = jwtService.extractUsername(refreshToken);
				String newToken=jwtService.generateToken(username);
				setCookie(response, "Access-Token", newToken, 60 * 60);
				return "Ready to go!!";
			}
			return "Login Again!!";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	public Users oauthRegister(OAuth2User users){
		Users user = new Users();
		user.setName(users.getAttribute("given_name"));
		user.setEmail(users.getAttribute("email"));
		user.setUsername(utilityService.extractUsernameFromEmail(user.getEmail()));
		user.setPassword(passwordEncoder.encode(UUID.randomUUID().toString()));
		user.setRole(Role.ROLE_USER);
		return userRepo.save(user);
	}
}