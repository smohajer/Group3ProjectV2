/*
 * filename: GUI.java
 * Authors: L. Cindy Doan and Greg Fury
 * 05/01/2021
 * This application calculates the daily compounded interest earned from a checking or savings account balance.
 * Original source code found at https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
 * Code was modified to create a interest earnded ledger app
 */
public class Account {	
	//initailize account balance
	private double balance;       
        private double interest;        
        private double rate;
        
	//a constructor which sets the initial balance of the account
        public Account() {		
	} 
        
	public Account(double initialBal) {
		balance = initialBal;
	}  
         
        //a constructor which sets the interest
        public Account(double amount, double rate) {
		interest = amount;
	}	
	//a getter method which returns the balance
	public double getBalance() {
		return balance;
	}
        
        public double getRate() {
		return rate;
	}
        //a getter method which returns the interest
        public double getInterest() {
		return interest;
	}
        //a method to write new balance to file
        public double SaveToFile() {
		return interest + balance;
	}
	//a method to update balance with user input amount
        public void updateBalance(double amount){
		balance = amount;
	}  
        
        public void updateRate(double amount){
		rate = amount;
	}  
        
        public void updateInterest(double amount){
		interest = amount;
	}  
	//deposits amount; adds to current amount
	public void deposit(double amount){
		balance = balance + amount;
	}
        //calculate daily compounded interest
        public void interestCalc(double rate){
                interest = balance * ((rate/100) /365);
		//double interest = amount * (rate/365);
	}	
	//decreases balance by the amount being withdrawn or throws insufficientfunds exception if not enough
	public void withdraw(double amount) throws InsufficientFunds{				
		//transaction count is increased by 1
		if (amount <= balance) {
			balance = balance - amount;			
		}		
		//insufficient funds exception is thrown
		else {
			double difference = amount - balance;
			throw new InsufficientFunds(difference);
		}
	}	
	//withdraws money from the account
	public void transfer(double amount, Account target) throws InsufficientFunds{		
		//transfers amount less than amount in balance
		if (amount <= balance) {
			balance = balance - amount;
			target.deposit(amount);
		}		
		//otherwise an insufficient funds exception is thrown
		else {
			double difference = amount - balance;
			throw new InsufficientFunds(difference);
		}
	}		
}
