/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package coin;

/**
 *
 * @author omiranda
 */
public class CoinTosser {
	public static void main(String[] args) {
		Coin c = new Coin();
		final int FLIPS = 50;
                int numOfHeads = 0;
                int numOfTails = 0;
		for (int i = 1; i <= FLIPS; i++) {
			boolean n = c.toss();
			if (n == true) {
				numOfHeads++;
				System.out.println("Heads");
			} else {
				numOfTails++;
				System.out.println("Tails");
			}
		}
		System.out.println();
		System.out.println("Number of heads " + numOfHeads);
		System.out.println("Number of tails " + numOfTails);
	}
}