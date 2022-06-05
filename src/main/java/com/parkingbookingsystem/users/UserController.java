package com.parkingbookingsystem.users;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.parkingbookingsystem.ApplicationUsers;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/users/register")
	public ResponseEntity<?> saveUser(@RequestBody ApplicationUsers applicationUsers)
			throws JsonMappingException, JsonProcessingException, JSONException{
		return userService.saveUser(applicationUsers);
	}
}

