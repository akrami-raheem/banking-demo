package com.bank.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USER_DETAILS")
public class UserDetailsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "user_name", unique = true)
	private String userName;

	private String password;

	private boolean enabled = true;

	private Date lastLogin;

//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "account_id", referencedColumnName = "account_number")
	@OneToOne(mappedBy = "userDetails")
	private AccountDetailsEntity accountDetails;

	public UserDetailsEntity() {
	}

	public UserDetailsEntity(String name, String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUsername(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public AccountDetailsEntity getAccountDetails() {
		return accountDetails;
	}

	public void setAccountDetails(AccountDetailsEntity accountDetails) {
		this.accountDetails = accountDetails;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	

}