package com.bank.service;

import com.bank.entity.AccountDetailsEntity;

public interface AccountDetailsService {
	AccountDetailsEntity save(AccountDetailsEntity accountDetailsEntity);
	AccountDetailsEntity findAccountDetailsByUserName(String userName);
}