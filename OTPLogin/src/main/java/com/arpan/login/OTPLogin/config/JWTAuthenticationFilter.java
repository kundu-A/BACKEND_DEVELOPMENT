package com.arpan.login.OTPLogin.config;

import com.arpan.login.OTPLogin.models.Tokens;
import com.arpan.login.OTPLogin.repository.TokenRepository;
import com.arpan.login.OTPLogin.service.JWTService;
import com.arpan.login.OTPLogin.service.MyUserServiceDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Date;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    JWTService jwtService;

    @Autowired
    MyUserServiceDetails myUserServiceDetails;

    @Autowired
    TokenRepository tokenRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String authHeader = request.getHeader("Authorization");
            String phoneNumber = null;
            String token = null;
            if (authHeader != null) {
                if (authHeader.startsWith("Bearer ")) {
                    token = authHeader.substring(7);
                    phoneNumber = jwtService.extractPhoneNumber(token);
                }
                Tokens tokens = tokenRepository.findTokensByAccessToken(token);
                if (System.currentTimeMillis()<tokens.getExpirationDuration()) {
                    if (phoneNumber != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                        UserDetails userDetails = myUserServiceDetails.loadUserByPhoneNumber(phoneNumber);
                        if (jwtService.validateAccessToken(token, userDetails)) {
                            UsernamePasswordAuthenticationToken authToken =
                                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                            SecurityContextHolder.getContext().setAuthentication(authToken);
                        }
                    }
                }
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
