package assignment3Package;

import java.util.ArrayList;


public class Cashier 
{
	double total;
	ArrayList<String> item;
	ArrayList<Double> price;
	int noOfItems;
	double average;
	double tendered;
	double result;
	
	private int quarters;
	private int dimes;
	private int nickels;
	private int pennies;
	private int dollars;
	
	public Cashier()
	{
		item = new ArrayList<String>();
		price = new ArrayList<Double>();
	}
	
	public void add(String name, double price)
	{ 
		this.item.add(name);
		this.price.add(price);
		total += price;
		noOfItems++;
	}
	
	public void average()
	{
		average = total / noOfItems;
	}
	
	public void tendered(double amount)
	{
		tendered = amount;
	}
	

	public void makeChange()
	{
		result = tendered - total;
        double remainingPrice = 0;
        numDollars = (int)(change*100/dollars);
        remainingPrice = change*100 % dollars;
        numQuarters = (int)(remainingPrice/quarters);
        remainingPrice = remainingPrice % quarters;
        numDimes = (int)(remainingPrice/dimes);
        remainingPrice = remainingPrice % dimes;
        numNickels = (int)(remainingPrice/nickels);
        remainingPrice = remainingPrice % nickels;
        numPennies = (int)remainingPrice;
	}
	
    
    
    int getDollars() {
        return numDollars;
    }
    int getQuarters() {
        return numQuarters;
    }
    int getDimes() {
        return numDimes;
    }
    int getNickels() {
        return numNickels;
    }
    int getPennies() {
        return numPennies;
    }
    
    
}