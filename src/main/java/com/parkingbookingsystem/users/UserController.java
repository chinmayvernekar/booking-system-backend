package com.parkingbookingsystem.users;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.parkingbookingsystem.ApplicationUsers;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/users/register")
	public ResponseEntity<?> saveUser(@RequestBody ApplicationUsers applicationUsers)
			throws JsonMappingException, JsonProcessingException, JSONException{
		return userService.saveUser(applicationUsers);
	}
}

