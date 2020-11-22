package com.bank.demo.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Description of file SavingsAccount.java <br>
 * 
 * 
 *         It represents a saving account in bank <br>
 *
 */
@Entity
@DiscriminatorValue(value = "SA")
public class SavingsAccount extends Account {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1397159430060752734L;

	public SavingsAccount() {
		super();
	}

	public SavingsAccount(int accountId, Date creationDate, Customer customer,
			 double balance) {
		super(accountId, creationDate, customer, balance);
	}

}
