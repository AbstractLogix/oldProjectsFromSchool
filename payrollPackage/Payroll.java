package payrollPackage;
	//fields
public class Payroll {
	private int employee_id;
	private double hours_worked;
	private double pay_rate;
	
	//constructor	
public Payroll(int id, double hours, double pay)
{
	employee_id = id;
	hours_worked = hours;
	pay_rate = pay;
}
	//accessor methods
public int getId()
{
	return employee_id;
}

public double getHours()
{
	return hours_worked;
}

public double getPay()
{
	return pay_rate;
}

public double salary()
{
	return hours_worked * pay_rate;
}


}

