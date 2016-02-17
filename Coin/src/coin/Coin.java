package coin;
import java.util.Random;
/**
 * @author Oscar Miranda
 * @version 2.0 September 5, 2014
 * This program simulates the tossing of a coin.
 * This program applies the concept of nested loops, 
 * and object construction.
 */
public class Coin {
	private Random generator;
	private boolean sides;
	/**
	 * constructs coin 
	 * 2 sides to a coin and creates an instance of the Random class.
	 */
	public Coin() {
		generator = new Random();
        }
	/**
	 * simulates toss of coin.
	 * @return returns Random boolean value true or false
         * returning ant integer value seemed superfluous because
         * there are only two possible values.
	 */
	public boolean toss() {
		return generator.nextBoolean();
	}
}