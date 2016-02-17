
package question;

import java.util.Scanner;

/**
 *
 * @author Oscar Miranda
 * Super class that is a question with an answer.
 */
public class Question
{
    private String text;
    private String answer;
    
    /**
     * constructs an empty question.
     */
    
    public Question()
    {
        text = "";
        answer = "";
    }
    /**
     * sets question text
     * @param questionText the text of this question
     */
    public void setText(String questionText)
    {
        text = questionText;
    }
    /**
     * sets the answer to this question
     * @param correctResponse the answer
     */
    public void setAnswer(String correctResponse)
    {
        answer = correctResponse;
    }
    /**
     * checks if the response is correct
     * @param response the response to check
     * @return true if the response was correct false if otherwise
     */
    
    public boolean checkAnswer(String response)
    {
        return response.equals(answer);
    }
    /**
     * displays question
     */
    public void display()
    {
        System.out.println(text);
    }
}
