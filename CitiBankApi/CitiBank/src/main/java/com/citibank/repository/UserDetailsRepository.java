package com.citibank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.citibank.entity.Users;

@Repository
public interface UserDetailsRepository extends JpaRepository<Users, Long> {

	Users findByUserName(String userName);
}
