package com.bank.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bank.constants.Constants;
import com.bank.entity.AccountDetailsEntity;
import com.bank.entity.TrasnsactionsEntity;
import com.bank.model.DashboardResponseDto;
import com.bank.model.Error;
import com.bank.model.TransactionRequestDto;
import com.bank.model.TransactionResponseDto;
import com.bank.model.TrasactionDetailDto;
import com.bank.repository.TransactionsRepository;

@Service
public class TransactionsServiceImpl implements TransactionService {

	@Autowired
	private TransactionsRepository transactionsRepository;

	@Autowired
	private AccountDetailsService accountDetailsService;

	@Value(value = "${balance.amount.error}")
	private String balanceAmountError;

	@Override
	public DashboardResponseDto fetchLatestTransactions(String userName) {
		DashboardResponseDto dashboardResponseDto = new DashboardResponseDto();
		List<TrasactionDetailDto> trasactionDetailDtos = null;

		AccountDetailsEntity accountDetailsEntity = accountDetailsService.findAccountDetailsByUserName(userName);
		dashboardResponseDto.setAccountBalance(accountDetailsEntity.getBalance());
		dashboardResponseDto.setAccountNumber(accountDetailsEntity.getAccountNumber());
		if (accountDetailsEntity != null && accountDetailsEntity.getTrasnsactionsEntity() != null
				&& !accountDetailsEntity.getTrasnsactionsEntity().isEmpty()) {
			trasactionDetailDtos = accountDetailsEntity.getTrasnsactionsEntity().stream()
					.sorted(Comparator.comparing(TrasnsactionsEntity::getCreatedDate))
					.filter(entity -> checkIfDateFallsInWeek(entity.getCreatedDate())).map(entity -> {
						TrasactionDetailDto trasactionDetailDto = new TrasactionDetailDto();
						trasactionDetailDto.setAmount(entity.getAmount());
						trasactionDetailDto.setTransactionNumber(entity.getTransactionNumber());
						trasactionDetailDto.setTransactionTime(getFormattedDate(entity.getCreatedDate()));
						trasactionDetailDto.setTransactionType(entity.getType());
						trasactionDetailDto.setClosingBalance(entity.getBalance());
						return trasactionDetailDto;
					}).collect(Collectors.toList());
			dashboardResponseDto.getTransactionDetails().addAll(trasactionDetailDtos);
		}
		return dashboardResponseDto;
	}

	@Override
	@Transactional
	public TransactionResponseDto performTransaction(TransactionRequestDto transactionDto) {

		TransactionResponseDto transactionResponseDto = new TransactionResponseDto();
		TrasnsactionsEntity trasnsactionsEntity = null;
		transactionResponseDto.setTransactionStatus(true);
		AccountDetailsEntity accountDetailsEntity = accountDetailsService
				.findAccountDetailsByUserName(transactionDto.getUserName());

		if (accountDetailsEntity != null) {
			if (transactionDto != null && transactionDto.getTransactionType() != null
					&& (transactionDto.getTransactionType().equals(Constants.DEPOSIT))) {
				accountDetailsEntity.setBalance(accountDetailsEntity.getBalance() + transactionDto.getAmount());
				trasnsactionsEntity = populateTransactionsEntity(transactionDto, accountDetailsEntity);
				accountDetailsEntity.getTrasnsactionsEntity().add(trasnsactionsEntity);
			} else if (accountDetailsEntity.getBalance() > transactionDto.getAmount()) {
				accountDetailsEntity.setBalance(accountDetailsEntity.getBalance() - transactionDto.getAmount());
				trasnsactionsEntity = populateTransactionsEntity(transactionDto, accountDetailsEntity);
				accountDetailsEntity.getTrasnsactionsEntity().add(trasnsactionsEntity);
			} else {
				transactionResponseDto = prepareError();
			}
		}
		return transactionResponseDto;
	}

	private TrasnsactionsEntity populateTransactionsEntity(TransactionRequestDto transactionDto,
			AccountDetailsEntity accountDetailsEntity) {
		TrasnsactionsEntity trasnsactionsEntity = new TrasnsactionsEntity();
		trasnsactionsEntity.setAccountDetails(accountDetailsEntity);
		trasnsactionsEntity.setCreatedDate(new Date());
		trasnsactionsEntity.setAmount(transactionDto.getAmount());
		trasnsactionsEntity.setType(transactionDto.getTransactionType());
		trasnsactionsEntity.setBalance(accountDetailsEntity.getBalance());
		transactionsRepository.save(trasnsactionsEntity);
		return trasnsactionsEntity;
	}

	private TransactionResponseDto prepareError() {
		TransactionResponseDto transactionResponseDto = new TransactionResponseDto();
		transactionResponseDto.setTransactionStatus(false);
		transactionResponseDto.setError(new Error(balanceAmountError));
		return transactionResponseDto;
	}

	private String getFormattedDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.dateFormat);
		return sdf.format(date);

	}

	private boolean checkIfDateFallsInWeek(Date transactionDate) {
		boolean result = true;
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, -7);
		Date dateBeforeSevenDays = cal.getTime();
		if (transactionDate.getTime() < dateBeforeSevenDays.getTime()) {
			result = false;
		}
		return result;
	}

}