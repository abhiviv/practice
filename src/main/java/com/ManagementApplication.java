package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.management.repository.RegistrationRepository;
import com.management.service.SecurityService;

@SpringBootApplication
@CrossOrigin
@EnableCaching
public class ManagementApplication implements CommandLineRunner{
	
	@Autowired
	private RegistrationRepository registrationRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ManagementApplication.class, args);
	}

	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Registration();
	}

	
	public void Registration() {
		com.management.entity.Registration registration=new com.management.entity.Registration();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode("123");
		registration.setEmail("Admin@gmail.com");
		registration.setName("Abhishek");
		registration.setPhoneno("7016503938");
		registration.setRole("Admin");
		registration.setPassword(hashedPassword);
		registrationRepository.save(registration);
	}
}
