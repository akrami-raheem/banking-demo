package com.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bank.entity.AccountDetailsEntity;
import com.bank.entity.UserDetailsEntity;
import com.bank.model.Error;
import com.bank.model.TransactionResponseDto;
import com.bank.model.UserDetailsDto;
import com.bank.repository.AccountDetailsRepository;

@Service
public class RegistrationServiceImpl implements RegistrationSevice {

	@Value(value = "${user.exists}")
	private String userExists;

	@Autowired
	private AccountDetailsRepository accountDetailsRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public TransactionResponseDto save(UserDetailsDto user) {
		TransactionResponseDto transactionResponseDto = new TransactionResponseDto();
		transactionResponseDto.setTransactionStatus(true);
		UserDetailsEntity newUser = new UserDetailsEntity();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));

		AccountDetailsEntity accountDetails = new AccountDetailsEntity();
		accountDetails.setFirstName(user.getFirstName());
		accountDetails.setLastName(user.getLastName());
		accountDetails.setUserDetails(newUser);

		newUser.setAccountDetails(accountDetails);
		try {
			accountDetailsRepository.save(accountDetails);
		} catch (DataIntegrityViolationException e) {
			transactionResponseDto.setTransactionStatus(false);
			transactionResponseDto.setError(new Error(userExists));
		}
		return transactionResponseDto;
	}
}