import java.text.NumberFormat; // currency type


public class TestEmployee 
{

	public static void main(String[] arg)
	{
		NumberFormat nf = NumberFormat.getCurrencyInstance();

		// Employee objects
		Employee e1 = new Employee("John Smith",4000,500);
		Employee e2 = new Employee("Jane Smith",5000,1500);	
		
		
		// Heading
		System.out.println("\tABC Company Payroll");
		System.out.println();
		System.out.println("----------- Employee ---------");
		
		// Body
		System.out.println("Employee name: " + e1.getName());
		System.out.println("Salary before bonus: " + nf.format(e1.getSalary()));
		System.out.println("Bonus amount: " + nf.format(e1.getBonus()));
		System.out.println("Salary after bonus: " + nf.format(e1.salaryAfter()));
		
		System.out.println();
		System.out.println("----------- Employee ---------");
		
		System.out.println("Employee name: " + e2.getName());
		System.out.println("Salary before bonus: " + nf.format(e2.getSalary()));
		System.out.println("Bonus amount: " + nf.format(e2.getBonus()));
		System.out.println("Salary after bonus: " + nf.format(e2.salaryAfter()));
		
		// Conclusion
		System.out.println("------------ End of Report-----");
	}
}