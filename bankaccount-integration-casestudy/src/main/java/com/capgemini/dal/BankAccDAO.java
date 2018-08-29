package com.capgemini.dal;

import java.util.List;

import com.capgemini.exceptions.UserNotFoundException;
import com.capgemini.pojo.BankAcc;
import com.capgemini.pojo.CurrentAcc;
import com.capgemini.pojo.SavingsAcc;

public interface BankAccDAO {
	public void addNewAccount(BankAcc acc);
	public int deleteAccount(int accNo) throws UserNotFoundException;
	public void updateAccount(BankAcc acc);
	public List<BankAcc> getAllAccount();
	
	public BankAcc getAccount(int accNo);
	public SavingsAcc getSavingsAcc(int accNo);
	public CurrentAcc getCurrentAcc(int accNo);
	
	
	

}
