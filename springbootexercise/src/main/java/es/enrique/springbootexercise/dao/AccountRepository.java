package es.enrique.springbootexercise.dao;

import org.springframework.data.repository.CrudRepository;

import es.enrique.springbootexercise.model.Account;

/**
 * CrudRepository interface to manipulate {@link Account} entities on the
 * database.
 * 
 * @author Enrique Rosales
 *
 */
public interface AccountRepository extends CrudRepository<Account, Integer> {

	/**
	 * Finds an account by its unique name.
	 * 
	 * @param name The name of the account to search for.
	 * @return The account with the given name if it exists.
	 */
	Account findByName(String name);

}
