package com.example.userManagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.userManagement.model.User;
import com.example.userManagement.repositry.UserRepo;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public boolean registeredUser(User user) {
        try {
            // Check if the user already exists by email
            if (userRepo.existsByEmail(user.getEmail())) {
                return false;  // User already exists
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;  // Return false if any exception occurs
        }

        // Save the user only if they don't already exist
        userRepo.save(user);
        return true;  // Return true indicating the user was successfully saved
    }


	@Override
	public User loginUser(String email, String password)
	{	
	User validuser=userRepo.findByEmail(email);
	if(validuser != null && validuser.getPassword().equals(password)) {
		
		return validuser;
	}
	
	
		return null;
		
		
	}


	@Override
	public User creatUser(User user) {
		
		return userRepo.save(user);
	}


	@Override
	public List<User> getUser() {
		
		return userRepo.findAll();
	}


	@Override
	public Optional<User> getUserDetails(int id) {
		
		return userRepo.findById(id);
	}


	@Override
	public User updateuserdetails(int id, User user) 
	{
		User userData = userRepo.findById(id).orElse(null);
		if(userData!=null) {
		return	userRepo.save(user);
		}else {
			throw new RuntimeException("User not found with id: "+id);
		}
		
	}


	@Override
	public void deleteuser(int id) {
		userRepo.deleteById(id);
		
	}


	@Override
	public boolean existsById(int id) {
		
		throw new UnsupportedOperationException("Unimplemented method 'existsById'");
	}
     
}
