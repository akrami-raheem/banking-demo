package com.bank.model;

import java.util.ArrayList;
import java.util.List;

public class DashboardResponseDto {
	private long accountBalance;
	private long accountNumber;
	private List<TrasactionDetailDto> transactionDetails = new ArrayList<TrasactionDetailDto>();

	public long getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(long accountBalance) {
		this.accountBalance = accountBalance;
	}

	public List<TrasactionDetailDto> getTransactionDetails() {
		return transactionDetails;
	}

	public void setTransactionDetails(List<TrasactionDetailDto> transactionDetails) {
		this.transactionDetails = transactionDetails;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

}