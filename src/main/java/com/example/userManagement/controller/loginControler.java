package com.example.userManagement.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import com.example.userManagement.config.OauthenticationSuccessHandler;
import com.example.userManagement.model.User;
import com.example.userManagement.service.UserService;
@Controller
public class loginControler {

	@Autowired
	private UserService userService;
	@Autowired
	private OauthenticationSuccessHandler oauthenticationSuccessHandler;
	
	 @GetMapping("/")
	 public String openLogPage(Model model) {
		 model.addAttribute("user", new User());
		 return "index";
	 }

	@PostMapping("/login")	
	 public String SubmitlogForm(@ModelAttribute ("user") User user , Model model) {
		 
		User validUser= userService.loginUser(user.getEmail(), user.getPassword());
		 if(validUser!=null) {
			 model.addAttribute("name", validUser.getName());
			 model.addAttribute("email", validUser.getEmail());
			 return "profile";
		 }	 
		 
		 else {
			 
			 model.addAttribute("errorMsg", "Email id or password incorrect.");
			 return "index";
		 }
		  
	 }

		@RequestMapping(value = "/profile")
		public String userProfile(Model model, Authentication authentication) {
			// Check if the authentication object is null (i.e., user is not authenticated)
			if (authentication == null || authentication.getPrincipal() == null) {
				// Handle the case where the user is not authenticated (redirect or show an
				// error)
				return "redirect:/"; // Or return an error page
			}

			// Cast authentication.getPrincipal() to DefaultOAuth2User
			DefaultOAuth2User oauthUser = (DefaultOAuth2User) authentication.getPrincipal();

			// Call sendData method to get the user's name
			String name = oauthenticationSuccessHandler.sendData(oauthUser);

			// Add the name to the model so it can be accessed in the profile view 
			model.addAttribute("name", name);

			// Return the view name (profile page)
			return "profile";
		}

}
