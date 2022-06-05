package com.parkingbookingsystem.users;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.parkingbookingsystem.ApplicationUsers;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/register")
	public ResponseEntity<?> saveUser(@RequestBody ApplicationUsers applicationUsers)
			throws JsonMappingException, JsonProcessingException, JSONException{
		return userService.saveUser(applicationUsers);
	}

	@PostMapping("/email-reset-password-link")
	public ResponseEntity<?> fogortPassword(@RequestParam(name="email",required = true) String email, HttpServletRequest request)
			throws JsonMappingException, JsonProcessingException, JSONException{
		return userService.findUserByEmail(email,request);
	}

	@PostMapping("/reset")
	public ResponseEntity<?> resetPassword(@RequestParam(name="token",required = true) String resetToken,@RequestBody ApplicationUsers users)
			throws JsonMappingException, JsonProcessingException, JSONException{
		return userService.findUserByResetToken(resetToken,users);
	}
}

