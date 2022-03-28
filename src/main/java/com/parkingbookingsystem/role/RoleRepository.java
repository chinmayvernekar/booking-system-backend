package com.parkingbookingsystem.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface RoleRepository extends JpaRepository<Role,Integer> {


	Role findByName(String name);
}
