package com.citibank.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.citibank.dto.AccountDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
@NoArgsConstructor
@Entity
@Data
@Table(name="AccountsCitiBank")
public class Account {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int accountNo;
	private BigDecimal balance;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;
	
	public static Account from(AccountDto accountDto) {
		Account account = new Account();
		account.setBalance(accountDto.getBalance());
		return account;
	}

}
