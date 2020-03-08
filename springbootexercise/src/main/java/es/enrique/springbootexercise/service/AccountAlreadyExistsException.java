package es.enrique.springbootexercise.service;

/**
 * Exception thrown when a new account is created using an already existing
 * account name in the system.
 * 
 * @author Enrique Rosales
 *
 */
public class AccountAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 3152667028638808711L;

	public AccountAlreadyExistsException() {
		super("Account name already exists!");
	}

}
