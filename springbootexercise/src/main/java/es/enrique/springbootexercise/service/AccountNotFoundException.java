package es.enrique.springbootexercise.service;

/**
 * Exception thrown when a query for an account is made but no account matches
 * the given name.
 * 
 * @author Enrique Rosales
 *
 */
public class AccountNotFoundException extends Exception {

	private static final long serialVersionUID = -1060690134304931574L;

	public AccountNotFoundException() {
		super("Account not found!");
	}
}
