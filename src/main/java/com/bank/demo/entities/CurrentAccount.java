package com.bank.demo.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Description of file CurrentAccount.java <br>
 *
 *
 *         It represents the current account in bank <br>
 *       
 */
@Entity
@DiscriminatorValue(value = "CA")
public class CurrentAccount extends Account {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1807396224062882024L;
	// decouvert
	private double overdraft;

	public CurrentAccount() {
		super();
	}

	public CurrentAccount(int accountId, Date creationDate, Customer customer, double overdraft, double balance) {
		super(accountId, creationDate, customer, balance);
		this.overdraft = overdraft;
	}

	public double getOverdraft() {
		return overdraft;
	}

	public void setOverdraft(double overdraft) {
		this.overdraft = overdraft;
	}

}
