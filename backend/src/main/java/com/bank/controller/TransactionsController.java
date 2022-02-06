package com.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.model.TransactionRequestDto;
import com.bank.model.TransactionResponseDto;
import com.bank.service.TransactionService;

@CrossOrigin()
@RestController
@RequestMapping({ "/transactions" })
public class TransactionsController {

	@Autowired
	private TransactionService transactionService;

	@GetMapping(value = "/{userName}", produces = "application/json")
	public ResponseEntity<?> fetchLatestTransactions(@PathVariable String userName) {
		return ResponseEntity.ok(transactionService.fetchLatestTransactions(userName));

	}

	@PostMapping(value = "/performTransaction")
	public ResponseEntity<TransactionResponseDto> performTransaction(
			@RequestBody TransactionRequestDto transactionDto) {
		TransactionResponseDto transactionResponseDto = transactionService.performTransaction(transactionDto);
		return ResponseEntity.ok(transactionResponseDto);
	}
}