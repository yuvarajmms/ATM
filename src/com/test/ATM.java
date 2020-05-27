package com.test;



import java.util.Scanner;


public class ATM {

	public static int balance = 0 , twenties = 0, tens = 0, fives = 0, ones = 0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		System.out.println("ATM !!");
		while(true) {
			
			
			System.out.println("Enter 1 for Deposit");
            System.out.println("Enter 2 for Withdraw");
            System.out.println("Enter 3 for EXIT");
            
            int n = scanner.nextInt();
            switch(n) {
            	case 1:
					System.out.println("Enter Amounts to be Deposited:");
            		scanner.skip("[\r\n]+");
            		String inputValue = scanner.nextLine();
            		deposit(inputValue);
                    break;
                    
            	case 2:
            		System.out.println("Enter money to be Withdraw:");
                    int withdrawAmt = scanner.nextInt();
                    withdraw(withdrawAmt);
                    break;
                    
            	case 3:
            		System.exit(0);
            	
            }
            
		}
		
	}
	
	/**
	 * Deposit logic will be handled here
	 * @param inputValue
	 */
	public static void deposit(String inputValue) {
		
		String[] array = inputValue.split(",");
		int inputTwenties = 0, inputTens= 0, inputFives=0, inputOnes=0;
		for(String str : array) {
			if(str.contains("20s")) {
				inputTwenties = getAmount(str);
			} else if(str.contains("10s")) {
				inputTens = getAmount(str);
			} else if(str.contains("5s")) {
				inputFives = getAmount(str);
			} else if(str.contains("1s")) {
				inputOnes = getAmount(str);
			}
		}
		
		
		int tempBalanace = (inputTwenties * 20) + (inputTens * 10) + (inputFives * 5) + (inputOnes * 1);
		
		if(inputTwenties < 0 || inputTens < 0 || inputFives < 0 || inputOnes < 0 ) {
			System.out.println("Incorrect deposit amount");
		} else if(tempBalanace > 0) {
        	//Update the balance
        	balance += tempBalanace;
        	//Update the currency values
        	twenties += inputTwenties;
        	tens += inputTens;
        	fives += inputFives;
        	ones += inputOnes;
        	System.out.println("Balance: 20s="+twenties+", 10s="+tens+", 5s="+fives+", 1s="+ones+", Total="+balance);
            
        } else {
        	System.out.println("Deposit amount cannot be zero");
        }
	}

	/**
	 * Withdraw business logic handled here
	 * @param withdraw
	 */
	 public static void withdraw(int withdraw) {
		 if( withdraw > 0 && balance >= withdraw) {
         	
         	int twenty = 0, ten = 0, five = 0, one = 0;
     		if(twenties > 0) {
     			twenty = (withdraw)/20;
     			if(twenty > twenties) {
     				twenty = twenties;
     			}
     			twenties -= twenty;
     		}
     		if(tens > 0) {
     			ten = (withdraw - (twenty*20)) / 10;
     			if(ten > tens) {
     				ten = tens;
     			}
     			tens -= ten;
     		}
     		if(fives > 0) {
     			five =  (withdraw - (twenty*20) - (ten*10)) / 5;
     			if(five > fives) {
     				five = fives;
     			}
     			fives -= five;
     		}
     		if(ones > 0) {
     			one =  (withdraw - (twenty*20) - (ten*10) - (five*5)) / 1;
     			if(one > ones) {
     				one = ones;
     			}
     			ones -= one;
     		}
     		balance -= withdraw;
     		System.out.println("Dispensed: 20s="+twenty+", 10s="+ten+", 5s="+five+", 1s="+one);
     		System.out.println("Balance: 20s="+twenties+", 10s="+tens+", 5s="+fives+", 1s="+ones+", Total="+balance);
             
         } else {
         	System.out.println("Incorrect or Insufficent Funds");
         }
	 }

	
    /**
     * 	The amount will be derived from the Input string 
     * @param valueString
     * @return Amount
     */
	public static int getAmount(String valueString) {
		int amt = 0;
		String[] array = valueString.split(":");
		amt = Integer.parseInt(array[array.length-1].trim());
		return amt;
		
	}
	
	

}
