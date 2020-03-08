package es.enrique.springbootexercise.service;

/**
 * Exception thrown when a withdrawal operations is tried to be made on a
 * non-treasury account that will make its balance go below 0.
 * 
 * @author Enrique Rosales
 *
 */
public class NegativeBalanceException extends Exception {

	private static final long serialVersionUID = 4157071196076817662L;

	public NegativeBalanceException() {
		super("Not allowed to go negative!");
	}

}
