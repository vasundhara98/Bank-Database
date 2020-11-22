package com.bank.demo.service;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;

import com.bank.demo.dao.AccountRepository;
import com.bank.demo.dao.CustomerRepository;
import com.bank.demo.dao.OperationRepository;
import com.bank.demo.entities.Account;
import com.bank.demo.entities.CurrentAccount;
import com.bank.demo.entities.Customer;
import com.bank.demo.entities.Operation;
import com.bank.demo.entities.PaymentOperation;
import com.bank.demo.entities.SavingsAccount;
import com.bank.demo.entities.WithdrawalOperation;

@Service
@Transactional
public class BankServiceImp implements IBankService {

	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private OperationRepository operationRepository;
	
	@Autowired 
	private CustomerRepository customerRepository;

	@Override
	public Account getAccountById(int accountId) {
		Account account = accountRepository.findById(accountId).orElse(null);
		if (account == null)
			throw new RuntimeException("No account with the search string found.");

		return account;
	}
	
	@Override
	public Page<Account> getAllAccounts(int page, int size) {
		Page<Account> account = accountRepository.findAll(PageRequest.of(page, size, Sort.unsorted()));
		if (ObjectUtils.isEmpty(account))
			throw new RuntimeException("No accounts found");
		return account;
	}

	@Override
	public void payToAccount(int accountId, double amount) {
		Account currentAcc = getAccountById(accountId);
		PaymentOperation paymentOp = new PaymentOperation(new Date(), amount,
				currentAcc.getBalance()+amount, currentAcc);
		operationRepository.save(paymentOp);
		currentAcc.setBalance(currentAcc.getBalance()+amount);
		accountRepository.save(currentAcc);

	}

	@Override
	public void removeFromAccount(int accountId, double amount) {
		Account currentAcc = getAccountById(accountId);
		double solde = 0;
		if (currentAcc instanceof CurrentAccount)
			solde = ((CurrentAccount) currentAcc).getOverdraft();
		if (amount > (currentAcc.getBalance()+solde)) {
			throw new RuntimeException("Insufficient balance.");
		}
		WithdrawalOperation withdrawalOp = new WithdrawalOperation(new Date(),
				amount, currentAcc.getBalance()-amount, currentAcc);
		operationRepository.save(withdrawalOp);
		currentAcc.setBalance(currentAcc.getBalance()-amount);
		accountRepository.save(currentAcc);

	}

	@Override
	public void transfer(int accountOriginId, int accountDestId,
			double amount) {
		if (accountOriginId==accountDestId)
			throw new RuntimeException(
					"Impossible operation: account id must be different");
		Account originAccount = getAccountById(accountOriginId);
		double solde = 0;
		if (originAccount instanceof CurrentAccount)
			solde = ((CurrentAccount) originAccount).getOverdraft();
		if (amount > (originAccount.getBalance()+solde)) {
			throw new RuntimeException("Insufficient balance.");
		}
		removeFromAccount(accountOriginId, amount);
		payToAccount(accountDestId, amount);
		originAccount.setBalance(originAccount.getBalance()-amount);
		accountRepository.save(originAccount);

	}

	@Override
	public Page<Operation> getAccountOperationByPage(int accountId,
			int page, int size) {
		return operationRepository.getAccountOperationsByPage(accountId, 
				PageRequest.of(page, size, Sort.unsorted()));
	}

	@Override
	public int addAccount(String name, String email, double overdraft, String accountType, double balance) {
		Customer cust = customerRepository.save(new Customer(name, email));
		int accountId = new Random().nextInt(Integer.MAX_VALUE);
		if( accountType.equals("SAVINGS")) {
			accountRepository.save(new SavingsAccount(accountId, new Date(), cust, balance));
		} else if (accountType.equals("CURRENT")){ //Current account
			accountRepository.save(new CurrentAccount(accountId, new Date(), cust, overdraft, balance));
		} else {
			throw new RuntimeException("Invalid account type. Should be one of SAVINGS or CURRENT.");
		}
		return accountId;
	}
	
	@Override
	public void routeGetAccount(int accountId, Model model, int page, Account account) {
		
	}

}
