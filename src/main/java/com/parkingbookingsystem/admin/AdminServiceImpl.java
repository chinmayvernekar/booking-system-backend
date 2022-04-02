package com.parkingbookingsystem.admin;

import com.parkingbookingsystem.ApplicationUserRepository;
import com.parkingbookingsystem.ApplicationUsers;
import com.parkingbookingsystem.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	RoleRepository roleRepository;



	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Autowired
	ApplicationUserRepository applicationUserRepository;

	@Override
	public ResponseEntity<?> saveAdmin(ApplicationUsers user) {
		Map<String,String> map = new HashMap<>();
		try{


			user.addRole(roleRepository.findByName("ADMIN"));
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			applicationUserRepository.save(user);
			map.put("message","Registration Sucessfull");
		}catch (Exception e){
			System.out.println(e);
		}
		return ResponseEntity.ok(map);
	}
}
