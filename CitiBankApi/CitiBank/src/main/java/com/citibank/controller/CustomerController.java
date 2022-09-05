package com.citibank.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citibank.dto.CustomerDto;
import com.citibank.entity.Customer;
import com.citibank.services.CustomerService;

@RestController
@RequestMapping("/customers")
@CrossOrigin(origins="http://localhost:3000")
public class CustomerController {
	
private CustomerService customerService;
	
	@Autowired
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@PostMapping
	public ResponseEntity<CustomerDto> addCustomer(@Valid @RequestBody CustomerDto customerDto){
		Customer customer = customerService.addCustomer(Customer.from(customerDto));
		return new ResponseEntity<>(CustomerDto.from(customer), HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<CustomerDto>> getAllAccounts(){
		List<Customer> customers = customerService.getAllCustomers();
		List<CustomerDto> customersDto = customers.stream().map(CustomerDto::from).collect(Collectors.toList());
		return new ResponseEntity<>(customersDto, HttpStatus.OK);	
	}
	
	
	@GetMapping(value ="{id}")
	public ResponseEntity<CustomerDto> getAccount(@PathVariable Integer id){
		Customer customer = customerService.getCustomer(id);
		return new ResponseEntity<>(CustomerDto.from(customer), HttpStatus.OK);	
	}
	

}
