//Oscar Miranda
package lab7package;

		import java.text.NumberFormat;
		public class SchoolContribution  
{
	    static final byte  MAX_DAYS 	= 30;
		static final float TARGET = 5000.00f;
		static final NumberFormat df  = NumberFormat.getCurrencyInstance();;
		private int day;
		
		private String s;
		private double totalContribution;
		
		public SchoolContribution() 
		{
		s = "Day \tDaily Contribution\tTotal Contribution\n"; 	// Heading
		}
		
		public void addContribution(double amount) 
		{
		totalContribution = totalContribution + amount;
		day++;
		s = s + day + "\t    " + df.format(amount) + "\t\t    " + df.format(totalContribution) + "\n";
		}
		
		public boolean hasNotMadeTarget ()  
		{ 
		       return totalContribution < TARGET;
		}
		public boolean hasMoreTime() 
		{ 
		            return day < MAX_DAYS; 
		}
		public boolean metTarget () 
		{
		          return day <= MAX_DAYS && totalContribution >= TARGET;
		}
		public String toString() 
		{ 
		      return s; 
	}	
}

