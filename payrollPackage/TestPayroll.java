package payrollPackage;

import java.util.Date;
import java.text.DateFormat;
import java.text.NumberFormat;

public class TestPayroll {

	public static void main(String[] arg)
	{
		//formatters
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		Date d = new Date();
		DateFormat df = DateFormat.getDateInstance();
		
		//objects		
		Payroll e1 = new Payroll(7557,40,32);
		
		//heading
		System.out.println("\nPayroll For Week of " + df.format(d));
		System.out.println("********************************");
		
		//body
		System.out.println("Employee ID#:.... " + e1.getId());
		System.out.println("Hours Worked:.... " + e1.getHours());
		System.out.println("Hourly Rate:.... " + nf.format(e1.getPay()) + "/hr");
		System.out.println("Your Salary is:.... " + nf.format(e1.salary()));
		
		
	}
}
