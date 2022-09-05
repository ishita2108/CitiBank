package com.citibank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.citibank.entity.Customer;


@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer>{

}
