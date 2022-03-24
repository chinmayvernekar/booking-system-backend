package com.parkingbookingsystem;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUsers,Long> {

	ApplicationUsers findByEmail(String email);
}
