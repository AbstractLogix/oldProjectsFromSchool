package lab9Package;
import java.util.ArrayList;

public class CheckArrayList {
	public static void main(String[] arg)
	{
		ArrayList list = new ArrayList();
		
		//display(list);
		list.add("A");
		list.add(0,"B");
		list.add(1, "C");
		list.add(new Integer(25));
		//display(list);
		
		list.add("A");
		list.add(0,"B");
		list.add(1, "C");
		list.add(new Product("Chair", 249.99));
		
		//display(list);

		list.remove(1);
		list.add("D");
		list.get(2);
		//display(list);
		
		Integer i = new Integer(25);
		list.add(i);
		double x = 3.75;
		list.add(x);
		char p = 'a';
		list.add(p);
		display(list);
	}
	
	static void display(ArrayList list)
	{
		System.out.println("The size of the list is " + list.size());
		System.out.println("The list is empty " + list.isEmpty());
		System.out.println();
		
		for (int i = 0; i < list.size(); i++)
		{
			Object o = list.get(i);
			if (o instanceof String)	
				System.out.println("This object " + (String)o + " is a string " );
			else if (o instanceof Integer)	
				System.out.println("This object " + (Integer)o + " is an integer " );
			else if (o instanceof Double)	
				System.out.println("This object " + (Double)o + " is an double " );
			else if (o instanceof Character)	
				System.out.println("This object " + (Character)o + " is an character " );
			else if (o instanceof Product)	
				System.out.println("This object " + " is an Product " + ((Product)o).getName());
			
		}	
	}
}
