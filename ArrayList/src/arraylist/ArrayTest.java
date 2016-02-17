/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arraylist;

/**
 *
 * @author omiranda
 */
public class ArrayTest
{
    public static void main(String[] args) {
        
        ArrayList arrayList1 = new ArrayList();
        for(int i = 0; i < 4000; i++){
        Object oks = new Object();
        arrayList1.addLast(oks);
        }
        System.out.println(arrayList1.size());
    }
}
