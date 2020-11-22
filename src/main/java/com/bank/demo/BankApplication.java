package com.bank.demo;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import com.bank.demo.dao.AccountRepository;
import com.bank.demo.dao.CustomerRepository;
import com.bank.demo.entities.Account;
import com.bank.demo.entities.CurrentAccount;
import com.bank.demo.entities.Customer;
import com.bank.demo.entities.SavingsAccount;
import com.bank.demo.service.IBankService;

@SpringBootApplication 
public class BankApplication implements CommandLineRunner  {
	
	@Autowired 
	private CustomerRepository customerRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired 
	private IBankService bankService;
	
	
	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(BankApplication.class, args);

	}

	@Override
	public void run(String... arg0) throws Exception {
		Customer cust1 = customerRepository.save(new Customer("Bob", "bob.b@gmail.com"));
		Customer cust2 = customerRepository.save(new Customer("Alice", "alice.ae@gmail.com"));
		Customer cust3 = customerRepository.save(new Customer("Joe", "joe.jay@gmail.com"));
		
		int acc1 = bankService.addAccount("Bob", "bob.b@gmail.com", 1000, "CURRENT", 10000);
		int acc2 = bankService.addAccount("Alice", "alice.ae@gmail.com", 0, "SAVINGS", 5000);
		int acc3 = bankService.addAccount("Joe", "joe.jay@gmail.com", 0, "SAVINGS", 1000);
		
		
		
		bankService.payToAccount(acc1, 1000);
		bankService.payToAccount(acc2, 10000);
		bankService.payToAccount(acc3, 1500);
		bankService.payToAccount(acc1, 2000);
		
		bankService.removeFromAccount(acc3, 400);
		bankService.removeFromAccount(acc1, 1500);
		bankService.removeFromAccount(acc2, 2000);
		bankService.removeFromAccount(acc3, 100);
		
		
	}
}