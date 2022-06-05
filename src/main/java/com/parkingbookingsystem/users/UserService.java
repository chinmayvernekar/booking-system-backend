package com.parkingbookingsystem.users;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.parkingbookingsystem.ApplicationUsers;
import org.json.JSONException;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;


public interface UserService {

	public ResponseEntity<?> saveUser(ApplicationUsers applicationUsers)
			throws JsonMappingException, JsonProcessingException, JSONException;

	public ResponseEntity<?> findUserByEmail(String email, HttpServletRequest request);


	public ResponseEntity<?> findUserByResetToken(String resetToken,ApplicationUsers users);

}
