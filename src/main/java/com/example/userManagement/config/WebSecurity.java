package com.example.userManagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableWebSecurity
public class WebSecurity {
    @Autowired
    private OauthenticationSuccessHandler handler;    
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/user/**").authenticated() // Secures only endpoints starting with /user/
                        .anyRequest().permitAll() // Allows unrestricted access to other endpoints

                )
                .httpBasic(withDefaults()) // Enables HTTP Basic Authentication for secured endpoints
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // Disables session creation (stateless)
                );
        http
                .logout(logout -> logout
                        .logoutUrl("/logout") // Sets the URL to trigger logout
                        .logoutSuccessUrl("/?logout=true") // Redirects to the homepage after logout
                        .invalidateHttpSession(true) // Invalidates the session
                        .deleteCookies("JSESSIONID") // Deletes the session cookie
                );

                //oauth configuration
              
                http.oauth2Login(oauth->{
                        oauth.loginPage("/");
                        oauth.successHandler(handler);
                        
                });



        return http.build();
    }
}
