package com.parkingbookingsystem.admin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.parkingbookingsystem.ApplicationUsers;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

	@Autowired
	AdminService adminService;

	@PostMapping("/admin/register")
	public ResponseEntity<?> saveAdmin(@RequestBody ApplicationUsers user)
			throws JsonMappingException, JsonProcessingException, JSONException {
		return adminService.saveAdmin(user);
	}
}
