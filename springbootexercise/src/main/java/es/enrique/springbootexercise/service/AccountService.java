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
	 * Increments the money of the account associated to the given name by the
	 * amount indicated.
	 * 
	 * @param accountName The account name in which to deposit the money.
	 * @param amount      The amount of money to deposit.
	 */
	void deposit(String accountName, Double amount);

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
	 */
	void withdraw(String accountName, Double amount) throws NegativeBalanceException;

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
	 */
	void transfer(String accountFrom, String accountTo, Double amount) throws NegativeBalanceException;

}
