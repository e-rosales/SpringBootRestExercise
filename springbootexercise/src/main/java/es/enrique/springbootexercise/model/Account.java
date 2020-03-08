package es.enrique.springbootexercise.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

/**
 * Java bean that represents and account in the system. It's associated to a
 * unique name and holds an amount of money in a given currency. The treasury
 * property indicates if the account may or not be able to go into negative
 * balance.
 * 
 * @author Enrique Rosales
 *
 */
@Entity
@Table(name = "accounts")
public class Account {

	private Integer id;
	private String name;
	private CurrencyUnit currency;
	private Money money;
	private Boolean treasury;

	// Empty constructor required by JPA
	public Account() {

	}

	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the currency
	 */
	@Column(name = "currency", nullable = false)
	public CurrencyUnit getCurrency() {
		return currency;
	}

	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(CurrencyUnit currency) {
		this.currency = currency;
	}

	/**
	 * @return the money
	 */
	@Column(name = "money", nullable = false)
	public Money getMoney() {
		return money;
	}

	/**
	 * @param money the money to set
	 */
	public void setMoney(Money money) {
		this.money = money;
	}

	/**
	 * @return the treasury
	 */
	@Column(name = "treasury")
	public Boolean getTreasury() {
		return treasury;
	}

	/**
	 * @param treasury the treasury to set
	 */
	public void setTreasury(Boolean treasury) {
		this.treasury = treasury;
	}

}
