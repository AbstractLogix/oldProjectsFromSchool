/*Class BinaryDigit Public Interface
   Values:
      BinaryDigit.ZRO, BinaryDigit.ONE

   Inherited Instance Method:
      int ordinal()
      
   Inherited Class Methods:
      static BinaryDigit value(String source)
      static BinaryDigit[] values()
     
   Defined Instance Methods:
      BinaryDigit inverse()
      int intValue()
      String toString()
   
   Defined Static Methods:
      BinaryDigit bitValue(int decimalDigit)
      BinaryDigit bitValue(char characterDigit)
      BinaryDigit bitValue(boolean booleanDigit)
*/

public enum BinaryDigit
{
   //Values
   ZRO, ONE;
   
   //Returns ZRO when this BinaryDigit is ONE, 
   //        ONE when this BinaryDigit is ZRO
   public BinaryDigit inverse()
   {
      return BinaryDigit.values()[1 - this.ordinal()];
   }
   
   //Returns 0 when this BinaryDigit is ZRO, 
   //        1 when this BinaryDigit is ONE
   public int intValue()
   {
      return this.ordinal();
   }
   
   //Returns "0" when this BinaryDigit is ZRO, 
   //        "1" when this BinaryDigit is ONE
   public String toString()
   {
      return "" + this.ordinal();
   }
   
   //Returns BinaryDigit.ZRO when parameter decimalDigit == 0
   //Returns BinaryDigit.ONE when parameter decimalDigit == 1
   //Throws an exception when the value of parameter decimalDigit
   //                                      is other than 0 or 1
   public static BinaryDigit bitValue(int decimalDigit)
   {
      return BinaryDigit.values()[decimalDigit];
   }
   
   //Returns BinaryDigit.ZRO when parameter characterDigit == '0'
   //Returns BinaryDigit.ONE when parameter characterDigit == '1'
   //Throws an exception when the value of parameter characterDigit
   //                                   is other than '0' or '1'
   public static BinaryDigit bitValue(char characterDigit)
   {
      return BinaryDigit.values()["01".indexOf(characterDigit)];
   }
   
   //Returns BinaryDigit.ZRO when parameter booleanDigit == false
   //Returns BinaryDigit.ONE when parameter booleanDigit == true
   public static BinaryDigit bitValue(boolean booleanDigit)
   {
      return (booleanDigit ? ONE : ZRO);
   }
}