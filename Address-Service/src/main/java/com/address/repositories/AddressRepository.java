package com.address.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.address.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

	@Query(nativeQuery = true, value = "SELECT a.id , a.lane1 , a.lane2 , a.state , a.zip  FROM microservice.address a join microservice.employee e on e.id = a.id where a.employee_id = :employeeId")
	Address findAddressByEmployeeId(@Param("employeeId") int employeeId);
}
