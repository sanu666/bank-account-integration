package com.capgemini.exceptions;

@SuppressWarnings("serial")
public class WithdrawAmountExceededException extends Exception{
	
	public WithdrawAmountExceededException(String message)
	{
		super(message);
	}

}
