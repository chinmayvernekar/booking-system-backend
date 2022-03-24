package com.parkingbookingsystem.users;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.parkingbookingsystem.ApplicationUserRepository;
import com.parkingbookingsystem.ApplicationUsers;
import com.parkingbookingsystem.role.RoleRepository;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	ApplicationUserRepository applicationUserRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Override
	public ResponseEntity<?> saveUser(ApplicationUsers applicationUsers)
			throws JsonMappingException, JsonProcessingException, JSONException {
		Map<String,String> map = new HashMap<>();
		try{
			applicationUsers.addRole(roleRepository.findByName("USER"));
			applicationUsers.setPassword(passwordEncoder.encode(applicationUsers.getPassword()));
			applicationUserRepository.save(applicationUsers);
			map.put("message","Registration Sucessfull");
		}catch (Exception e){
			System.out.println(e);
		}
		return ResponseEntity.ok(map);
	}

}
