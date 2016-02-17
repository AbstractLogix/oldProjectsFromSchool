/*
    2-dimentional arrays
 */

package inclass4;

public class InClass4 {


    public static void main(String[] args) {
        
        /** if not like this 
        int [][] scores2 = new int [7][3];
        scores2 [0][0] = 1;
        would have to do this 21 times; better to 
        fill and initialize at once.
        */
        
        
        
        int [][] scores = 
        {
            {1,0,1},
            {1,1,0},
            {0,0,1},
            {1,0,0},
            {0,1,1},
            {0,1,1},
            {1,1,0}
        };
        String[] countries = 
        {
            "Canada","China","Germany","Korea",
            "Japan","Russia","United States"
        };
        
        //raged array
        String[][] pyramid =
        {
            {"red"},
            {"blue", "green"},
            {"orange", "purple","pink"},
            {"brown","black","yellow","salmon"}
        };
        
        for(int r = 0; r < pyramid.length; r++)
        {
            for(int c = 0; c < pyramid[r].length; c++)
            {
                System.out.print(pyramid[r] [c] + "/t");
            }
            System.out.println("");
        }
        
        
        
        
        
        System.out.println(".t/tGold/tSilver/tBronze");
        // study this logic. internesting scores[rows].length
        
        for(int rows = 0; rows < scores.length; rows++)
        {
            System.out.print(countries[rows] + "/t");
            for(int columns = 0; columns < scores[rows].length; columns++)
            {
                System.out.print("/t" + scores[rows][columns]);
            }
            System.out.println("");
        }
    }
    
}


/**
 * enhanced for loop
 *  for(string element: myArray)
 * {
 * sysout ("blah blah " + element);
 * }
 */