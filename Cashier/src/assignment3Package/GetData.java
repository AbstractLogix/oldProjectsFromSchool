package assignment3Package;
import javax.swing.JOptionPane;

public class GetData {
	
	
	
	static String str;
	static double getDouble(String s)
	{
		str = JOptionPane.showInputDialog(s);
		return Double.parseDouble(str);
	}
	static int getInt(String s)
	{
		str = JOptionPane.showInputDialog(s);
		return Integer.parseInt(str);
	}
	static String getWord(String s)
	{
		return JOptionPane.showInputDialog(s);
	}
}