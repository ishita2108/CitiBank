package com.citibank.dto;

import java.math.BigDecimal;


import com.citibank.entity.Account;

import lombok.Data;

@Data
public class AccountDto {

	private int accountNo;
	private BigDecimal balance;
	private CustomerDto customerDto;
	
	
	public static AccountDto from(Account account) {
		AccountDto accountDto = new AccountDto();
		accountDto.setAccountNo(account.getAccountNo());
		accountDto.setBalance(account.getBalance());
		accountDto.setCustomerDto(CustomerDto.from(account.getCustomer()));

		return accountDto;
		
	}
	
}
