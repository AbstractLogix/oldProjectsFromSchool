//An instance of this class specializes a Bit-String of exactly 8 Binary-Digits
//
//Inherited Methods
// All public methods of the Bit-String class
//
//Specialized Methods
// zeroExtension()   : create a Word instance with identical unsigned integer value
// signExtension()   : create a Word instance with identical signed integer value
//
public class Byte extends BitString
{
   //Class Constant
   public static final int WIDTH = 8;  //# of bits in 1 Byte
   
   //Constructor: Initialize a new Byte with int value 0
   public Byte()
   {
      super(WIDTH);
   }
   
   //Constructor: Initialize a new Byte with a given int value
   public Byte(int value)
   {
      super(WIDTH, value);
   }
   
   //Constructor: Initialize a new Byte from a given String image
   public Byte(String image)
   {
      super(WIDTH, image);
   }
   
   //Return the Word formed by zero-extending this Byte
   public Word zeroExtension()
   {
      return new Word(0);
   }
   
   //Return the Word formed by sign-extending this Byte
   public Word signExtension()
   {
      return new Word(0);
   }
}