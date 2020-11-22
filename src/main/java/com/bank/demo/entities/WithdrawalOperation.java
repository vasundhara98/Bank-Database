package com.bank.demo.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Description of file WithdrawalOperation <br>
 * 
 *
 *         It represents a withdrawal operation for an account <br>
 */
@Entity
@DiscriminatorValue(value = "WO")
public class WithdrawalOperation extends Operation {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5444265658335247078L;

	public WithdrawalOperation() {
		super();
	}

	public WithdrawalOperation(Date operationDate, double amount,double balance,  Account account) {
		super(operationDate, amount, balance, account);
	}

}
