/*
 * Interest Ledger
 * Authore: L. Cindy Doan
 * 05/01/2021
 * This application calculates the daily compounded interest earned from a checking or savings account balance.
 * Original source code found at https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
 * Code was modified to create a interest earnded ledger app
 */
public class InsufficientFunds extends Exception {	
	//initializes balance
	private double balance;	
	//constructor for insufficientfunds exception
	public InsufficientFunds(double initial) {
		balance = initial;
	}
	//a getter method for balance
	public double getBalance() {
		return balance;
	}
}
