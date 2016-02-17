//An instance of this class represents a Bit-String, a sequence of Binary Digits
//
//Class-Typical methods
// Constructors: create and initialize a new Bit-String instance
// Accessors   : return information on the state of a Bit-String instance
// Imagers     : return printable images of a Bit-String instance
//
//Some methods of the class implement typical operations on Bit-Strings
// magnitude() : interpretetion as an unsigned integer
// extension() : growing a Bit-String by appending another Binary-Digit
// complement(): 1's complement or bit-inversion of each Binary-Digit
// increment() : modifying a Bit-String so that its magnitude increases by 1
// negative()  : forming the 2's complement of a Bit-String
//
//Other methods manipulate an individual Binary-Digit of the Bit-String
// set()       : force a selected Binary-Digit to 1
// clear()     : force a selected Binary-Digit to 0
// complement(): invert a selected Binary-Digit (0 to 1, 1 to 0)
// assign()    : force a selected Binary-Digit to a given value
//
//**************** THIS CLASS HAS NO MUTATOR METHODS ****************
//Methods may return new instances derived from the invoking instance
// but leave the invoking instance (this) unchanged
//*******************************************************************
//
public class BitString
{
    //Class constants for the minimum and maximum lengths of any BitString
    public static final int MIN_LENGTH = 1;   //minimum length
    public static final int MAX_LENGTH = 32;  //maximum length

    //Instance Variable
    private BinaryDigit[] bits;   //bits[0] is least significant

    //CONSTRUCTORS ***********************************************************

    //Initializes a BitString of a given length with int value 0
    public BitString(int length)
    {
        this.bits = new BinaryDigit[length];
        for (int k = 0; k < this.bits.length; k++)
            this.bits[k] = BinaryDigit.ZRO;
    }

    //Initializes a BitString of a given length with a given int value
    public BitString(int length, int value)
    {
        this(length);
        int binaryDigit;
        int k = length - 1;

        while(k > 0)
        {
            binaryDigit = value % 2;
            value = value / 2;
            this.bits[k] = BinaryDigit.bitValue(binaryDigit);
            k--;
        }
        if ( value != 0 )
            throw new RuntimeException("Unsigned Overflow: " + value);
    }

    //Initializes a BitString of the given (parameter) length
    // from a given (parameter) image of '0' and '1' chars
    public BitString(int length, String image)
    {
        this(length);

        int mostSig = image.indexOf('1');
        image = image.substring(mostSig).trim();
        if (image.length() > length)
            throw new RuntimeException("Unsigned Overflow: " + image);

        int b = 0;
        for (int i = image.length()-1; i >= 0; i--)
        {
            int digit = Integer.parseInt(image.substring(i, i + 1));
            this.bits[b] = BinaryDigit.bitValue( digit );
            b++;
        }
    }

    //ACCESSORS **************************************************************

    //Returns an array of all BinaryDigits of this BitString in the same order
    public BinaryDigit[] getBits()
    {
        return this.copy().bits;
    }

    //Returns the BinaryDigit of this BitString at a given index (parameter)
    public BinaryDigit getBit(int index)
    {
        return this.bits[index];
    }

    //Returns the number of BinaryDigits of this BitString
    public int length()
    {
        return this.bits.length;
    }

    //Returns a new BitString that is an exact copy of this BitString
    public BitString copy()
    {
        return new BitString(this.length(), this.image());
    }

    //BIT-STRING MANIPULATION ************************************************

    //Returns the unsigned integer interpretation of this BitString
    public int magnitude()
    {
        int value = 0;
        for(int k = this.length() - 1; k >= 0; k--)
        {
            value = value * 2 + this.bits[k].intValue();
        }
        return value;
    }

    //Returns a new BitString obtained by adding an additional BinaryDigit
    // to the high end of a copy of this BitString
    //Parameter extraBit provides the additional BinaryDigit to be added
    public BitString extension(BinaryDigit extraBit)
    {
        return new BitString(this.length() + 1, extraBit + this.image());
    }

    //Returns a new BitString whose BinaryDigits are the complements of
    // the BinaryDigits of this BitString
    public BitString complement()
    {
        BitString result = this.copy();
        for(int i = 0; i < result.length(); i++)
        {
            result.bits[i] = result.bits[i].inverse();
        }
        return result;
    }

    //Returns a new BitString with the same length as this BitString,
    // and whose magnitude is 1 more than that of this BitString
    public BitString increment()
    {
        BitString result = this.copy();

        int i = 0;
        do {
            result.bits[i] = result.bits[i].inverse();
            i++;
        } while (i < result.length() && result.bits[i -1] == BinaryDigit.ZRO);

        return result;
    }

    //Returns a new BitString with the same length as this BitString,
    // and whose value is the 2's-complement of this BitString
    public BitString negative()
    {
        return this.complement().increment();
    }

    //BIT MANIPULATION *******************************************************

    //Returns a new BitString that is identical to this BitString
    // except that the bit at position index is BinaryDigit.ONE
    public BitString set(int index)
    {
        return this.assign(index,BinaryDigit.ONE);
    }

    //Returns a new BitString that is identical to this BitString
    // except that the bit at position index is BinaryDigit.ZRO
    public BitString clear(int index)
    {
        return this.assign(index, BinaryDigit.ZRO);
    }

    //Returns a new BitString that is identical to this BitString
    // except that the bit at position index is the complement of
    // the bit at position index of this BitString
    public BitString complement(int index)
    {
        return assign(index,this.bits[index].inverse());
    }

    //Returns a new BitString that is identical to this BitString
    // except that the bit at position index has been replaced by
    // the given (parameter) BinaryDigit digit
    public BitString assign(int index, BinaryDigit digit)
    {
        BitString result = this.copy();
        result.bits[index] = digit;
        return result;
    }

    //IMAGES *****************************************************************

    //Returns an exact image of this BitString
    public String image()
    {
        String image = "";
        for(int k = 0; k < bits.length; k++)
        {
            image = bits[k] + image;
        }
        return image.trim();
    }

    //Override
    //Returns an image of this BitString with groups of 4 separated by a space
    public String toString()
    {
        String image = this.image();
        for(int sp = image.length() - 4; sp > 0; sp -=4)
        {
            image = image.substring(0, sp) + " " + image.substring(sp);
        }
        return image.trim();
    }
}