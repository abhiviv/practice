package com.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.management.entity.Registration;
import com.management.service.RegistrationService;

@RestController
@RequestMapping(path = "/web")
public class RegistrationController {

	@Autowired
	private RegistrationService registrationService;
	
	@GetMapping(path = "/getLoginUser")
	public Registration getuserdetails() {
		return registrationService.getuserdetails();	
	}
	
	@PostMapping(path = "/registration")
	public Registration registration(@RequestBody Registration registration) {
		return registrationService.registration(registration);
	}
	
	@PostMapping(path = "/addAdminAndStudent")
	@PreAuthorize("hasAuthority('Admin')")
	public Registration registration1(@RequestBody Registration registration) {
		return registrationService.registration(registration);
	}
	
	@GetMapping(path = "/getUsersByRoles/{role}")
	public List<Registration> filterData(@PathVariable("role")String Role) {
		return registrationService.filter(Role);
	}
	
	
	
}
