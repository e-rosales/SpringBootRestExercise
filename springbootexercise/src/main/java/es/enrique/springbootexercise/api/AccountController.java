package es.enrique.springbootexercise.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.enrique.springbootexercise.model.Account;
import es.enrique.springbootexercise.service.AccountAlreadyExistsException;
import es.enrique.springbootexercise.service.AccountNotFoundException;
import es.enrique.springbootexercise.service.AccountService;
import es.enrique.springbootexercise.service.NegativeBalanceException;

/**
 * RESTful controller that provides an API to perform operations over accounts
 * on the system.
 * 
 * @author Enrique Rosales
 *
 */
@RestController
public class AccountController {

	@Autowired
	private AccountService service;

	/**
	 * Find an account by the given parameter and returns its information. Returns
	 * 404 status if no account is found.
	 * 
	 * @param name The name of the account to search for.
	 * @return The info of the account as a ResponseEntity.
	 */
	@GetMapping("find")
	public ResponseEntity<Account> find(@RequestParam(value = "name") String name) {
		try {
			Account account = service.find(name);
			return new ResponseEntity<Account>(account, HttpStatus.OK);
		} catch (AccountNotFoundException e) {
			// In case no account exists, return 404 status
			return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
		}

	}

	/**
	 * Creates a new account with the specified parameters. Returns 400 status if
	 * the account name is already in use.
	 * 
	 * @param name     The name of the account.
	 * @param currency The currency the account will use.
	 * @param treasury Determines if the account is a treasury account or not.
	 *                 Defaults to false.
	 * @return The newly created account as a ResponseBody.
	 */
	@GetMapping("create")
	public ResponseEntity<Account> create(@RequestParam(value = "name") String name,
			@RequestParam(value = "currency") String currency,
			@RequestParam(value = "treasury", defaultValue = "false") boolean treasury) {
		try {
			service.create(name, currency, treasury);
			Account account = service.find(name);
			return new ResponseEntity<Account>(account, HttpStatus.OK);
		} catch (AccountAlreadyExistsException e) {
			return new ResponseEntity<Account>(HttpStatus.BAD_REQUEST);
		} catch (AccountNotFoundException e) {
			return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Increments the amount of money in the given account by the amount specified.
	 * Return 404 status if the account doesn't exist.
	 * 
	 * @param name  The account in which to increase the balance.
	 * @param money The amount of money to increase.
	 * @return The account as a ResponseBody.
	 */
	@GetMapping("deposit")
	public ResponseEntity<Account> deposit(@RequestParam(value = "name") String name,
			@RequestParam(value = "money") double money) {
		try {
			service.deposit(name, money);
			Account account = service.find(name);
			return new ResponseEntity<Account>(account, HttpStatus.OK);
		} catch (AccountNotFoundException e) {
			return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Decreases the amount of money in the given account by the amount specified.
	 * Return 404 status if the account doesn't exist.
	 *
	 * If the withdrawal would make the account's balance go negative and it's not a
	 * treasury account, returns 400 status.
	 * 
	 * @param name  The account in which to decrease the balance.
	 * @param money The amount of money to withdraw.
	 * @return The account as a ResponseBody.
	 */
	@GetMapping("withdraw")
	public ResponseEntity<Account> withdraw(@RequestParam(value = "name") String name,
			@RequestParam(value = "money") double money) {
		try {
			service.withdraw(name, money);
			Account account = service.find(name);
			return new ResponseEntity<Account>(account, HttpStatus.OK);
		} catch (AccountNotFoundException e) {
			return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
		} catch (NegativeBalanceException e) {
			return new ResponseEntity<Account>(HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Transfer the amount of money specified from the first account to the second.
	 * 
	 * If any of the accounts doesn't exist, return a 404.
	 * 
	 * If the withdrawal would make the account's balance go negative and it's not a
	 * treasury account, returns 400 status.
	 * 
	 * @param nameAccountFrom The account from where the money is sent.
	 * @param nameAccountTo   The account that receives the money.
	 * @param money           The amount of money to transfer.
	 * @return The account as a ResponseBody.
	 */
	@GetMapping("transfer")
	public ResponseEntity<Account> transfer(@RequestParam(value = "nameAccountFrom") String nameAccountFrom,
			@RequestParam(value = "nameAccountTo") String nameAccountTo, @RequestParam(value = "money") double money) {
		try {
			service.transfer(nameAccountFrom, nameAccountTo, money);
			Account account = service.find(nameAccountFrom);
			return new ResponseEntity<Account>(account, HttpStatus.OK);
		} catch (AccountNotFoundException e) {
			return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
		} catch (NegativeBalanceException e) {
			return new ResponseEntity<Account>(HttpStatus.BAD_REQUEST);
		}
	}

}
