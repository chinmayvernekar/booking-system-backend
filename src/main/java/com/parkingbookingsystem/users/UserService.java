package com.parkingbookingsystem.users;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.parkingbookingsystem.ApplicationUsers;
import org.json.JSONException;
import org.springframework.http.ResponseEntity;


public interface UserService {

	public ResponseEntity<?> saveUser(ApplicationUsers applicationUsers)
			throws JsonMappingException, JsonProcessingException, JSONException;
}
