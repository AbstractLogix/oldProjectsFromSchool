
public class Employee {
	private String employeeName;
	private double salaryBefore;
	private double bonusAmount;

public Employee(String name, double salary, double bonus)
{ 
	employeeName=name;
	salaryBefore=salary;
	bonusAmount=bonus;
}

public String getName()
{
	return employeeName;
}
public double getSalary() 
{
	return salaryBefore;
}
public double getBonus()
{
	return bonusAmount;
}
public double salaryAfter()
{
	return salaryBefore + bonusAmount;
}

}