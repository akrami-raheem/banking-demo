package com.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bank.entity.TrasnsactionsEntity;

@Repository
public interface TransactionsRepository extends JpaRepository<TrasnsactionsEntity, Long> {
	@Query(value = "select td.* from transaction_details td, account_details ad, user_details ud where td.account_number = ad.account_number and ad.user_id= ud.id and ud.user_name = :userName", nativeQuery = true)
	List<TrasnsactionsEntity> findLatestTransactionsByUserName(@Param("userName") String userName);
}