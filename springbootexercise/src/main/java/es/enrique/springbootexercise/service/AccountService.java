package es.enrique.springbootexercise.service;

import org.springframework.stereotype.Service;

import es.enrique.springbootexercise.model.Account;

/**
 * Service layer interface for manipulating {@link Account} objects in the
 * system.
 * 
 * @author Enrique Rosales
 *
 */
@Service
public interface AccountService {

	/**
	 * Creates a new {@link Account} with the given name and currency and sets the
	 * treasury property for the account. If the name already exists in the systems,
	 * throws {@link AccountAlreadyExistsException}.
	 * 
	 * A newly created account always has a balance of 0.
	 * 
	 * @param name     The name of the new account.
	 * @param currency The currency unit.
	 * @param treasury Indicates if this is a treasury account, allowing it to have
	 *                 negative balance.
	 * 
	 * @throws AccountAlreadyExistsException If the account name already exists in
	 *                                       the system.
	 */
	void create(String name, String currency, Boolean treasury) throws AccountAlreadyExistsException;

	/**
	 * Return the info of an {@link Account} associated to the given name.
	 * 
	 * @param name The name of the account to find.
	 * @throws AccountNotFoundException If the name is not associated to any
	 *                                  account.
	 */
	Account find(String name) throws AccountNotFoundException;

	/**
	 * Increments the money of the account associated to the given name by the
	 * amount indicated.
	 * 
	 * @param accountName The account name in which to deposit the money.
	 * @param amount      The amount of money to deposit.
	 * @throws AccountNotFoundException If the given name is not associated to any
	 *                                  account.
	 */
	void deposit(String accountName, Double amount) throws AccountNotFoundException;

	/**
	 * Decreases the money of the account associated to the given name by the amount
	 * indicated.
	 * 
	 * May throw {@link NegativeBalanceException} if the withdrawal would make a
	 * non-treasury account's balance go below 0.
	 * 
	 * @param accountName The account name in which to deposit the money.
	 * @param amount      The amount of money to deposit.
	 * @throws NegativeBalanceException If the withdrawal makes the account's
	 *                                  balance go below 0 and it's not a treasury
	 *                                  account.
	 * @throws AccountNotFoundException If the given name is not associated to any
	 *                                  account.
	 */
	void withdraw(String accountName, Double amount) throws NegativeBalanceException, AccountNotFoundException;

	/**
	 * Transfers the indicated amount of money from the first given account to the
	 * second.
	 * 
	 * May throw {@link NegativeBalanceException} if the withdrawal would make a
	 * non-treasury account's balance go below 0.
	 * 
	 * @param accountFrom The account from which the money is sent.
	 * @param accountTo   The account that receives the money.
	 * @param amount      The amount of money to transfer.
	 * @throws NegativeBalanceException If the withdrawal makes the account's
	 *                                  balance go below 0 and it's not a treasury
	 *                                  account.
	 * @throws AccountNotFoundException If the given name is not associated to any
	 *                                  account.
	 */
	void transfer(String accountFrom, String accountTo, Double amount)
			throws NegativeBalanceException, AccountNotFoundException;

}
