package com.management.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.management.entity.Registration;
import com.management.repository.RegistrationRepository;

@Service
@Transactional
public class RegistrationService {

	@Autowired
	private RegistrationRepository registrationRepository;
	
	@Autowired
	private SecurityService securityService;
	
	public Registration getuserdetails() {
		Object authentication = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String loggedInUserEmailId = (String)authentication;
		Registration  loginusers=registrationRepository.findByEmail(loggedInUserEmailId);
		return loginusers;	
	}
	
	
	
	public Registration registration(Registration registration) {
		String EmailId=registration.getEmail();
		Registration checkDublicate=registrationRepository.findByEmail(EmailId);
		if(checkDublicate != null) {
			throw new com.management.exception.CustomeException("Check Emaild",
			          "Emaiid Allready used",
			          "Enter different EmailId",
			          "then click Submit",
			          "if and issued contact us");
		}else {
			registration.setPassword(securityService.encodePassword(registration.getPassword()));
			return registrationRepository.save(registration);
		}
		
	}
	
	public List<Registration> filter(String Role) {
		return registrationRepository.findByRole(Role);
	}
	
}
