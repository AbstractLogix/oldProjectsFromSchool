package evaluator;
/**
   A class that can compute the value of an arithmetic expression.
*/
public class Evaluator
{
   private ExpressionTokenizer tokenizer;

   /**
      Constructs an evaluator.
      @param anExpression a string containing the expression
      to be evaluated
   */
   public Evaluator(String anExpression)
   {
      tokenizer = new ExpressionTokenizer(anExpression);
   }

   /**
      Evaluates the expression.
      @return the value of the expression.
   */
   public int getExpressionValue()
   {
      int value = getTermValue();
      boolean done = false;
      while (!done)
      {
         String next = tokenizer.peekToken();
         if ("+".equals(next) || "-".equals(next))
         {
            tokenizer.nextToken(); // Discard "+" or "-"
            int value2 = getTermValue();
            if ("+".equals(next)) { value = value + value2; }
            else { value = value - value2; }
         }
         else 
         {
            done = true;
         }
      }
      System.out.println("getExpressionValue outputs");
      System.out.println(value);
      return value;
   }

   /**
      Evaluates the next term found in the expression.
      @return the value of the term
   */
   public int getTermValue()
   {
      int value = getExponentiationValue();
      boolean done = false;
      while (!done)
      {
         String next = tokenizer.peekToken();
     
         if ("*".equals(next) || "/".equals(next) || "%".equals(next))
         {
            tokenizer.nextToken();
            int value2 = getExponentiationValue();

            if ("*".equals(next)) { value = value * value2; }
            
            else if ("/".equals(next)){ value = value / value2; }
            
            else { value = value % value2; }
         }
         else 
         {
            done = true;
         }
      }
      System.out.println("getTermValue outputs");
      System.out.println(value);
      return value;
   }

   /**
      Evaluates the next exponentiation found in the expression.
      @return the value of the exponentiation
   */
   public int getExponentiationValue()
   {
      int value = getFactorValue();
      boolean done = false;
      while (!done)
      {
         String next = tokenizer.peekToken();
     
         if ("^".equals(next))
         {
            tokenizer.nextToken();
            int value2 = getFactorValue();
            value = (int) Math.pow(1.0 * value, 1.0 * value2);
         }
         else 
         {
            done = true;
         }
      }
      System.out.println("getExponentiationValue outputs");
      System.out.println(value);
      return value;
   }

   /**
      Evaluates the next factor found in the expression.
      @return the value of the factor
   */
   public int getFactorValue()
   {
      int value;
      String next = tokenizer.peekToken();
      if ("(".equals(next))
      {
         tokenizer.nextToken(); // Discard "("
         value = getExpressionValue();
         tokenizer.nextToken(); // Discard ")"
      }
      else
      {
         value = Integer.parseInt(tokenizer.nextToken());
      }
      System.out.println("getFactorValue outputs");
      System.out.println(value);
      return value;
   }
}

