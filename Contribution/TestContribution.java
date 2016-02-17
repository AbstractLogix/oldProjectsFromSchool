//Oscar Miranda
package lab7package;

	import javax.swing.JOptionPane;
public class TestContribution{

		public static void main(String[] arg)
{
	SchoolContribution s = new SchoolContribution();
	while (s.hasNotMadeTarget() && s.hasMoreTime()) 
	{
		double amount = GetData.getDouble("Enter contribution");
		s.addContribution(amount);
	}
	
		 	if (s.hasMoreTime()){
		 		
			System.out.println(s.toString() + "\nyou have more time");
		 	}
				else{
		 		System.out.println("\nSorry we cannot go on the trip\n");
	
		 	}
	}
}