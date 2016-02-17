/**
 * Created by omiranda on 8/19/15.
 */
public class testClass {

    public static void main(String[] args) {

        int total = 0;

        for(int i = 0; i <= 10; i++) {
            total += i;
        }

        //System.out.println(total);


        Person Oscar = new Person(2);

        myMysteryMethod(9);

    }

    public static void myMysteryMethod (int max )
    {
        for ( int i = 1; i <= max; i++ ) {
            for ( int j = 1; j <= max; j++ ) {
                System.out.print ( String.format ( "%4d", j * i ));
            }
            System.out.println();
        }
    }
}
