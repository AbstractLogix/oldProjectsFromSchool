// CDA 3103   Fall 2014
// Assignment#1 Bit-Strings Test Program
// Norman Pestaina
import java.util.*;
public class BitStringTest
{
    public static void main(String[] args)
    {
        bitStringTest( new BitString(8, 103) );

        dataTest( new Byte("01100100"), new Byte("11001010") );
    }

    private static void bitStringTest(BitString bits)
    {
        System.out.println("\nBitString Test " + bits);

        display("Bit String", bits);

        BitString test = new BitString(bits.length(), bits.image());
        display("Increment",  bits.increment());
        display("Complement", bits.complement());
        display("Negative",   bits.negative());

        display("Manipulation", test);
        test = test.clear(7).clear(6);
        display("Clear 7, 6", test);
        test = test.set(5).set(4);
        display("Set 5, 4", test);
        test = test.complement(3).complement(2);
        display("Invert 3, 2", test);
        test = test.assign(1, test.getBit(0).inverse());
        display("[1] = ~[0]", test);
    }

    private static void dataTest(Byte a, Byte b)
    {
        byteTest(a);
        byteTest(b);

        Word w = new Word(a.image() + b.image());
        wordTest(w);
    }

    private static void byteTest(Byte byt)
    {
        System.out.println("\nByte Test " + byt);
        display("Data Byte", byt);
        display("Zero_Extend", byt.zeroExtension());
        display("Sign_Extend", byt.signExtension());
    }

    private static void wordTest(Word wrd)
    {
        System.out.println("\nWord Test " + wrd);
        display("Word Data", wrd);
        display("Low Byte",  wrd.loByte());
        display("High Byte", wrd.hiByte());
        integerTest(wrd);
        integerTest(new Word( wrd.negative().image() ));
    }

    private static final int FIELD_WIDTH = 12;

    private static void integerTest(Word wrd)
    {
        System.out.println("\nInteger Test " + wrd + "\n" +
                field("Unsigned",FIELD_WIDTH) + " : " + wrd.magnitude() + "\n" +
                field("Signed",FIELD_WIDTH) + " : " + wrd.signedValue() );
    }

    private static void display(String legend, BitString str)
    {
        System.out.println(field(legend,FIELD_WIDTH) + " : " + str + " : " +
                Arrays.toString(str.getBits()) );
    }

    private static String field(String item, int fieldWidth)
    {
        String str = "" + item;
        while ( str.length() < fieldWidth )
            str = " " + str;
        return str;
    }

}