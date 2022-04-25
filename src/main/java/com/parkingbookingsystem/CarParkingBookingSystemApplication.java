package com.parkingbookingsystem;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parkingbookingsystem.locationdetails.ParkingLocationRepository;
import com.parkingbookingsystem.locationdetails.ParkingLocations;
import com.parkingbookingsystem.locationdetails.ParkingLocationsService;
import com.parkingbookingsystem.role.Role;
import com.parkingbookingsystem.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableCaching
public class CarParkingBookingSystemApplication implements CommandLineRunner{

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	ParkingLocationRepository parkingLocationRepository;

	@Autowired
	ParkingLocationsService parkingLocationsService;

	public static void main(String[] args) {
		SpringApplication.run(CarParkingBookingSystemApplication.class, args);
	}

	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

		@Bean
		CommandLineRunner runner(){
			return args -> {
				if(parkingLocationRepository.checkDataExist() == 0) {
					//read and write data to database
					ObjectMapper mapper = new ObjectMapper();
					TypeReference<List<ParkingLocations>> typeReference = new TypeReference<List<ParkingLocations>>() {
					};
					InputStream inputStream = TypeReference.class.getResourceAsStream("/json/parkingf135e53.json");

					try {
						List<ParkingLocations> parking = mapper.readValue(inputStream, typeReference);
						parkingLocationsService.saveDetails(parking);
						System.out.println("Details Saved");
					} catch (IOException e) {
						System.out.println("Unable to save details " + e);
					}
				}else {
					System.out.println("Details Already Exist");
				}

			};
		}


	@Override
	public void run(String... args) throws Exception {
		roleRepository.saveAll(Arrays.asList(
				new Role(1, "USER"),
				new Role(2, "ADMIN")
		));
	}
}