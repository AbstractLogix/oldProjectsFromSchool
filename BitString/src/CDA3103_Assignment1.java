// CDA 3103   Spring 2015
// Assignment#1 Bit-Strings Test Program
// Norman Pestaina
import java.util.*;
public class CDA3103_Assignment1
{
   public static void main(String[] args)
   {      
      bitStringTest(108);
      byteWordTest( new BitString(8, "11000110") );
   }
   
   private static void bitStringTest(int number)
   {
      System.out.println("BitString Test");
      BitString testData = constructors(number);
      
      accessors( testData );
      equalsDemo( testData );
      manipulation( testData );
   }
   
   private static BitString constructors(int number)
   {
      System.out.println("Constructors");
      
      display("Zero", new BitString(8));
      
      BitString test = new BitString(8, number);
      display("Value", test);
      
      test = new BitString(test.length(), test.image());
      display("Image", test);
      
      return test;
   }   
   
   private static void accessors(BitString data)
   {
      System.out.println("Accessors");
      display("Data", data);
      System.out.println(field("length()",FIELD_WIDTH) + " : " + data.length());
      System.out.println(field("image()",FIELD_WIDTH) + " : " + data.image());
      System.out.println(field("value()",FIELD_WIDTH) + " : " + data.value());
      System.out.println(field("highBit()",FIELD_WIDTH) + " : " + data.highBit());
      System.out.println(field("getBit(2)",FIELD_WIDTH) + " : " + data.getBit(2));
   }
   
   private static void equalsDemo(BitString data)
   {
      System.out.println("Equals & Clone");
      BitString twin = (BitString)data.clone();
      System.out.println(field("data == clone",FIELD_WIDTH) + " : " + 
                         data + (data == twin ? " == " : " != ") + twin);
      System.out.println(field(".equals( )",FIELD_WIDTH) + " : " + 
                         data + (data.equals(twin) ? " " : " !") + "equals " +  twin );
   }   
   
   private static void manipulation(BitString bits)
   {
      System.out.println("BitString Manipulation");
      
      display("Data", bits);
       
      display("Increment",  bits.increment());
      display("1's Complement", bits.onesComplement());
      display("2's Complement",   bits.twosComplement());
      
      BitString data = bits.extension(BinaryDigit.ZRO).extension(BinaryDigit.ONE);
      display("Extension " + data.value(), data);
      
      System.out.println("Bit Manipulation");
      display("Clear 7, 6", bits.clear(7).clear(6));
      display("Set 5, 4", bits.set(5).set(4));
      display("Invert 3, 2", bits.complement(3).complement(2));
      display("[1] = ~[0]", bits.assign(1, bits.getBit(0).inverse())); 
   }
   
   private static void byteWordTest(BitString bits)
   {
      System.out.println("Byte & Word Test");
      Word aWord = new Word(
                   bits.extension(BinaryDigit.ZRO).extension(BinaryDigit.ONE).image());
      display("Word", aWord);
      display("Low Byte", aWord.loByte());
      display("Zero Extension", aWord.loByte().zeroExtension());
      display("Sign Extension", aWord.loByte().signExtension());  
      display("High Byte", aWord.hiByte());      
      display("Zero Extension", aWord.hiByte().zeroExtension());
      display("Sign Extension", aWord.hiByte().signExtension());          
   }
   
   private static final int FIELD_WIDTH = 15;
   
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