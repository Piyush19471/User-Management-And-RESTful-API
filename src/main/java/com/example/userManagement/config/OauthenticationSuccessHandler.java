package com.example.userManagement.config;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;

import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.userManagement.model.User;
import com.example.userManagement.repositry.UserRepo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class OauthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private UserRepo userRepo;
    Logger logger = LoggerFactory.getLogger(OauthenticationSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        logger.info("OAuth Authentication Success Handler triggered");

        // Ensure that authentication principal is of type DefaultOAuth2User
        DefaultOAuth2User user = (DefaultOAuth2User) authentication.getPrincipal();

        // Extract user attributes
        String email = (String) user.getAttribute("email");
        String name = (String) user.getAttribute("name");
        String picture = (String) user.getAttribute("picture");

        // Check for null values and handle them
        if (email == null || name == null || picture == null) {
            logger.error("Missing OAuth attributes (email, name, picture)");
            return; // Handle appropriately, e.g., redirect to error page
        }

        // Create and save user if it doesn't exist
        if (!userRepo.existsByEmail(email)) {
            User newUser = new User();
            newUser.setName(name);
            newUser.setEmail(email);
            userRepo.save(newUser);
            logger.info("Saved new user with email: " + email);
        }

        // Redirect to profile page
        new DefaultRedirectStrategy().sendRedirect(request, response, "/profile");
    }

    // Method to extract user name (can be used in controller as well)
    public String sendData(DefaultOAuth2User user) {
        return (String) user.getAttribute("name");
    }
}
