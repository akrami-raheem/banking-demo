package com.bank.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "transaction_details")
public class TrasnsactionsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "transaction_numer")
	private Long transactionNumber;

	@Column(name = "type")
	private String type;

	@ManyToOne
	@JoinColumn(name = "account_number", nullable = false)
	private AccountDetailsEntity accountDetails;

	@Column(name = "amount")
	private long amount;

	@Column(name = "created_date")
	@CreatedDate
	private Date createdDate;

	@Column(name = "balance")
	private long balance;

	public Long getTransactionNumber() {
		return transactionNumber;
	}

	public void setTransactionNumber(Long transactionNumber) {
		this.transactionNumber = transactionNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public AccountDetailsEntity getAccountDetails() {
		return accountDetails;
	}

	public void setAccountDetails(AccountDetailsEntity accountDetails) {
		this.accountDetails = accountDetails;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

}