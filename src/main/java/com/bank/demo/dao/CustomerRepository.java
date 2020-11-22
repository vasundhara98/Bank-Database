package com.bank.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.demo.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
