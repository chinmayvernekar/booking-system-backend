package com.parkingbookingsystem.admin;

import com.parkingbookingsystem.ApplicationUsers;
import org.springframework.http.ResponseEntity;

public interface AdminService {

	public ResponseEntity<?> saveAdmin(ApplicationUsers user);
}
