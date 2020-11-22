package com.bank.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bank.demo.entities.Account;
import com.bank.demo.entities.Operation;
import com.bank.demo.service.IBankService;

/**
 * Description of file BankController.java
 * 
 *
 *
 * It represents the controller of the spring MVC framework. <br>
 *
 */
@Controller
public class BankController {

	@Autowired
	private IBankService bankService;

	@RequestMapping("/home")
	public String toHome() {
		return "accounts";
	}

	@RequestMapping("/getAccount")
	public String getAccountById(@RequestParam(name = "accountId") String accountId, Model model,
			@RequestParam(name = "page", defaultValue = "0") int page) {
		model.addAttribute("accountIdModel", accountId);
		try {
			Account account = bankService.getAccountById(Integer.parseInt(accountId));
			model.addAttribute("accountModel", account);
			Page<Operation> pageOperation = bankService.getAccountOperationByPage(Integer.parseInt(accountId), page, 4);
			int pageNumber = pageOperation.getTotalPages();
			Integer[] pages = new Integer[pageNumber];
			for (int i = 0; i < pageNumber; i++) {
				pages[i] = i;
			}
			model.addAttribute("pageOperationsModel", pageOperation);
			model.addAttribute("pagesModel", pages);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("exceptionModel", e);
		}

		return "accounts";
	}

	@RequestMapping("/getAccountsList" )
	public String getAccountsList(Model model,
			@RequestParam(name = "page", defaultValue = "0") int page) {
		try {
			Page<Account> pageAccounts = bankService.getAllAccounts(page, 4);
			System.out.println(pageAccounts.getContent());
			int pageNumber = pageAccounts.getTotalPages();
			Integer[] pages = new Integer[pageNumber];
			for (int i = 0; i < pageNumber; i++) {
				pages[i] = i;
			}
			model.addAttribute("pageAccountsModel", pageAccounts);
			model.addAttribute("pagesModel", pages);

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("exceptionModel", e);
		}

		return "listofaccounts";
	}
	
	@RequestMapping(value = "/addAccountOperation", method = RequestMethod.POST)
	public String addAccountOperation(Model model, String name, String email, String accountType, @RequestParam(defaultValue = "0") double overdraft, double balance, @RequestParam(name = "page", defaultValue = "0") int page) {
		int accountId = 0;
		try {
			accountId = bankService.addAccount(name, email, overdraft, accountType, balance);
			model.addAttribute("accountIdModel", accountId);
			Account account = bankService.getAccountById(accountId);
			model.addAttribute("accountModel", account);
			Page<Operation> pageOperation = bankService.getAccountOperationByPage(accountId, page, 4);
			int pageNumber = pageOperation.getTotalPages();
			Integer[] pages = new Integer[pageNumber];
			for (int i = 0; i < pageNumber; i++) {
				pages[i] = i;
			}
			model.addAttribute("pageOperationsModel", pageOperation);
			model.addAttribute("pagesModel", pages);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("exceptionModel", e);
		}
		return "accounts";
	}

	@RequestMapping(value = "/saveAccountOperation", method = RequestMethod.POST)
	public String saveAccountOperation(Model model, String accountId, String operationType, String accountIdDest,
			@RequestParam(defaultValue = "0") double operationAmount) {
		System.out.println(accountId);
		try {
			if (operationAmount == 0) {
				throw new RuntimeException("Enter valid amount greater than 0");
			}
			if (operationType.equals("PAYMENT")) {
				bankService.payToAccount(Integer.parseInt(accountId), operationAmount);
			} else if (operationType.equals("WITHDRAWAL")) {
				bankService.removeFromAccount(Integer.parseInt(accountId), operationAmount);
			} else {
				bankService.transfer(Integer.parseInt(accountId), Integer.parseInt(accountIdDest), operationAmount);
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorModel", e);
			return "redirect:/getAccount?accountId=" + accountId + "&errorModel=" + e.getMessage();
		}

		return "redirect:/getAccount?accountId=" + accountId;
	}

}
