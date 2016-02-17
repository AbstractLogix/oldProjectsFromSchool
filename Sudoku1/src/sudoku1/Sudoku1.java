
package sudoku1;

/**
 *
 * @author Oscar Miranda
 * Due Date: 9-16-14
 */
public class Sudoku1
{
   public static void main(String[] args)
   {
      // Row and column Latin but with invalid subsquares
      String config1 = "1234567892345678913456789124567891235678912346" 
         + "78912345789123456891234567912345678";
      String[][] puzzle1 = makeSudoku(config1);
      if (isValidSudoku(puzzle1))
      {
         System.out.println("This puzzle is valid.");
      }
      else
      {
         System.out.println("This puzzle is invalid.");
      }
      System.out.println(getPrintableSudoku(puzzle1));
      System.out.println("--------------------------------------------------");

      // Row Latin but column not Latin and with invalid subsquares
      String config2 = "12345678912345678912345678912345678912345678"
         + "9123456789123456789123456789123456789";
      String[][] puzzle2 = makeSudoku(config2);
      if (isValidSudoku(puzzle2))
      {
         System.out.println("This puzzle is valid.");
      }
      else
      {
         System.out.println("This puzzle is invalid.");
      } 
       
      System.out.println(getPrintableSudoku(puzzle2));
      System.out.println("--------------------------------------------------"); 
       
      // A valid sudoku
      String config3 = "25813764914698532779324685147286319558149273663"
         + "9571482315728964824619573967354218";
      String[][] puzzle3 = makeSudoku(config3);
      if (isValidSudoku(puzzle3))
      {
         System.out.println("This puzzle is valid.");
      }
      else
      {
         System.out.println("This puzzle is invalid.");
      } 
      System.out.println(getPrintableSudoku(puzzle3));
      System.out.println("--------------------------------------------------"); 

   }
   
   // makes sudoku puzzle; instantiates x which is a 2D string and uses a for
   // loop to generate a 9 x 9 grid.
   public static String[][] makeSudoku(String s)
   {
      int SIZE = 9;
      int k = 0;
      String[][] x = new String[SIZE][SIZE];
      for (int i = 0; i < SIZE; i++)
      {
         for (int j = 0; j < SIZE; j++)
         {
            x[i][j] = s.substring(k, k + 1);
            k++;
         }
      }
      return x;
   }

   public static String getPrintableSudoku(String[][] x)
   {
      int SIZE = 9;
      String temp = "";
      for (int i = 0; i < SIZE; i++)
      {
         if ((i == 3) || (i == 6))
         {
            temp = temp + "=================\n";
         }
         for (int j = 0; j < SIZE; j++)
         {
            if ((j == 3) || (j == 6))
            {
               temp = temp + " || ";
            }
            temp = temp + x[i][j];
         }
         temp = temp + "\n";
      }
      return temp;
   }

   public static boolean isValidSudoku(String[][] x)
   {
      return rowsAreLatin(x) && colsAreLatin(x) && goodSubsquares(x);
   }

   public static boolean rowsAreLatin(String[][] x)
   {
      
      for(int i = 0; i < x.length; i++)
      {
          if(!isLatin(x[i])) return false; 
      }
      
      return true;
   }

   public static boolean isLatin(String[] x)
   {
       for(int i = 0; i < x.length;i++)
       {
           for(int j = i+1; j < x.length; j++)
           {
               if(i != j && x[i].equals(x[j])) return false;
           }
       }
       
       return true;
   }

   public static boolean colsAreLatin(String[][] x)
   {
        for(int i = 0; i < x.length; i++)
        {
            String[] thisColumn = new String[x[i].length];
            for(int j = 0; j < x[0].length; j++) 
            {
                thisColumn[j] = x[j][i];
            }
            if(!isLatin(thisColumn)) return false; 
        }
      
      return true;
   }   
 
   public static boolean goodSubsquares(String[][] x)
   {
      int multiplier = 3;
      for(int i = 0; i < x.length / 3; i++)
      {
          for(int j = 0; j < x[i].length / 3; j++)
          {
              if(!goodSubsquare(x, i * multiplier, j * multiplier)) return false;
          }
      }
      
      return true;
   }

   public static boolean goodSubsquare(String[][] x, int i, int j)
   {
        String[] block = new String[x[i].length];
        
        for(int k = 0; k < block.length; k++)
        {
            block[k] = x[(k / 3) + i][(k % 3) + j];
        }
        return isLatin(block);
   }
   
}
