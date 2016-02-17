package newpackage;
import java.text.NumberFormat;
public class Petrol {

	char gas;
	String type;
	double amount, cost;
	String free;

	static final float REGULAR_GAS =2.99f;
	static final float PREMIUM_GAS =3.15f;
	static final float SUPER_GAS =3.25f;
	
	static final String REGULAR_WASH="You get free wash excluding under carriage and wheels";
	static final String PREMIUM_WASH="You get free wash excluding under carriage";
	static final String SUPER_WASH="You get free super wash";	
	NumberFormat nf=NumberFormat.getCurrencyInstance();
Petrol(char ch, double a)
{
	gas = ch;
	amount = a;
}
void calculateCost()
{
	switch(gas)
	{
	case 'r':
	case 'R':
		type = "Regular";
		cost= amount* REGULAR_GAS;
		if(amount > 10)
			free=REGULAR_WASH;
	break;
	case 'p':
	case 'P':
		type="Premium";
		cost=amount*PREMIUM_GAS;
		if(amount > 10)
			free=PREMIUM_WASH;
	break;
	case 's':
	case 'S':
		type="Super";
		cost=amount*SUPER_GAS;
		if(amount > 10)
			free=SUPER_WASH;
		break;
		default:
			type = "unknown";
			break;
	}
}
	public String toString()
	{
		if(type.equals("Unknown"))
			return "Petrol is" +type+"cannot be served";
		else
			return "the type of petrol that will be served is" + type
					+"\nYour bill is: $" + nf.format(cost)+"\n"+free;
	}
}
