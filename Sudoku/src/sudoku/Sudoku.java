package sudoku;

/**
 *
 * @author omiranda
 */
public class Sudoku
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        String input1 = "1234567892345678913456789124567891235678912346"
         + "78912345789123456891234567912345678";
        String input2 = "12345678912345678912345678912345678912345678"
         + "9123456789123456789123456789123456789";
        String input3 = "25813764914698532779324685147286319558149273663"
         + "9571482315728964824619573967354218";
        
        //String[][] puzzle1 = makeSudoku(input1);
       // String[][] puzzle2 = makeSudoku(input2);
       // String[][] puzzle3 = makeSudoku(input3);

        //SudokuPuzzle puzzle1 = new SudokuPuzzle();
        //puzzle1.checkIfSudoku(puzzle1);
        //myPuzzle.checkIfSudoku(puzzle2);
        //myPuzzle.checkIfSudoku(puzzle3);
    }
    
    public static String[][] makeSudoku(String config){
      int SIZE = 9;
      int k = 0;
      String[][] x = new String[SIZE][SIZE];
      
      for (int i = 0; i < SIZE; i++){
         for (int j = 0; j < SIZE; j++){
            x[i][j] = config.substring(k, k + 1);
            k++;
         }
      }
      
      return x;
    }
    
    public static boolean isValidSudoku(int[][] sudoku)
    {
            // Verify the rows
            for(int row = 0; row < 9; row++)
                    if(!checkRow(sudoku, row))
                            return false;

            // Verify the columns
            for(int col = 0; col < 9; col++)
                    if(!checkCol(sudoku,col))
                            return false;

            // Verify the 3x3 subgrids
            for(int row = 0; row < 9; row += 3)
                    for(int col = 0; col < 9; col += 3)
                            if(!checkBox(sudoku,row,col))
                                    return false;

            // If all the tests pass, then the sudoku is valid
            return true;
    }
	
    // This checks the specified row to make sure that each digit
    // appears once and only once
    public static boolean checkRow(int[][] sudoku, int row)
    {
            boolean[] hit = new boolean[10];

            for(int col = 0; col < 9; col++)
            {
                    // If a number has been reused then the Sudoku is invalid
                    if(hit[sudoku[row][col]])
                            return false;

                    // Mark a number as having been used
                    hit[sudoku[row][col]] = true;
            }
            // Input is restricted in such a way that only digits 1 - 9 will appear
            // As such, if 9 digits have been seen without duplicates, then the row
            // must have all 9 digits once and only once
            return true;
    }
	
    // This checks the specified column to make sure that each digit
    // appears once and only once
    public static boolean checkCol(int[][] sudoku, int col)
    {
            boolean[] hit = new boolean[10];

            for(int row = 0; row < 9; row++)
            {
                    // If a number has been reused then the Sudoku is invalid
                    if(hit[sudoku[row][col]])
                            return false;

                    // Mark a number as having been used
                    hit[sudoku[row][col]] = true;
            }
            // Input is restricted in such a way that only digits 1 - 9 will appear
            // As such, if 9 digits have been seen without duplicates, then the
            // column must have all 9 digits once and only once
            return true;
    }
	
    // This checks the specified 3x3 sub-grid(box) to make sure that each digit
    // appears once and only once
    public static boolean checkBox(int[][] sudoku, int row, int col)
    {
        boolean[] hit = new boolean[10];

        // Loop through each cell in a box
        for(int r = row; r < row+3; r++)
        {
            for(int c = col; c < col+3; c++)
            {				
                if(hit[sudoku[r][c]])
                        return false;
                hit[sudoku[r][c]] = true;
            }
        }
        return true;
    }
    
    // will make sudoku and check it
    public void checkIfSudoku(String input){
        String [][] testSudoku = makeSudoku(input);
        
        if (isValidSudoku(testSudoku)){
            System.out.println("This puzzle is valid");
        }
        else {
            System.out.println("This puzzle is not valid");
        }
        System.out.println(getPrintableSudoku(input));
    }
    
    public String getPrintableSudoku(String [][] testSudoku){
        int SIZE = 9;
        String temp = "";
        
        for (int i = 0; i < SIZE; i++){
           if ((i == 3) || (i == 6)){
              temp = temp + "=================\n";
           }
           for (int j = 0; j < SIZE; j++){
              if ((j == 3) || (j == 6)){
                 temp = temp + " || ";
              }
              temp = temp + testSudoku[i][j];
           }
           temp = temp + "\n";
        }
        return temp;
    }
}
