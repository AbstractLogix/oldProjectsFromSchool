//An instance of this class represents a Bit-String, a sequence of Binary Digits
//
//Class-Typical methods
// Constructors    : create and initialize a new Bit-String instance
// Accessors       : return information on the state of a Bit-String instance
//
//**************** THIS CLASS HAS NO MUTATOR METHODS ***************************
//***************** BitString objects are IMMUTABLE  ***************************
//A method may return a new BitString derived from an invoking instance
// but always leaves the invoking instance (this) unchanged
//******************************************************************************
//
//Overrides of Object-inherited methods
// equals()        : compare BitString objects for equality
// toString()      : return a printable image of a BitString
// clone()         : return an exact copy of a BitString
//
//Typical operations on a Bit-String
// extension()     : create a Bit-String with one additional Binary-Digit
// increment()     : create a Bit-String whose unsigned value is 1 more 
// onesComplement(): create the 1's complement of a BitString
// twosComplement(): create the 2's complement of a BitString
//
//Bit-manipulation of a Bit-String
// assign()        : force a selected Binary-Digit to a given value (ZRO or ONE)
// set()           : force a selected Binary-Digit to ONE
// clear()         : force a selected Binary-Digit to ZRO
// complement()    : invert a selected Binary-Digit (ZRO to ONE, ONE to ZRO)
//
public class BitString implements Cloneable
{
   //Class constant
   public static final int MIN_LENGTH = 1;   //minimum length of any BitString
   
   //Instance Variable - A sequence of BinaryDigit's to represent an integer
   private BinaryDigit[] bits;   //bits[0] is the least significant digit
   

   //CONSTRUCTORS **************************************************************
   
   //Initialize a BitString of given length with int value 0
   // :: parameter length :: the required number of BinaryDigits
   public BitString(int length)
   {
      if (length < MIN_LENGTH )
         throw new RuntimeException( length + " Invalid length" );
         
      this.bits = new BinaryDigit[length];
      for (int k = 0; k < this.bits.length; k++)
         this.bits[k] = BinaryDigit.ZRO;
   }
   
   //Initialize a BitString of given length with a given unsigned int value
   // :: parameter length :: the required number of BinaryDigits
   // :: parameter value  :: the required unsigned integer value
   //Exception thrown if the number of digits exceeds the given length
   public BitString(int length, int value)
   {
      this(length);
      
      if ( value < 0 )
         throw new RuntimeException( value + " Unsigned value expected" );
         
      //Missing Implementation
      int binaryDigit;
      int k = length -1;
      while(k > 0)
      {
          binaryDigit = value % 2;
          value = value / 2;
          this.bits[k] = BinaryDigit.bitValue(binaryDigit);
      }
      //Test for unsigned overflow      
      if ( value != 0 )
         throw new RuntimeException("Unsigned Overflow: " + value);
   }

   //Initialize a BitString of given length to match a given String image 
   // :: parameter length :: the required number of BinaryDigits
   // :: parameter image  :: required value as a String of '0' & '1' chars
   //Exception thrown if the length of image exceeds the given length
   public BitString(int length, String image)
   {
      this(length);
      
      if (image.length() > length)
         throw new RuntimeException("Unsigned Overflow: " + image);
      /*
      !!!!!!!!!!!!!!!! revise !!!!!!!!!!!!!!
      */
      //Missing Implementation
      int mostSig = image.indexOf('1');
      image = image.substring(mostSig).trim();
      
      int b = 0;
      for(int i = image.length() - 1; i >= 0; i--)
      {
          int digit = Integer.parseInt(image.substring(i, i + 1));
          this.bits[b] = BinaryDigit.bitValue(digit);
          b++;
      }
   }
   
   //ACCESSORS *****************************************************************

   //Return the number of BinaryDigits of this BitString
   public int length()
   {
      return this.bits.length;
   }
   
   //Return the BinaryDigit of this BitString at a given position 
   // :: parameter index :: the position of the required BinaryDigit
   public BinaryDigit getBit(int index)
   {
      return this.bits[index];
   }
   
   //Return the most significant BinaryDigit of this BitString
   public BinaryDigit highBit()
   {
      return this.bits[this.length()-1];
      
      /// reviseee ---1--1!!!!!!!!!!!!!!!!!!!!!
   }
   
   //Return an array of all BinaryDigits of this BitString in the same order
   public BinaryDigit[] getBits()
   {
      BinaryDigit[] i = this.bits.clone();
      
      return i;
      // reviseeseeeeeee!!!!!!!!!
   }

   //Return the String image (0's & 1's) of this BitString
   public String image()
   {
       String image = "";
       for (BinaryDigit bit : bits)
       {
           image = bit + image;
       }
       return image.trim();
   }   
  
   //Return the unsigned integer interpretation of this BitString
   public int value()
   {
      return 0;
   }

   //OVERRIDE methods inherited from class Object*******************************
   
   //Return an image of this BitString with groups of 4 separated by 1 space  
   //Examples: "0 0100",  "0110 1001",  "10 1100 0101"
   public String toString()
   {
      return "";
   }
   
   //Return true if this BitString is identical to another given Object
   // otherwise return false
   // :: parameter other :: the Object to be compared with this BitString
   public boolean equals(Object other)
   {
      if (other == null || other.getClass() != this.getClass())
         return false;
         
      BitString that = (BitString)other;

      //Missing Implementation
            
      return true;
   }
    
   //Return a new Object that is an exact copy of this BitString
   public Object clone()
   {
      try
      {
         BitString twin = (BitString)super.clone();
         twin.bits = this.getBits();
         return twin;
      }
      catch (CloneNotSupportedException cnse)
      {
         return null;
      }
   }

   //BIT-STRING MANIPULATION ***************************************************

   //Return a new BitString obtained by adding an additional BinaryDigit 
   // to the high end of a copy of this BitString
   // :: parameter extraBit :: the additional BinaryDigit to be added
   public BitString extension(BinaryDigit extraBit)
   {
      return new BitString(this.length() + 1, extraBit + this.image());
   }
   
   //Return a new BitString with 
   // --- the same length as this BitString,
   // --- unsigned integer value 1 more than this BitString
   public BitString increment()
   {
       BitString result = (BitString)this.clone();
       for(int i = 0; i < result.length(); i++)
       {
           result.bits[i] = result.bits[i].inverse();
       }
       return result;
   }
   
   //Return a new BitString with
   // --- the same length as this BitString,
   // --- BinaryDigits forming the 1's Complement of this BitString
   public BitString onesComplement()
   {
      return this;
   }

   //Return a new BitString with
   // --- the same length as this BitString,
   // --- BinaryDigits forming the 2's Complement of this BitString
   public BitString twosComplement()
   {
      return this;
   }

   //BIT MANIPULATION **********************************************************  
   
   //Return a new BitString that is identical to this BitString except that 
   // the BinaryDigit at a given index has been replaced by a given BinaryDigit
   // :: parameter index :: the index of the digit to be replaced
   // :: parameter digit :: the replacement digit   
   public BitString assign(int index, BinaryDigit digit)
   {
       BitString j = (BitString)this.clone();
       j.bits[index] = digit;
       return j;
   }
   
   //Return a new BitString that is identical to this BitString except that 
   // the BinaryDigit at a given index has been replaced by BinaryDigit.ONE
   // :: parameter index :: the index of the digit to be replaced
   public BitString set(int index)
   {
      return this.assign(index, BinaryDigit.ONE);
   }
   
   //Return a new BitString that is identical to this BitString except that 
   // the BinaryDigit at a given index has been replaced by BinaryDigit.ZRO
   // :: parameter index :: the index of the digit to be replaced
   public BitString clear(int index)
   {
      return this.assign(index, BinaryDigit.ZRO);
   }
   
   //Return a new BitString that is identical to this BitString except that 
   // the BinaryDigit at position index has been replaced by its inverse
   // :: parameter index :: the index of the digit to be inverted
   public BitString complement(int index)
   {
       return assign(index, this.bits[index].inverse());
   }
}