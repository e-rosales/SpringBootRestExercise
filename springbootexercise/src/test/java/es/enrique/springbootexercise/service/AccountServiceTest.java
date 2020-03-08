package es.enrique.springbootexercise.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.enrique.springbootexercise.dao.AccountRepository;
import es.enrique.springbootexercise.model.Account;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class AccountServiceTest {

	@Autowired
	private AccountRepository repository;

	@Autowired
	private AccountService service;

	@BeforeAll
	void setupMock() {
		// Set up the accounts for testing.
		Account testAccount_1 = new Account();
		testAccount_1.setName("Test 1");
		testAccount_1.setCurrencyString("EUR");
		testAccount_1.setMoneyDouble(0.0);
		testAccount_1.setTreasury(false);

		Account testAccount_2 = new Account();
		testAccount_2.setName("Test 2");
		testAccount_2.setCurrencyString("EUR");
		testAccount_2.setMoneyDouble(100.0);
		testAccount_2.setTreasury(true);

		repository.save(testAccount_1);
		repository.save(testAccount_2);
	}

	/**
	 * Asserts that an account can be found by its unique name.
	 */
	@Test
	void test_findAccountByName() {

		try {

			String name = "Test 2";
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

	/**
	 * Asserts that a new account is created correctly in the system.
	 */
	@Test
	void test_createNewAccount() {
		try {
			String accountName = "Test 3";
			String currency = "EUR";
			service.create(accountName, currency, false);

			Account created = repository.findByName("Test 3");
			assertEquals(accountName, created.getName());
		} catch (AccountAlreadyExistsException e) {
			fail("Account already exists.");
		}
	}

	/**
	 * Asserts that if a new account is created with an already existing name, an
	 * exception is thrown.
	 */
	@Test
	void test_createNewAccount_AccountAlreadyExists() {
		String accountName = "Test 1";
		String currency = "EUR";
		assertThrows(AccountAlreadyExistsException.class, () -> service.create(accountName, currency, false));
	}

	/**
	 * Tests the deposit money operation.
	 */
	@Test
	void test_depositMoney() {
		double amount = 100.0;
		String name = "Test 1";
		try {
			service.deposit(name, amount);
		} catch (AccountNotFoundException e) {
			fail("Account not found");
		}

		Account account = repository.findByName(name);
		assertEquals(amount, account.getMoney().getAmount().doubleValue());
	}
	
	/**
	 * Tests the withdraw money operation.
	 */
	@Test
	void test_withdrawMoney() {
		double amount = 50.0;
		String name = "Test 2";
		
		try {
			service.withdraw(name, amount);
		} catch (AccountNotFoundException e) {
			fail("Account not found");
		} catch (NegativeBalanceException e) {
			fail("Negative balance");
		}

		Account account = repository.findByName(name);
		assertEquals(50.0, account.getMoney().getAmount().doubleValue());
	}

}
