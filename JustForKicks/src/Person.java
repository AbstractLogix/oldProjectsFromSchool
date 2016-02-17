/**
 * Created by omiranda on 8/19/15.
 */
public class Person extends Animal {

    private String firstName, lastName;
    private int favNum;
    private boolean child;
    private int numOfLegs;

    public Person() {}
    public Person(int numOfLegs) { this.numOfLegs = numOfLegs; }

    public Person(String firstName, String lastName, int favNum) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.favNum = favNum;
    }

    public Person(String firstName, String lastName, int favNum, boolean child) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.favNum = favNum;
        this.child = child;
    }

    public String getFirstName() {
        return this.firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setFavNum(int n) {
        this.favNum = n;
    }
    public void setChild(boolean x) {
        this.child = x;
    }

    @Override
    public String toString() {
        String result = this.child != false ? this.getFullName() + "'s favorite number is: " + favNum + " Is he a child? " + child
        : this.getFullName() + "'s favorite number is: " + favNum;
        return result;
    }

    public int howManyLegs() {
        return getNumOfLegs(numOfLegs);
    }
}
