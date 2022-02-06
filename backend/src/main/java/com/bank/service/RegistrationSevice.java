package com.bank.service;

import com.bank.model.TransactionResponseDto;
import com.bank.model.UserDetailsDto;

public interface RegistrationSevice {
	public TransactionResponseDto save(UserDetailsDto user);

}