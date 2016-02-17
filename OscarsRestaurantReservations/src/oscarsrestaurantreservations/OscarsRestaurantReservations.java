/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oscarsrestaurantreservations;

import java.util.Scanner;

/**
 * Driver class excuse the ugliness
 * @author Oscar Miranda
 */
public class OscarsRestaurantReservations
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {

        OscarsRestaurantReservations res = new OscarsRestaurantReservations();
        res.userInput();
     
    }
    
    public void userInput()
    {
        LinkedList newList = new LinkedList();
        LinkedListIteratorInter itr = newList.listIterator();
        RestaurantQueue queue = new RestaurantQueue();
        boolean exit = false;
        while(!exit)
        {
            System.out.println("\n--------------------------------------\n");
            System.out.print("Please select one of the following options: \n" + "1. Make reservation\n"
                    + "2. Create walk-in\n" + "3. Seat next walk-in customer\n" + "4. Quit\n >>");

            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            int choice = 0;
            try
            {
                choice = Integer.parseInt(input);
            }
            catch(NumberFormatException e)
            {
                System.out.println("Please enter a whole number!");
            }

            switch(choice)
            {
                case 1:
                    System.out.println("Please enter party name ");
                    String inputName = in.nextLine();
                    System.out.println("Enter reservation time (military time)");
                    int inputTime = in.nextInt();               
                    Reservation res = new Reservation(inputName, inputTime);
                    itr.makeReservation(res);
                    newList.display();
                    break;
                case 2:
                    System.out.println("Enter walk-in first name ");
                    String first = in.nextLine();
                    System.out.println("enter walk-in last name ");
                    String last = in.nextLine();
                    WalkIn walkIN = new WalkIn(first, last);
                    queue.addWalkin(walkIN);
                    break;
                case 3:
                    queue.fillEmptyTable(queue.remove());
                    System.out.println("Next customer in Queue: " + queue.peek());
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    break;
            }        
        }             
    }  
}
