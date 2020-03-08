package es.enrique.springbootexercise.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

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
	private Boolean treasury;
	private String currencyString;
	private Double moneyDouble;
	
	// Complex types
	private CurrencyUnit currency;
	private Money money;

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
	 * @return the currencyString
	 */
	@Column(name = "currency", nullable = false)
	public String getCurrencyString() {
		return currencyString;
	}

	/**
	 * @param currencyString the currencyString to set
	 */
	public void setCurrencyString(String currencyString) {
		this.currencyString = currencyString;
		this.currency = CurrencyUnit.of(currencyString);
	}

	/**
	 * @return the moneyDouble
	 */
	@Column(name = "money", nullable = false)
	public Double getMoneyDouble() {
		return moneyDouble;
	}

	/**
	 * @param moneyDouble the moneyDouble to set
	 */
	public void setMoneyDouble(Double moneyDouble) {
		this.moneyDouble = moneyDouble;
		this.money = Money.of(currency, moneyDouble);
	}

	/**
	 * @return the currency
	 */
	@Transient
	public CurrencyUnit getCurrency() {
		return currency;
	}

	/**
	 * @return the money
	 */
	@Transient
	public Money getMoney() {
		return money;
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
