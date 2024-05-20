package com.employee.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.employee.entities.Address;
import com.employee.entities.Employee;
import com.employee.entities.EmployeeResponse;
import com.employee.openfeignclient.AddressClient;
import com.employee.repositories.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private WebClient webClient;
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@Autowired
	private LoadBalancerClient loadBalancerClient;
	
	@Autowired
	private AddressClient addressClient;
	
	public EmployeeResponse getEmployeeDetails(int id) {
//		Address address = callToRestTemplate(id);	
//		Address address = callToWebCient(id);
		Address address = addressClient.getAddressDetail(id);
		Employee employee = repository.findById(id).get();
		EmployeeResponse employeeResponse = modelMapper.map(employee, EmployeeResponse.class);
		employeeResponse.setAddress(address);
		return employeeResponse; 
	}

	private Address callToRestTemplate(int id) {
		String uri = getUriFromLoadBalancerDiscoveryClient();
		String contextRoot = getContextRoot();
//		return restTemplate.getForObject(uri+contextRoot+"/address/{id}", Address.class , id);
		return restTemplate.getForObject("http://ADDRESS-SERVICE/address-app/api/address/{id}", Address.class , id);
	}

	private Address callToWebCient(int id) {
		String url = getUriFromLoadBalancerDiscoveryClient();
		String contextRoot = getContextRoot();
//		return webClient.get().uri(url+contextRoot+"/address/"+id).retrieve().bodyToMono(Address.class).block();
		return webClient.get().uri("http://ADDRESS-SERVICE/address-app/api/address/"+id).retrieve().bodyToMono(Address.class).block();
	}
	
	private String getContextRoot() {
		ServiceInstance serviceInstance = loadBalancerClient.choose("ADDRESS-SERVICE");
		String contextPath = serviceInstance.getMetadata().get("configPath");
		return contextPath;
	}
	
	private String getUriFromDiscoveryClient() {
		List<ServiceInstance> addressServiceInstances = discoveryClient.getInstances("ADDRESS-SERVICE");
		ServiceInstance serviceInstance = addressServiceInstances.get(0);
		String uri = serviceInstance.getUri().toString();
		return uri;
	}
	
	private String getUriFromLoadBalancerDiscoveryClient() {
		ServiceInstance serviceInstance = loadBalancerClient.choose("ADDRESS-SERVICE");
		String uri = serviceInstance.getUri().toString();
		System.out.println(uri);
		return uri;
	}
}




