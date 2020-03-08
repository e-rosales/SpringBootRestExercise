package es.enrique.springbootexercise.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.enrique.springbootexercise.model.Account;

/**
 * 
 * Unit test cases for DAO layer.
 * 
 * @author Enrique Rosales
 *
 */
@SpringBootTest
class AccountRepositoryTest {

	@Autowired
	private AccountRepository repository;

	/**
	 * Asserts that an account can be found by the given name.
	 */
	@Test
	void test_findByName() {
		Account account = new Account();
		account.setName("Test Account");
		account.setCurrencyString("EUR");
		account.setMoneyDouble(0.0);

		repository.save(account);

		Account accountCreated = repository.findByName("Test Account");
		assertEquals(accountCreated.getName(), accountCreated.getName());
		assertEquals(accountCreated.getMoneyDouble(), accountCreated.getMoneyDouble());
		assertEquals(accountCreated.getCurrencyString(), accountCreated.getCurrencyString());
	}

	/**
	 * Asserts that a non-existing account name returns null.
	 */
	@Test
	void test_findByName_nameNotFound() {
		Account accountFound = repository.findByName("Non existing account");
		assertNull(accountFound);
	}

}
