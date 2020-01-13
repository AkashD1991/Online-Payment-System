package com.tqpp;

import java.sql.Date;

public class Users 
{
	int account;
	String name;
	String email;
	String password;
	String b_date;
	int amount;
	int role;
	String rolen;
	int isActiveId;
	String isActiveName;
	
	public int getAccount() {
		return account;
	}
	public String getRolen() {
		return rolen;
	}
	public void setRolen(String rolen) {
		this.rolen = rolen;
	}
	public void setAccount(int account) {
		this.account = account;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public Users(String name, String email, String password, String b_date, int amount)
	{
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.b_date = b_date;
		this.amount = amount;
	}
	public Users() {
		super();
	}
	
	
	public Users(int account, String name, String email, String password, String b_date, int amount, int role,
			String rolen, int isActiveId, String isActiveName) {
		super();
		this.account = account;
		this.name = name;
		this.email = email;
		this.password = password;
		this.b_date = b_date;
		this.amount = amount;
		this.role = role;
		this.rolen = rolen;
		this.isActiveId = isActiveId;
		this.isActiveName = isActiveName;
	}
	 
	
	
	public String getEmail() {
		return email;
	}
	public Users(int account, String name, String email, String b_date, int amount, String rolen, String isActiveName) {
		super();
		this.account = account;
		this.name = name;
		this.email = email;
		this.b_date = b_date;
		this.amount = amount;
		this.rolen = rolen;
		this.isActiveName = isActiveName;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getB_date() {
		return b_date;
	}
	public void setB_date(String b_date) {
		this.b_date = b_date;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getIsActiveId() {
		return isActiveId;
	}
	public void setIsActiveId(int isActiveId) {
		this.isActiveId = isActiveId;
	}
	public String getIsActiveName() {
		return isActiveName;
	}
	public void setIsActiveName(String isActiveName) {
		this.isActiveName = isActiveName;
	}

	
}
