package com.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bank.entity.AccountDetailsEntity;

@Repository
public interface AccountDetailsRepository extends JpaRepository<AccountDetailsEntity, Long> {

	@Query(value = "from AccountDetailsEntity ad where ad.userDetails.userName =:userName")
	AccountDetailsEntity findAccountDetailsByUserName(@Param("userName") String userName);
}