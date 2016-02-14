package newpackage;
import javax.swing.JOptionPane;

public class TestPetrol {

	public static void main(String [] arg)
	{
		
		String str = JOptionPane.showInputDialog("COP2210 Petrol Station"
												+ "\nEnter the type of petrol"
												+ "\nr/R - for regular gas"
												+ "\np/P - for premium gas"
												+ "\ns/S- for super gas");
		char ch= str.charAt(0);
		str=JOptionPane.showInputDialog("Enter the amount of gas to be purchased");
		float amount = Float.parseFloat(str);
		
		Petrol p = new Petrol(ch,amount);
		p.calculateCost();
		JOptionPane.showMessageDialog(null, p.toString());
	}
}
