package lab7package;
import java.text.NumberFormat;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
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
