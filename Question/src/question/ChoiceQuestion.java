
package question;

import java.util.ArrayList;

/**
 *
 * @author Oscar Miranda
 * multiple choice question subclass
 */
public class ChoiceQuestion extends Question
{
    private ArrayList<String> choices = new ArrayList<>();
    /**
     * adds choices to the question
     * @param choice the multiple choices.
     */
    public void addChoice(String choice)
    {
        choices.add(choice);
    }
    
    /**
     * overrides the display method and displays the answer choices.
     */
    @Override
    public void display()
    {
        super.display();
        for(int i = 0; i < choices.size(); i++)
        {
            int choiceNumber = i + 1;
            System.out.println(choiceNumber + ": " + choices.get(i));
        }
    }
    
}
