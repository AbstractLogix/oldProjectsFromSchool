/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inclassexamples;
import java.util.Arrays;
/**
 *
 * @author omiranda
 */
public class InClassExamples
{


    public static void main(String[] args)
    {
        InClassExamples object = new InClassExamples();
        int[] a = {3,11,11};
        //System.out.println(makeOutWord("<<>>", "word"));
        //System.out.println(object.extraEnd("Hello"));
        //System.out.println(object.firstHalf("avdcasdfasdf"));
        //System.out.println(object.comboString("hello", "hi"));
        //System.out.println(object.theEnd("hello", false));
        //System.out.println(object.parrotTrouble(false, 21));
        String ai = Arrays.toString(object.maxEnd3(a));
        System.out.println(ai);
    }
    public static String makeOutWord(String out, String word)
    {
        String str;
        str = out.substring(0, 2);
        str = str.concat(word);
        str = str.concat(out.substring(2));
        return str;
    }
    public String extraEnd(String str)
    {
        String str1;
        str1 = str.substring(str.length()-2);
        return str1 + str1;        
    }
    public String firstHalf(String str)
    {
        int st = str.length() / 2;
        return str.substring(0, st);
    }
    public String comboString(String a, String b)
    {
        if(a.length() < b.length())
        {
            String c = a.concat(b);
            return c + a;
        }
        else {
            String c = b.concat(a);
            return c + b ;
        }
    }
    public String left2(String str)
    {
        String substring1 = str.substring(2);
        return substring1.concat(str.substring(0, 2));
    }
    public String theEnd(String str, boolean front)
    {
        if(front)
        {
            return str.substring(0, 1);
        } else {
            return str.substring(str.length()-1);
        }
    }
    public boolean endsLy(String str)
    {
        return str.substring(str.length()-2).contains("ly");            
    }
    public String nTwice(String str, int n)
    {
        return str.substring(0, n) + str.substring(str.length()-n);
    }
    public String withoutX(String str)
    {
        if(str.substring(0,1).contains("x") && str.substring(str.length()-1).contains("x"))
        {
            return str.substring(1, str.length()-2);
        }
        else if(str.substring(0, 1).contains("x"))
        {
            return str.substring(1);
        }
        else if(str.substring(str.length()-1).contains("x"))
        {
            return str.substring(0, str.length()-2);
        }
        else
        {
            return str;
        }
    }
    public boolean parrotTrouble(boolean talking, int hour)
    {        
        return talking && hour < 7 || hour > 20 && talking;           
    }
    
   public int[] maxEnd3(int[] nums) {
    int first = nums[0];
    int last = nums[nums.length-1];
        if ( first > last) { 
        int[] a = { first, first, first };
        return a;
        }
        else {
            int[] b = {last, last, last};
            return b;
            }
    }


}

