/*
* waddappp
*/
import java.util.Scanner;
import java.util.*;
public class Animal
{
    
    public static final double FAVNUMBER = 1.6299;
    
    private String name;    
    private int weight;
    private boolean hasOwner = false;
    private byte age;
    private long uniqueID;
    private char favoriteChar;
    private double speed;
    private float height;
    
    protected static int numberOfAnimals = 0;
    
    static Scanner userInput = new Scanner(System.in);
    
    public Animal()
    {
        numberOfAnimals++;
        //Math example other arithmetic is possible / - % *
        int sumOfNumbers = 5 + 1;
        System.out.println("5 + 1 = " + sumOfNumbers);
        
        System.out.print("Enter the name: \n");
        
        
        if(userInput.hasNextLine())
        {
            this.setName(userInput.nextLine());
        }
        
        this.setFavoriteChar();
        this.setUniqueID();
    }
    
    public static void main(String[] args) 
    {
        Animal theAnimal = new Animal();
        
        int[] favoriteNumber = new int[20];
        favoriteNumber[0] = 100;
        
        String[] stringArray = {"random", "wrods", "here"};
        
        for(String word : stringArray)
        {
            System.out.println(word);
        }
        
        String[][][] arrayName = {{{"000"}, {"100"}, {"200"}}};
        
        for(int i = 0; i < arrayName.length; i++)
        {
            for(int j = 0; j < arrayName[i].length; j++)
            {
                for(int k = 0; k < arrayName[i][j].length; k++)
                {
                    System.out.print("| " + arrayName[i][j][k] + " ");
                }
            }
            System.out.println("|");
        }
        
        String[] cloneOfArray = Arrays.copyOf(stringArray, 3);
        System.out.println(Arrays.toString(cloneOfArray));
        System.out.println(Arrays.binarySearch(cloneOfArray, "Random"));
    }

    protected static void countTo(int startingNumber)
    {
        for(int i = startingNumber; i <= 100; i++)
        {
            if(i == 90) continue;
            
            System.out.println(i);
        }
    }
    protected static String printNumbers(int maxNumbers)
    {
        int i = 1;
        while(i < (maxNumbers / 2))
        {
            System.out.println(i);
            i++;
            
            if(i == (maxNumbers / 2 )) break;
        }
        Animal.countTo(maxNumbers / 2);
         return "End of printNumbers";
    }
    
    protected static void guessMyNumber()
    {
        int number;
        do
        {
            System.out.println("Guess Number up to 100");
            
            while(!userInput.hasNextInt())
            {
                String numberEntered = userInput.next();
                System.out.printf("%s is not a number\n", numberEntered);                               
            }
            
            number = userInput.nextInt();
            
        } while(number != 50);
    }
    
    public String makeSound()
    {
        return "Grrr";
    }
    
    public static void speakAnimal(Animal randAnimal)
    {
        System.out.println("Animal says " + randAnimal.makeSound());
    }
    
    public void setName(String name)
    {
        this.name = name;
    }

    public void setFavoriteChar(char favoriteChar)
    {
        this.favoriteChar = favoriteChar;
    }
    
    public void setFavoriteChar()
    {
        int randomNumber = (int) (Math.random() * 126) + 1;
        
        this.favoriteChar = (char) randomNumber;
        
        if(randomNumber == 32)
        {
            System.out.println("Favorite character set to Space");
        } else if(randomNumber == 10) {
            System.out.println("Favorite character set to Newline");
        } else {
            System.out.println("Favorite character set to " + this.favoriteChar);
        }
        
        if((randomNumber > 97) && (randomNumber < 122))
        {
            System.out.println("Favorite character is a lowercase");
        }
        
        if(((randomNumber > 97) && (randomNumber < 122)) || ((randomNumber > 64) && (randomNumber < 91)))
        {
            System.out.println("Favorite character is a letter");
        }
        // turnary operator. If bool is true you get 50 if not you get value.
        int whichIsBigger = (50 > randomNumber) ? 50 : randomNumber;
        
        switch(randomNumber)
        {
            case 8:
                System.out.println("Favorite character set to backspace");
                break;
            case 10:
            case 11:
            case 12:
                System.out.println("Blah blah");
                break;
            default: 
                System.out.println("you done goof'd");
                break;
        }
    }

    public void setUniqueID(Long uniqueID)
    {
        this.uniqueID = uniqueID;
        System.out.println("Unique ID set to: " + this.uniqueID);
    }
    
    public void setUniqueID()
    {
        long minNumber = 1;
        long maxNumber = 1000000;
        
        this.uniqueID = minNumber + (long) (Math.random() * ((maxNumber - minNumber) + 1));
        String stringNumber = Long.toString(maxNumber);
        
        int numberString = Integer.parseInt(stringNumber);
        System.out.println("Unique ID set to: " + this.uniqueID);
    }
    
}
