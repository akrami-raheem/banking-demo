package com.bank.service;

import com.bank.model.DashboardResponseDto;
import com.bank.model.TransactionRequestDto;
import com.bank.model.TransactionResponseDto;

public interface TransactionService {
	public DashboardResponseDto fetchLatestTransactions(String userName);
	public TransactionResponseDto performTransaction(TransactionRequestDto transactionDto);
}
