package com.parkingbookingsystem.users;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.parkingbookingsystem.ApplicationUserRepository;
import com.parkingbookingsystem.ApplicationUsers;
import com.parkingbookingsystem.role.Role;
import com.parkingbookingsystem.role.RoleRepository;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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
		boolean checkUserExists = false;
		try{

			if(applicationUserRepository.findIfUserPresent(applicationUsers.getEmail()) == checkUserExists)
			{
				Role role = roleRepository.findByName("USER");
				applicationUsers.addRole(role);
				applicationUsers.setPassword(passwordEncoder.encode(applicationUsers.getPassword()));
				applicationUserRepository.save(applicationUsers);
				map.put("message", "Registration Sucessfull");
			}
			else {
				map.put("messgae","User " + applicationUsers.getEmail() + "is already registered.");
			}
		}catch (Exception e){
			System.out.println(e);
		}
		return ResponseEntity.ok(map);
	}

}
