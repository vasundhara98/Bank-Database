package com.bank.demo.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Description of file PaymentOperation.java
 * 
 *
 *         It represents a payment operation at an account <br>
 */
@Entity
@DiscriminatorValue(value = "PO")
public class PaymentOperation extends Operation {

	/**
	 * 
	 */
	private static final long serialVersionUID = -504864216994149407L;

	public PaymentOperation() {
		super();
	}

	public PaymentOperation(Date operationDate, double amount, double balance, Account account) {
		super(operationDate, amount, balance, account);
	}

}
