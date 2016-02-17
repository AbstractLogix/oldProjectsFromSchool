/**Class BinaryDigit Public Interface
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

        Defined Static Method:
        BinaryDigit bitValue(int decimalDigit)
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

    //Returns BinaryDigit.ZRO when parameter DecimalDigit == 0
    //Returns BinaryDigit.ONE when parameter DecimalDigit == 1
    //Throws an exception when the value of parameter decimalDigit
    //                                      is other than 0 or 1
    public static BinaryDigit bitValue(int decimalDigit)
    {
        return BinaryDigit.values()[decimalDigit];
    }
}