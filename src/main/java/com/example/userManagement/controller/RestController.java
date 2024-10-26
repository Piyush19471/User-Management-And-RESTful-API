package com.example.userManagement.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.userManagement.model.User;
import com.example.userManagement.service.UserService;
@org.springframework.web.bind.annotation.RestController
public class RestController {
	
	@Autowired
	private UserService userService;
	
    @PostMapping("/user")
	public User inputUser(@RequestBody User user) 
    {
		
		return userService.creatUser(user);
		
	}
	
	@GetMapping("/user")
    public List<User> getallUsers(){
		
    	return userService.getUser();
    }
	
	@GetMapping("/user/{id}")
	public ResponseEntity<User> getUserDetails(@PathVariable int id) {
	User user=userService.getUserDetails(id).orElse(null);
	if(user!= null) {
		return ResponseEntity.ok().body(user);
		
	}else {
		return ResponseEntity.notFound().build();		
	}
		
	}
	@PutMapping("/user/{id}")
	
    public ResponseEntity<User> updateUserDetails(@PathVariable int id, @RequestBody User user) 
	{
	User user2=	userService.updateuserdetails(id, user);
	if(user2!=null) {
		return ResponseEntity.ok(user2);
	}else {
		return ResponseEntity.notFound().build();
	}
	
	}
	

   	@DeleteMapping("user/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable int id) {
		if (!userService.existsById(id)) {
			try {
				throw new Exception("User with ID " + id + " not found.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		userService.deleteuser(id);
		return ResponseEntity.noContent().build();
	}



	// @DeleteMapping("user/{id}")
	// public ResponseEntity<Void> deleteUser(@PathVariable  int id)
	
	// {
	//      userService.deleteuser(id);
	// 	return ResponseEntity.noContent().build();
		
		
	// }
	
	
}
