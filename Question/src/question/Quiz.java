
package question;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Oscar Miranda
 */
public class Quiz
{
    static int count = 0;
    static int wrongCount = 0;
    static int total;
    static ArrayList<Question> myQuiz = new ArrayList<>();

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        Quiz quiz1 = new Quiz();
        quiz1.createQuestions();
        quiz1.presentQuestions(myQuiz);
       
        /**
         * main : 
         * {
         *      Quiz myQuiz = newQuiz();
         *      myQuiz.createQuestions();
         *      myQuiz.admnisterQuestions();
         *      myQuiz.displaySummary();
         * {
         * 
         * 
         * public void createQuestions(){
         * }
         * 
         * public void counter(){   }
         * 
         * 
         */
        //create questions into array list
        
        
        
        // administers questions // ex. myQuiz.get(i).display();
        // iterate arraylist and display
        // if correct++ incorrect++
        // summarize 
        /*
        // regular question using super class
        Question q = new Question();
        q.setText("Who was the inventor of Java?");
        q.setAnswer("james gosling");        
        q.display();
        System.out.print("Your answer: ");
        String response = in.nextLine().toLowerCase();
        printAnswerResponse(q.checkAnswer(response));
        
        Question q2 = new Question();
        q2.setText("Who is one of the Authors of the C programming language?");
        q2.setAnswer("dennis ritchie");
        q2.display();
        System.out.print("Your answer: ");
        String response2 = in.nextLine().toLowerCase();
        printAnswerResponse(q2.checkAnswer(response2));
        
        presentQuestion(first);
        presentQuestion(second);
        

        
        presentTFQuestion(firstTF);
        presentTFQuestion(secondTF);
        presentTFQuestion(thirdTF);
        
        
        presentFITBQuestion(fITB1);
        presentFITBQuestion(fITB2);
        presentFITBQuestion(fITB3);
        
        System.out.println("Total Correct: " + count);
        System.out.println("Total incorrect: " + wrongCount);
        total = count + wrongCount;
        System.out.println("total questions answered = " + total);
        */

    }

    public void createQuestions()
    {

        //fill in the blank 
        Fill_In_The_Blank fITB1 = new Fill_In_The_Blank();
        fITB1.setText("Ubuntu is a Linux _____.");
        fITB1.setAnswer("distribution");
        myQuiz.add(fITB1);
        
        Fill_In_The_Blank fITB2 = new Fill_In_The_Blank();
        fITB2.setText("Linux is sometimes refered to as ___/linux.");
        fITB2.setAnswer("gnu");
        myQuiz.add(fITB2);

        
        Fill_In_The_Blank fITB3 = new Fill_In_The_Blank();
        fITB3.setText("Donald Knuth is a ________ scientist.");
        fITB3.setAnswer("computer");
        myQuiz.add(fITB3);

        //true / false question
        
        TrueFalseQuestion firstTF = new TrueFalseQuestion();
        firstTF.setText("Linus Torvalds the creator of the Linux Kernel.");
        firstTF.setAnswer("true");
        myQuiz.add(firstTF);
        
        TrueFalseQuestion secondTF = new TrueFalseQuestion();
        secondTF.setText("The sky is blue.");
        secondTF.setAnswer("true");
        myQuiz.add(secondTF);
        
        TrueFalseQuestion thirdTF = new TrueFalseQuestion();
        thirdTF.setText("Linux is an operating system.");
        thirdTF.setAnswer("true");
        myQuiz.add(thirdTF);

        
        //multiple choice question
        ChoiceQuestion first = new ChoiceQuestion();
        first.setText("What was the orginal name of the Java language?");
        first.addChoice("*7");
        first.addChoice("Duke");
        first.addChoice("Oak");
        first.addChoice("Gosling");
        first.setAnswer("3");
        myQuiz.add(first);
        
        ChoiceQuestion second = new ChoiceQuestion();
        second.setText("In which country was the inventor of Java born?");
        second.addChoice("Australia");
        second.addChoice("Canada");
        second.addChoice("Denmark");
        second.addChoice("United States");
        second.setAnswer("2");
        myQuiz.add(second);
    }
    public void presentQuestions(ArrayList myQuiz)
    {
        for(int i = 0; i < myQuiz.size(); i++){
            myQuiz.get(i);
            System.out.print("Your answer(input number): ");
            Scanner in = new Scanner(System.in);
            String response = in.nextLine();
            //printAnswerResponse(myQuiz.checkAnswer(response));
        }
    }

    /**
     * Presents a question to the user and checks the response.
     * @param q the question
     */
    /*
    public static void presentQuestion(ChoiceQuestion q)
    {
        q.display();
        System.out.print("Your answer(input number): ");
        Scanner in = new Scanner(System.in);
        String response = in.nextLine();
        printAnswerResponse(q.checkAnswer(response));
    }
    public static void presentTFQuestion(TrueFalseQuestion t)
    {
        t.display();
        System.out.print("True or False? ");
        Scanner in = new Scanner(System.in);
        String response = in.nextLine().toLowerCase();
        printAnswerResponse(t.checkAnswer(response));
    }
    public static void presentFITBQuestion(Fill_In_The_Blank t)
    {
        t.display();
        System.out.print("Fill in the blank ");
        Scanner in = new Scanner(System.in);
        String response = in.nextLine().toLowerCase();
        printAnswerResponse(t.checkAnswer(response));
    }
    */    
    public static void printAnswerResponse(boolean isValidAnswer)
    {
        if(isValidAnswer) 
        {
            System.out.println("correct");
            count++;
        }
        else 
        {
            System.out.println("incorrect");
            wrongCount++;
        }
    }
}
