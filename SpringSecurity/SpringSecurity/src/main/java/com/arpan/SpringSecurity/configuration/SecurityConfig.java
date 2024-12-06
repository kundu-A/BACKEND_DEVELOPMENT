package com.arpan.SpringSecurity.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.arpan.SpringSecurity.services.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	
	@Autowired
	private MyUserDetailsService myUserDetailsService; 

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		//This is called builder approach.
		return http
		.csrf(customizer->customizer.disable())
		.authorizeHttpRequests(request->request.anyRequest().authenticated())
		.httpBasic(Customizer.withDefaults())
		//.formLogin(Customizer.withDefaults())
		.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.build();
		/*
		 * Customizer<CsrfConfigurer<HttpSecuity>> custCsrf=new Customizer<CsrfConfigurer<HttpSecuity>>(){
		 * @Override
		 * public void customize(CsrfConfigurer<HttpSecurity> customizer){
		 * 		customizer.disbale();
		 * }
		 * };
		 * http.csrf(custCsrf);
		 */
	}
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
		provider.setUserDetailsService(myUserDetailsService);
		return provider;
	}
	
	/*
	 * We are going to use this because here is also we are using hardcode values for username and password.
	 * But in normal case we are going to do this.
	 * 
	 * @Bean 
	 * public UserDetailsService userDetailsService() {
	 * 
	 * UserDetails user1= User .withDefaultPasswordEncoder() .username("Goutam")
	 * .password("G@123") .roles("USER") .build(); UserDetails user2= User
	 * .withDefaultPasswordEncoder() .username("Manojit") .password("M@123")
	 * .roles("ADMIN") .build();
	 * 
	 * return new InMemoryUserDetailsManager(user1 ,user2); }
	 */
}
