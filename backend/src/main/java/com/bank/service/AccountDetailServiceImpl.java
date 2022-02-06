package com.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.entity.AccountDetailsEntity;
import com.bank.repository.AccountDetailsRepository;

@Service
public class AccountDetailServiceImpl implements AccountDetailsService {

	@Autowired
	private AccountDetailsRepository accountDetailsRepository;

	@Override
	public AccountDetailsEntity save(AccountDetailsEntity accountDetailsEntity) {
		return accountDetailsRepository.save(accountDetailsEntity);
	}

	@Override
	public AccountDetailsEntity findAccountDetailsByUserName(String userName) {
		return accountDetailsRepository.findAccountDetailsByUserName(userName);
	}

}