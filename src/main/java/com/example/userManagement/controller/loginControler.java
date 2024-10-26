package com.example.userManagement.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.userManagement.model.User;
import com.example.userManagement.service.UserService;
@Controller
public class loginControler {

	@Autowired
	private UserService userService;
	
	 @GetMapping("/")
	 public String openLogPage(Model model) {
		 model.addAttribute("user", new User());
		 return "index";
	 }
	 @SuppressWarnings("null")
	@PostMapping("/logForm")
	 public String SubmitlogForm(@ModelAttribute ("user") User user , Model model) {
		 
		User validUser= userService.loginUser(user.getEmail(), user.getPassword());
		 if(validUser!=null) {
			 model.addAttribute("name", validUser.getName());
			 model.addAttribute("email", validUser.getEmail());
			 return "profile";
		 }	 
		 
		 else {
			 
			 model.addAttribute("errorMsg", "Email id or Password incorrect 😒🤦‍♂️");
			 return "index";
		 }
		  
	 }
	 
}
