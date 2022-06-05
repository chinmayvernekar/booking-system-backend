package com.parkingbookingsystem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUsers,Long> {

	ApplicationUsers findByEmail(String email);

	@Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END from ApplicationUsers u " +
			"where u.email = ?1")
	boolean findIfUserPresent(String email);

	@Query("select u.id from ApplicationUsers u where u.email = ?1")
	UUID setUserIdToToken(String email);

	Optional<ApplicationUsers> findUserByEmail(String email);

	Optional<ApplicationUsers> findByResetToken(String resetToken);
}
