package com.example.userManagement.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.userManagement.model.User;
import com.example.userManagement.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class Register_and_Logout_controller {

    @Autowired
    private UserService userService;

    @GetMapping("/regPage")
    public String openRegpage(org.springframework.ui.Model model) {
        model.addAttribute("user", new User());  // Add a new User object to the model
        return "register";  // Return the registration page
    }

    @PostMapping("/regForm")
    public String submitRegForm(@ModelAttribute("user") User users, org.springframework.ui.Model model) {
        // Check if the passwords match first
        if (!users.getPassword().equals(users.getCnfPassword())) {
            model.addAttribute("errorMsg", "Password doesn't match with Confirm Password 😜");
            return "register";  // Return to registration page without saving
        }

        // Try to register the user
        boolean status = false;
        try {
            // Register the user if validation passes
            status = userService.registeredUser(users);
        } catch (Exception e) {
            // Handle any exceptions during registration
            model.addAttribute("errorMsg", "An error occurred during registration.");
            return "register";  // Return to registration page
        }

        // If registration is successful
        if (status) {
            return "sucess";  // Redirect to success page
        } else {
            // If registration fails, display an appropriate error
            model.addAttribute("errorMsg", "User not registered. Email might already exist try with another email 😇");
            return "error";  // Return to registration page
        }
    }


 @GetMapping("/logout")
 public String logout(HttpServletRequest request) {
   HttpSession session= request.getSession(false);
   if(session!=null) {
	   session.invalidate();
   }
 	return "redirect:/";
 	
 	
 }

 @GetMapping("/forgotPage")
 public String forgotPass() {
	 
	 return "forgotPassword";
 }
 
 
}
