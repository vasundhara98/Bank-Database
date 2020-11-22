package com.bank.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.ui.Model;

import com.bank.demo.entities.Account;
import com.bank.demo.entities.Operation;

/**
 * Description of file IBankService.java
 * 
 *
 *         This interface provide the functional requirement of the bank
 *         application <br>
 */
public interface IBankService {
	

	public Account getAccountById(int accountId);
	

	/**
	 * It is used to add the specified amount 
	 * account <br>
	 * 
	 * @param accountId
	 * @param amount
	 */
	
	
	
	
	public void payToAccount(int accountId, double amount);

	/**
	 * This method is used to remove the specified amount from the specified
	 * account
	 * 
	 * @param accountId
	 * @param amount
	 */
	public void removeFromAccount(int accountId, double amount);

	/**
	 * This method is used to make a transfer between two account.
	 * 
	 * @param accountOriginId
	 * @param accountDestId
	 * @param amount
	 */

	public void transfer(int accountOriginId, int accountDestId, double amount);

	/**
	 * This method is used to get operation of the specified account by page
	 * 
	 * @param accountId
	 * @param page
	 * @param size
	 * @return
	 */

	public Page<Operation> getAccountOperationByPage(int accountId, int page, int size);

	/**
	 * It is used to add a new 
	 * account <br>
	 * 
	 * @param accountId
	 * @param amount
	 */
	
	

	public int addAccount(String name, String email, double overdraft, String accountType, double balance);
	
	/**
	 * It is used to list all  
	 * accounts <br>
	 * 
	 * @param accountId
	 * @param amount
	 */
	
	public Page<Account> getAllAccounts(int page, int size);
	
	/**
	 * util to route to
	 * accounts <br>
	 * 
	 * @param accountId
	 * @param amount
	 */


	public void routeGetAccount(int accountId, Model model, int page, Account account);
	
}
