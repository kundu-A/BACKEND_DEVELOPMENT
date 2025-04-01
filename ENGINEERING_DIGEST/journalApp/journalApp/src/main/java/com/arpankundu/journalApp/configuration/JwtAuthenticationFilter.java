package com.arpankundu.journalApp.configuration;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.arpankundu.journalApp.services.JwtService;
import com.arpankundu.journalApp.services.MyUserServiceDetails;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
    JwtService jwtService;

    @Autowired
    MyUserServiceDetails userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //String authHeader = request.getHeader("Authorization");
        String token = extractTokenFromCookies(request);
        System.out.println(token);
        String username = null;
        if (token != null) {
            username = jwtService.extractUsername(token);
        }
        System.out.println(username);

        /*if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
        }*/

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (jwtService.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }


    //Extract Access Token from the Cookies
    public String extractTokenFromCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (request.getCookies() == null) {
            System.out.println("No cookies found in the request!");
            return null;
        }

        String accessToken ="";
        String refreshToken ="";
        System.out.println("Received Cookies:");

        for (Cookie cookie : cookies) {
            System.out.println("Cookie Name: " + cookie.getName() + ", Value: " + cookie.getValue());

            if ("Access-Token".equals(cookie.getName())) { // Use correct name
                accessToken = cookie.getValue();
            } else if ("Refresh-Token".equals(cookie.getName())) { // Use correct name
                refreshToken = cookie.getValue();
            }
        }

        System.out.println("Extracted Access Token: " + accessToken);
        System.out.println("Extracted Refresh Token: " + refreshToken);

        return accessToken; // Return only access token for authentication
    }

    //Extract Refresh Token from the Cookies
    public String extractRefreshTokenFromCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (request.getCookies() == null) {
            System.out.println("No cookies found in the request!");
            return null;
        }

        String accessToken ="";
        String refreshToken ="";
        System.out.println("Received Cookies:");

        for (Cookie cookie : cookies) {
            System.out.println("Cookie Name: " + cookie.getName() + ", Value: " + cookie.getValue());

            if ("Access-Token".equals(cookie.getName())) { // Use correct name
                accessToken = cookie.getValue();
            } else if ("Refresh-Token".equals(cookie.getName())) { // Use correct name
                refreshToken = cookie.getValue();
            }
        }

        System.out.println("Extracted Access Token: " + accessToken);
        System.out.println("Extracted Refresh Token: " + refreshToken);

        return refreshToken; // Return only access token for authentication
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        return path.startsWith("/user/login") || path.startsWith("/user/register")
                || path.startsWith("/user/login-with-otp") || path.startsWith("/user/verify-otp-login")
                || path.startsWith("/user/forgot-password") || path.startsWith("/user//forgotPassword-otp-verification")
                || path.startsWith("/user//forgotPassword-set-password") || path.startsWith("/user//refresh-button");
    }
}
