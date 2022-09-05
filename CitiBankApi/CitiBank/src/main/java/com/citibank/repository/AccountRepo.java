package com.citibank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.citibank.entity.Account;

@Repository
public interface AccountRepo extends JpaRepository<Account, String> {
	
	Account findByAccountNo(String accountNo);
	List<Account> findByCustomerId(int customer_id);
}
