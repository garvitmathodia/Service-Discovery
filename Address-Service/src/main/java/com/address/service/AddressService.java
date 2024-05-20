package com.address.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.address.entities.Address;
import com.address.repositories.AddressRepository;

@Service
public class AddressService {
	
	@Autowired
	private AddressRepository repository;
	
	public Address getAddressById(int id) {
		Address address = repository.findAddressByEmployeeId(id);
		return address;
	}
}
