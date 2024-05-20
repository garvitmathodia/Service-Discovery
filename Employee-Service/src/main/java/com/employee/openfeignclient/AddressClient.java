package com.employee.openfeignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.employee.entities.Address;

@FeignClient(name = "ADDRESS-SERVICE" , path = "/address-app/api/")
public interface AddressClient {

	@GetMapping("/address/{id}")
	public Address getAddressDetail(@PathVariable("id") int id) ;
}
