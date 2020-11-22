package com.bank.demo.entities;

import java.io.Serializable;

import java.util.Collection;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Description of file Account.java
 * 
 *
 *         It represents the customer account in bank <br>
 *         This copy right notice should not be removed <br>
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ACCOUNT_TYPE", discriminatorType = DiscriminatorType.STRING, length = 2)
public abstract class Account implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1011003148342636018L;
	/**
	 * 
	 */
	@Id
	private int accountId;
	private Date creationDate;
	private double balance;
	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@ManyToOne
	@JoinColumn(name = "CUSTOMER_CODE")
	private Customer customer;
	@OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
	private Collection<Operation> operations;

	public Account() {
		super();
	}

	public Account(int accountId, Date creationDate, Customer customer, double balance) {
		super();
		this.accountId = accountId;
		this.creationDate = creationDate;
		this.customer = customer;
		this.balance = balance;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Collection<Operation> getOperations() {
		return operations;
	}

	public void setOperations(Collection<Operation> operations) {
		this.operations = operations;
	}

}
