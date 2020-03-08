package es.enrique.springbootexercise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.enrique.springbootexercise.dao.AccountRepository;
import es.enrique.springbootexercise.model.Account;
import es.enrique.springbootexercise.service.AccountAlreadyExistsException;
import es.enrique.springbootexercise.service.AccountNotFoundException;
import es.enrique.springbootexercise.service.AccountService;
import es.enrique.springbootexercise.service.NegativeBalanceException;

/**
 * Implementation of {@link AccountService}.
 * 
 * @author Enrique Rosales
 *
 */
@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountRepository repository;

	@Override
	public void create(String name, String currency, Boolean treasury) throws AccountAlreadyExistsException {
		if (repository.findByName(name) != null) {
			// Account name already exists. Stop new account creation.
			throw new AccountAlreadyExistsException();
		}

		Account account = new Account();
		account.setName(name);
		account.setCurrencyString(currency);
		account.setMoneyDouble(0.0);
		account.setTreasury(treasury);
		repository.save(account);
	}

	@Override
	public Account find(String name) throws AccountNotFoundException {
		Account found = repository.findByName(name);
		if (found == null) {
			throw new AccountNotFoundException();
		}
		return found;
	}

	@Override
	public void deposit(String accountName, Double amount) throws AccountNotFoundException {
		Account account = repository.findByName(accountName);
		if (account == null) {
			// The account doesn't exist.
			throw new AccountNotFoundException();
		}

		account.setMoney(account.getMoney().plus(amount));
		repository.save(account);
	}

	@Override
	public void withdraw(String accountName, Double amount) throws NegativeBalanceException, AccountNotFoundException {
		Account account = repository.findByName(accountName);
		if (account == null) {
			// The account doesn't exist.
			throw new AccountNotFoundException();
		}

		if (!account.getTreasury()) {
			if (account.getMoney().getAmount().doubleValue() - amount < 0) {
				// Non-treasury account would go negative, don't do the operation.
				throw new NegativeBalanceException();
			}
		}

		account.setMoney(account.getMoney().minus(amount));
		repository.save(account);

	}

	@Override
	public void transfer(String accountFrom, String accountTo, Double amount)
			throws NegativeBalanceException, AccountNotFoundException {
		Account accountFromEntity = repository.findByName(accountFrom);
		Account accountToEntity = repository.findByName(accountTo);

		if (accountFromEntity == null || accountToEntity == null) {
			throw new AccountNotFoundException();
		}

		if (!accountFromEntity.getTreasury()) {
			if (accountFromEntity.getMoney().getAmount().doubleValue() - amount < 0) {
				// Non-treasury account would go negative, don't do the operation.
				throw new NegativeBalanceException();
			}
		}

		accountFromEntity.setMoney(accountFromEntity.getMoney().minus(amount));
		accountToEntity.setMoney(accountToEntity.getMoney().plus(amount));
		repository.save(accountFromEntity);
		repository.save(accountToEntity);
	}

}
