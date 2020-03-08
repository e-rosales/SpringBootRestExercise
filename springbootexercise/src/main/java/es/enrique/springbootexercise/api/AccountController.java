package es.enrique.springbootexercise.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.enrique.springbootexercise.model.Account;
import es.enrique.springbootexercise.service.AccountAlreadyExistsException;
import es.enrique.springbootexercise.service.AccountNotFoundException;
import es.enrique.springbootexercise.service.AccountService;

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
	@GetMapping("/find")
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
	@PostMapping("/create")
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

}
