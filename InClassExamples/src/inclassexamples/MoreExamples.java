/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inclassexamples;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author omiranda
 */
public class MoreExamples
{
    public static void main(String[] args)
    {
        ArrayList<Double> values = new ArrayList<>();

        System.out.println("please enter values, q to quit:");
        Scanner in = new Scanner(System.in);
        while(in.hasNextDouble())
        {
            values.add(in.nextDouble());
        }
        double largest = values.get(0);
        for(int i = 1; i < values.size(); i++)
        {
            if(values.get(i) > largest)
            {
                largest = values.get(i);
            }
        }
        for(double element : values)
        {
            System.out.print(element);
            if(element == largest)
            {
                System.out.print(" <== largest value");
            }
            System.out.println();
        }
    }

    

}
