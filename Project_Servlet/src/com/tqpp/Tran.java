package com.tqpp;

public class Tran
{
	public int tran_id;
	public String User_id;
	public String Payee_id;
	public String date;
	public int amount;
	
	public Tran(int tran_id, String user_id, String payee_id, String date, int amount) {
		super();
		this.tran_id = tran_id;
		User_id = user_id;
		Payee_id = payee_id;
		this.date = date;
		this.amount = amount;
	}
	public int getTran_id() {
		return tran_id;
	}
	public void setTran_id(int tran_id) {
		this.tran_id = tran_id;
	}
	public String getUser_id() {
		return User_id;
	}
	public void setUser_id(String user_id) {
		User_id = user_id;
	}
	public String getPayee_id() {
		return Payee_id;
	}
	public void setPayee_id(String payee_id) {
		Payee_id = payee_id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}

}
