package com.address.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.address.entities.Address;
import com.address.service.AddressService;

@RestController
public class AddressController {
	
	@Autowired
	private AddressService service;
	
	@GetMapping("/address/{id}")
	public Address getAddressDetail(@PathVariable("id") int id) {
		System.out.println("Address service");
		Address address = service.getAddressById(id);
		return address;
	}
}
