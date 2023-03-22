package ics499.GalaxyGenerator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig { // this class is needed for authentication

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests((requests) -> requests
            // the line below will make any access to /planets need to be logged in first
            // .requestMatchers("/planets").authenticated()
            .anyRequest().permitAll()) // anything is permit (does not require log in)
        .formLogin((form) -> form
            .defaultSuccessUrl("/home") // return to home after sucessful login
            .usernameParameter("email") // email from User model is used as login parameter
            .permitAll())
        .logout((logout) -> logout.permitAll())
        .csrf().disable()
        .cors().disable();
    return http.build();
  }

  @Bean // Boilerplate code
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean // Boilerplate code
  public UserDetailsService userDetailsService() {
    return new CustomUserDetailsService();
  }

  @Bean // Boilerplate code
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService()); // set userDetailsService from function above
    authProvider.setPasswordEncoder(passwordEncoder()); // Set password encoded from function above

    return authProvider;
  }
}
