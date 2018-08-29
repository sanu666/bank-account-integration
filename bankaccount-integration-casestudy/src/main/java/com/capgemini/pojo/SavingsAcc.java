package com.capgemini.pojo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.capgemini.dal.BankAccDAO;
import com.capgemini.exceptions.WithdrawAmountExceededException;


public class SavingsAcc extends BankAcc{
	protected boolean isSalAcc;
	
	

	public SavingsAcc() {
		super();
		
	}



	public SavingsAcc(int accNo, String accName, double accBal, boolean isSalAcc) {
		super(accNo, accName, accBal);
		this.isSalAcc=isSalAcc;
	}





	public void setSalAcc(boolean isSalAcc) {
		this.isSalAcc = isSalAcc;
	}



	public boolean getisSalAcc() {
		return isSalAcc;
	}



	public void setisSalAcc(boolean isSalAcc) {
		this.isSalAcc = isSalAcc;
	}



	



	@Override
	public String toString() {
		return "SavingsAcc [isSalAcc=" + isSalAcc + ", accNo=" + accNo
				+ ", accName=" + accName + ", accBal=" + accBal + "]";
	}



	@SuppressWarnings("resource")
	@Override
	public void withdraw(int accNo,double amount) throws WithdrawAmountExceededException {
		ApplicationContext applicationContext=new ClassPathXmlApplicationContext("Context.xml");
		BankAccDAO accDao=(BankAccDAO) applicationContext.getBean("bankAccDAO");
		BankAcc bankAccount=accDao.getAccount(accNo);
		if(amount>bankAccount.accBal)
		{
			throw new WithdrawAmountExceededException("\n\nAmount too high received\n\n");
		}
		bankAccount.accBal-=amount;
		accDao.updateAccount(bankAccount);
		}
	
	
}
