package com.bank.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "account_details")
public class AccountDetailsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "account_number")
	private Long accountNumber;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "balance")
	private long balance;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
//	@OneToOne(mappedBy = "accountDetails")
	private UserDetailsEntity userDetails;

	@OneToMany(mappedBy = "accountDetails")
	private Set<TrasnsactionsEntity> trasnsactionsEntity;

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	public UserDetailsEntity getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetailsEntity userDetails) {
		this.userDetails = userDetails;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<TrasnsactionsEntity> getTrasnsactionsEntity() {
		return trasnsactionsEntity;
	}

	public void setTrasnsactionsEntity(Set<TrasnsactionsEntity> trasnsactionsEntity) {
		this.trasnsactionsEntity = trasnsactionsEntity;
	}
	
	

}