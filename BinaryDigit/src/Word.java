//An instance of this class specializes a Bit-String of exactly 16 Binary-Digits
//
//Inherited Methods
// All public methods of the Bit-String class
//
//Specialized Methods
// loByte()   : create a Byte instance identical to the lower 8 Binary-Digits
// hiByte()   : create a Byte instance identical to the higher 8 Binary-Digits
//
public class Word extends BitString
{
    //Class Constant
    public static final int WIDTH = 16; //# of bits in a Word

    //Constructor: Initialize a new Word with int value 0
    public Word()
    {
        super(WIDTH);
    }

    //Constructor: Initialize a new Word with a given int value
    public Word(int value)
    {
        super(WIDTH, value);
    }

    //Constructor: Initialize a new Word from an image of the Word
    public Word(String image)
    {
        super(WIDTH, image);
    }

    //Returns the (signed) int value of this Word
    // when interpreted as a signed integer
    public int signedValue()
    {
        return 0;
    }

    //Return a Byte that is a copy of the low byte of this Word
    public Byte loByte()
    {
        return null;
    }

    //Return a Byte that is a copy of the high byte of this Word
    public Byte hiByte()
    {
        return null;
    }
}