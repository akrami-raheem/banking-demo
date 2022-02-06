package com.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bank.model.UserDetailsDto;
import com.bank.service.RegistrationSevice;

@RestController
@CrossOrigin
public class RegistrationController {

	@Autowired
	private RegistrationSevice registrationSevice;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody UserDetailsDto user) throws Exception {
		return ResponseEntity.ok(registrationSevice.save(user));
	}
}
