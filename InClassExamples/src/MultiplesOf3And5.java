
import java.util.ArrayList;

/**
 *
 * @author omiranda
 */
public class MultiplesOf3And5
{
    public static void main(String[] args) 
    {
//        int n = 0;
//        for(int i = 0; i < 1000; i++){
//            if(i % 3 == 0 || i % 5 == 0){
//                System.out.println(i);
//                n += i;
//            }
//        }
//        System.out.println("sum of all values " + n);
        
        ArrayList<Integer> fibArray = new ArrayList();
        MultiplesOf3And5 o = new MultiplesOf3And5();
        int n = 0;
        fibArray.add(fibonacciRecursion(4000000));
        for(int i : fibArray){
            if(i % 2 == 0){
                 i += n;
            }
        }
        System.out.println(n);
    }
    
         public static int fibonacciRecursion(int number){
            if(number == 1 || number == 2){
                return 1;
            }
            int fibo1 =1, fibo2 =1, fibonacci=1;
            for(int i = 3; i <= number; i++){
                fibonacci = fibo1 + fibo2;
                fibo1 = fibo2;
                fibo2 = fibonacci;
            }
            return fibonacci;
        }
}
