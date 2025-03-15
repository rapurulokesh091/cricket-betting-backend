package com.cricketbetting.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cricketbetting.dao.UserDAO;

@RestController
@RequestMapping("/api/users")
public class UserController 
{
	private UserDAO userDAO;
	
	public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
	
	@GetMapping("/all")
    public ResponseEntity<List<Map<String, Object>>> getAllUsers() 
	{
		List<Map<String, Object>> users = userDAO.findAll();
        return ResponseEntity.ok(users);
    }
	
	@PostMapping("/register")
	public ResponseEntity<Map<String, Object>> registration(@RequestBody Map<String, Object> map) 
	{
		Map<String, Object> response = new HashMap<>();
		try
		{
			String username = (String) map.get("username");
			String emailId = (String) map.get("emailId");
			int mobileNumber = (int) map.get("mobileNumber");
			String userpassword = (String) map.get("userpassword");
			userDAO.insertUserDetails(username,emailId,mobileNumber,userpassword);
			response.put("message", "Users registered successfully");
            return ResponseEntity.ok(response);
		}
		catch (Exception e) 
		{
			response.put("message", "Internal server error");
            response.put("error", e.getMessage());
            return ResponseEntity.status(500).body(response);
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, Object> map) 
	{
		Map<String, Object> response = new HashMap<>();
		try
		{
			String mobileNumber = (String) map.get("mobileNumber");
			String userpassword = (String) map.get("userpassword");
			if (mobileNumber ==null || userpassword == null) 
			{
				response.put("message", "Username and password are required");
                return ResponseEntity.badRequest().body(response);
	        }
			List<Map<String, Object>> users =userDAO.getLogin(mobileNumber,userpassword);
			if (users.isEmpty()) 
			{
                response.put("message", "Invalid username or password");
                return ResponseEntity.status(401).body(response);
            }
			else
			{
				response.put("message", "Login successful");
	            response.put("user", users.get(0));
	            return ResponseEntity.ok(response);
			}
		}
		catch (Exception e) 
		{
			response.put("message", "Internal server error");
            response.put("error", e.getMessage());
            return ResponseEntity.status(500).body(response);
		}
	}
}
