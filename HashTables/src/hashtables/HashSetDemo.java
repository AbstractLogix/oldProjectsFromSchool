package hashtables;
import java.util.Iterator;
/**
 *
 * @author omiranda
 */
public class HashSetDemo
{
   public static void main(String[] args) {
       HashTables names = new HashTables(101);
       
       names.add("Harry");
       names.add("Sue");
       names.add("Nina");
       names.add("Susannah");
       names.add("Larry");
       names.add("Eve");
       names.remove("Sue");
       names.remove("Larry");
       
       Iterator iter = names.iterator();
       while(iter.hasNext())
       {
           System.out.println(iter.next());
       }
   }
}
