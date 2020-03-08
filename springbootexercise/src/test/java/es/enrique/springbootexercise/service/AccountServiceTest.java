package es.enrique.springbootexercise.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import es.enrique.springbootexercise.dao.AccountRepository;
import es.enrique.springbootexercise.model.Account;

@SpringBootTest
public class AccountServiceTest {

	@MockBean
	private AccountRepository repository;

	@Autowired
	private AccountService service;

	@BeforeEach
	void setupMock() {
		// Set up the accounts for testing.
		Account testAccount_1 = new Account();
		testAccount_1.setName("Test 1");
		testAccount_1.setCurrencyString("EUR");
		testAccount_1.setMoneyDouble(0.0);
		testAccount_1.setTreasury(false);

		Account testAccount_2 = new Account();
		testAccount_2.setName("Test 1");
		testAccount_2.setCurrencyString("EUR");
		testAccount_2.setMoneyDouble(0.0);
		testAccount_2.setTreasury(false);

		// Set up mock repository

		Mockito.when(repository.findByName(testAccount_1.getName())).thenReturn(testAccount_1);

		Mockito.when(repository.findByName(testAccount_2.getName())).thenReturn(testAccount_2);
	}

	/**
	 * Asserts that an account can be found by its unique name.
	 */
	@Test
	void test_findAccountByName() {

		try {

			String name = "Test 1";
			Account found = service.find(name);

			assertEquals(name, found.getName());

		} catch (AccountNotFoundException e) {
			fail("Account not found");
		}

	}

	/**
	 * Asserts that a search for a non-existing account throws an exception
	 */
	@Test
	void test_findAccountByName_notFound() {
		String name = "Non existing account";

		assertThrows(AccountNotFoundException.class, () -> service.find(name));
	}

}
