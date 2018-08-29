package com.capgemini.test;

import java.util.*;

import org.springframework.context.support.ClassPathXmlApplicationContext;










import com.capgemini.dal.BankAccDAOImpl;
import com.capgemini.exceptions.UserNotFoundException;
import com.capgemini.exceptions.WithdrawAmountExceededException;
import com.capgemini.pojo.BankAcc;
import com.capgemini.pojo.CurrentAcc;
import com.capgemini.pojo.SavingsAcc;






public class BankAccountTest {
	
	
	
	

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		BankAccDAOImpl dao=context.getBean(BankAccDAOImpl.class);
		
		Scanner sc=new Scanner(System.in);
		int SavingsAccChoice=0,typeChoice=0;
		BankAcc bankAccobj=null;
		SavingsAcc savingsAccobj=null;
		CurrentAcc currentAccobj=null;
		int ch,id;
		String name;
		float amount;
		double accBal;
		while(true)
		{
			System.out.println("\n\n\nEnter Your Choice:\n\n1-add user\n2-retreive all users\n3-Delete account by id\n4-update account by id\n5-Deposit money\n6-Withdraw money\n9-exit\n\n\n");
			int choice=sc.nextInt();
			switch(choice)
			{
			
			
			case 1:
				System.out.println("Enter name and opening Balance\n");
				name=sc.next();
				accBal=sc.nextDouble();
				System.out.println("Enter 1 for creating a Savings account\nEnter 2 for creating a Current account");
				 typeChoice=sc.nextInt();
				 
				if(typeChoice==1)
				{
					System.out.println("Enter 1-Salaried account\n2-Non-Salaried Account\n");
					 SavingsAccChoice=sc.nextInt();
				}
				
				if(typeChoice==2)
				{
					BankAccountTest.addCurrenntAccount(name,accBal);
				}
				
				else if(typeChoice==1 && SavingsAccChoice==1)
				{
					BankAccountTest.addSavingsSalaried(name,accBal);
				}
				
				else
				{
					BankAccountTest.addSavingsNonSalaried(name,accBal);
				}
				
				break;
				
				
				
			case 2:
				
				
				System.out.println("The List of accounts is:\n\n");
				List<BankAcc> bank=dao.getAllAccount();
				for(BankAcc p : bank){
					System.out.println("\n\nBank List::"+p);
				}
				break;
				
				
				
			case 3:
				
				System.out.println("Enter the account Number to delete:\n\n");
				ch=sc.nextInt();
				int response=0;
				try {
				 response=dao.deleteAccount(ch);	
					}
				catch (UserNotFoundException e1) {
						// TODO Auto-generated catch block
						System.out.println(e1.toString());
					}
				if(response!=0)
				System.out.println("\n\nUser Deleted Sucessfully\n\n");
				break;
				
				
				
			case 4:
				
				try{
				System.out.println("\nEnter your id and name to update\n");
				 id=sc.nextInt();
				String nameupdate=sc.next();
				bankAccobj=dao.getAccount(id);
				bankAccobj.setAccName(nameupdate);
				dao.updateAccount(bankAccobj);
				}
				catch(IndexOutOfBoundsException e)
				{
					System.out.println("\n\nUser With this Id does not exist\n\n");
				}
				break;
				
				
			case 5:
				
				try{
				System.out.println("\n\nEnter id of the account\n");
				 id=sc.nextInt();
				 bankAccobj=dao.getAccount(id);
				System.out.println("\n"+bankAccobj+"\n");
				
				System.out.println("\n\nEnter amount you want to deposit\n");
				amount=sc.nextFloat();
				
				bankAccobj.deposit(id, amount);
					System.out.println("\n\nUpdated Account:-\n\n"+dao.getAccount(id)+"\n\n");
				}
				catch(IndexOutOfBoundsException e)
				{
					System.out.println("\n\nUser With this Id does not exist\n\n");
				}
				break;
				
				
			case 6:
				
				
				try{
				System.out.println("Enter 1 for Savings Account and 2 for Current Account");
				ch=sc.nextInt();
				if(ch==1)
				{
				System.out.println("\n\nEnter accc Number of the account\n");
				 id=sc.nextInt();
				 bankAccobj=dao.getAccount(id);
				 savingsAccobj=dao.getSavingsAcc(id);
				System.out.println(bankAccobj);
				System.out.println("\n\nEnter amount you want to Withdraw\n");
				amount=sc.nextFloat();
				
						try {
							savingsAccobj.withdraw(id, amount);
						} catch (WithdrawAmountExceededException e) {
							// TODO Auto-generated catch block
							System.out.println(e.toString());
							break;
						}
						System.out.println("\nWithdraw Successful\n\n");
						System.out.println("\n"+dao.getAccount(id)+"\n");
					
					
				}
				
				
				
				
				else if(ch==2)
				{
					System.out.println("\n\nEnter accc Number of the account\n");
					 id=sc.nextInt();
					 bankAccobj=dao.getAccount(id);
					 currentAccobj=dao.getCurrentAcc(id);
					System.out.println(bankAccobj);
					System.out.println("\n\nEnter amount you want to Withdraw\n");
					amount=sc.nextFloat();
					
						try {
							currentAccobj.withdraw(id, amount);
						} catch (WithdrawAmountExceededException e) {
							// TODO Auto-generated catch block
							System.out.println(e.toString());
							break;
						}
						System.out.println("\nWithdraw Successful\n\n");
						System.out.println("\n"+dao.getAccount(id)+"\n");
					}
					
					
				
				else
					System.out.println("\nIncorrect Account Type\n");
				}
				catch(IndexOutOfBoundsException e)
				{
					System.out.println("\n\nUser With this Id does not exist\n\n");
				}
				break;
				
				
			default:
				return;
			}
		}

		
		}
	
	

	@SuppressWarnings("resource")
	public static void addSavingsNonSalaried(String name, double accBal) {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		SavingsAcc bank=new SavingsAcc();
		bank.setAccName(name);
		bank.setAccBal(accBal);
		bank.setisSalAcc(false);
		BankAccDAOImpl dao=context.getBean(BankAccDAOImpl.class);
		dao.addNewAccount(bank);
		
	}

	@SuppressWarnings("resource")
	public static void addSavingsSalaried(String name, double accBal) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		SavingsAcc bank=new SavingsAcc();
		bank.setAccName(name);
		bank.setAccBal(accBal);
		bank.setisSalAcc(true);
		BankAccDAOImpl dao=context.getBean(BankAccDAOImpl.class);
		dao.addNewAccount(bank);
	}
	@SuppressWarnings("resource")
	public static void addCurrenntAccount(String name, double accBal)
	{
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		CurrentAcc bank=new CurrentAcc();
		bank.setAccName(name);
		bank.setAccBal(accBal);
		bank.setOdLimit(5000);
		BankAccDAOImpl dao=context.getBean(BankAccDAOImpl.class);
		dao.addNewAccount(bank);
	}	

	}


