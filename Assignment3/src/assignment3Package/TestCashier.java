package assignment3Package;
import java.util.Date;
import java.text.DateFormat;
import java.text.NumberFormat;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class TestCashier {
	

	public static void main(String[] arg)
	{
		
		Cashier c = new Cashier();
		String name;
		double price;
		
			name = GetData.getWord("Enter name of item");
			price = GetData.getDouble("Enter price of item");
		    c.add(name, price);
		
		    name = GetData.getWord("Enter name of item");
			price = GetData.getDouble("Enter price of item");
		    c.add(name, price);
		    
		    name = GetData.getWord("Enter name of item");
			price = GetData.getDouble("Enter price of item");
		    c.add(name, price);
		
		    name = GetData.getWord("Enter name of item");
			price = GetData.getDouble("Enter price of item");
		    c.add(name, price);
	    
	    
	    c.average();

	    
	    double amount = GetData.getDouble("Enter amount of money for payment");

	    c.tendered(amount); 
	    c.makeChange();

		generateReceipt(c);	
	}
       static void generateReceipt(Cashier c)
       {
    	   NumberFormat nf = NumberFormat.getInstance();
    	   Date d = new Date();			
    	   DateFormat df = DateFormat.getDateInstance();
    	   
			String receipt = "Oscars Corner Store\n"
							+ "Welcome – thanks for stopping with us on " + "\n" + df.format(d) + "\n"
							+   "\n" + "Items: \n";
			for ( String ray :c.item) {
				receipt += ray + " .......... ";
				receipt += c.price.get(c.item.indexOf(ray)) + "\n";
			}
			
			
			receipt+= "Total: " + String.valueOf(nf.format(c.total))+ "\n";
			receipt+= "Number of items purchased: " + String.valueOf(c.noOfItems) + "\n";
			receipt+= "Average cost was: " + String.valueOf(nf.format(c.average))+ "\n" + "___________" + "\n";
			
			receipt += "Amount tendered: " + String.valueOf(nf.format(c.tendered))+ "\n";
			
			receipt += "Cash back: " + nf.format(c.result) + "\n";
	
            receipt += "Dollars: \n" + c.getDollars() + "/n"; 
            receipt += "Quarters: \n" + c.getQuarters() + "/n";
            receipt += "Dimes: \n" + c.getDimes() + "/n";           
            receipt += "Nickels: \n" + c.getNickels() + "/n";           
            receipt += "Pennies: \n" + c.getPennies() + "/n";           
           
           
	    	JTextArea text = new JTextArea(receipt, 5, 25);
	    	JScrollPane scroll = new JScrollPane(text);
	
	    	JOptionPane.showMessageDialog(null, scroll, receipt, JOptionPane.INFORMATION_MESSAGE);
	    	   
	    	   
			
			
       }

}