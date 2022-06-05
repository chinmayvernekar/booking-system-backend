package com.parkingbookingsystem.users;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.parkingbookingsystem.ApplicationUserRepository;
import com.parkingbookingsystem.ApplicationUsers;
import com.parkingbookingsystem.EmailService;
import com.parkingbookingsystem.role.Role;
import com.parkingbookingsystem.role.RoleRepository;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	EmailService emailService;

	@Autowired
	ApplicationUserRepository applicationUserRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;



	@Override
	public ResponseEntity<?> saveUser(ApplicationUsers applicationUsers)
			throws JsonMappingException, JsonProcessingException, JSONException {
		Map<Object,String> map = new HashMap<>();
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
				HttpStatus status = HttpStatus.CONFLICT;
				map.put(status,"User " + applicationUsers.getEmail() + " is already registered.");
			}
		}catch (Exception e){
			System.out.println(e);
		}
		return ResponseEntity.ok(map);
	}

	@Override
	public ResponseEntity<?> findUserByEmail(String email,HttpServletRequest request) {

		Map<Object,String> map = new HashMap<>();
		try {

			Optional<ApplicationUsers> emailId = applicationUserRepository.findUserByEmail(email);

			if(!emailId.isPresent()){

				map.put("message","USER DOES NOT EXIST IN OUR RECORDS");
			}
			else{
				ApplicationUsers user = emailId.get();
				user.setResetToken(UUID.randomUUID().toString());
				// Saving The Token To DB.
				applicationUserRepository.save(user);

				String appUrl = request.getScheme() + "://" + request.getServerName();

				emailService.sendSimpleEmail(
						user.getEmail(),
						"To reset your password, click the link below:\n" + appUrl + "user/reset?token=" + user.getResetToken(),
						"Password Reset Request");
				map.put("message","Email Sent With Password Reset Link");
			}

		}catch (Exception e){
			e.printStackTrace();
		}
		return ResponseEntity.ok(map);
	}

	@Override
	public  ResponseEntity<?> findUserByResetToken(String resetToken,ApplicationUsers users) {
		Map<Object,String> map = new HashMap<>();
		try {
			Optional<ApplicationUsers> token = applicationUserRepository.findByResetToken(resetToken);

			if(token.isPresent()){
				ApplicationUsers user = token.get();

				user.setPassword(passwordEncoder.encode(users.getPassword()));


				user.setResetToken(null);

				applicationUserRepository.save(user);

				map.put("message","You have sucessfully reset password");
			}else {
				map.put("message","Please click on fogort password to get reset link on registered email");
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return ResponseEntity.ok(map);
	}



}
