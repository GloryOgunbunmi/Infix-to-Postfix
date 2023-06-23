//import java.io.IOException;
import java.util.Scanner;

//testclass declaration this class includes the methods to convert
//infix notation to post-fix expression
public class testclass {
//variables declaration....
   private Stackinpost stk;
   private String ipt;
   private String opt = "";
//testclass Constructor declaration....
   public testclass(String stin) {
      ipt = stin;//variable initialization...
      int stackSize = ipt.length(); //initializing the stack length....
      stk = new Stackinpost(stackSize); //calling a instance of the testclass...
   }
//method to convert infix to postfix expression..
   public String convIntoPost() {
   //iterating over the loop based on length of the given infix string...
      for (int j = 0; j < ipt.length(); j++) {
         char ch = ipt.charAt(j);
      //switch case statements are added for choosing the operations.....
         switch (ch) {
            case '+': 
            case '-':
               opertr(ch, 1); //calling the method to re-arrange the character into stack..
               break; 
            case '*': 
            case '/':
               opertr(ch, 2); //calling the method to re-arrange the character into stack..
               break; 
            case '(': 
               stk.push(ch); //calling the method to re-arrange the character into stack..
               break;
            case ')': 
               gotParen(ch); //calling the method to re-arrange the character into stack..
               break;
            default: 
               opt = opt + ch; //assigning default value to opt
               break;
         }
      }
      while (!stk.isEmpty()) { //iterate over the loop...
         opt = opt + stk.pop();
      }
   // System.out.println(opt);
      return opt; 
   }
//this method places the characters of given string ...
   public void opertr(char opThis, int prec1) {
      while (!stk.isEmpty()) {
         char opTop = stk.pop();
         if (opTop == '(') {
            stk.push(opTop);//inserts into stack...
            break;
         }
         else {
            int prec2;
            if (opTop == '+' || opTop == '-')
               prec2 = 1;
            else
               prec2 = 2;
            if (prec2 < prec1) { 
               stk.push(opTop);//inserts into stack...
               break;
            }
            else
               opt = opt + opTop;
         }
      }
      stk.push(opThis);//inserts into stack...
   }
   public void gotParen(char ch){ //check for the parenthesis in the given expression...
      while (!stk.isEmpty()) {
         char chx = stk.pop();
         if (chx == '(') 
            break; 
         else
            opt = opt + chx; 
      }
   }
   public static void main(String[] args)/*throws IOException*/ { //main method to call all the other methods to do infix to post fix 
      String ipt = "";
      System.out.println("Enter Infix expresstion \n"); //asking for the input from user...
      Scanner sc=new Scanner(System.in);//scanner object takes input from user....
   
      ipt=sc.nextLine();
      String opt;//output which is postfix expression of the given infix notation...
      testclass testTrans = new testclass(ipt); //creating the object from the constructor 
      opt = testTrans.convIntoPost(); //calling method using constructor object ,which is created above...
      System.out.println("Postfix is " + opt + '\n'); //showing the output to the user in postfix form...
   }
   class Stackinpost {//this class helps in object creating ...
      private int maxSize;
      private char[] arraystk;
      private int top;
      public Stackinpost(int max) {
         maxSize = max;
         arraystk = new char[maxSize];
         top = -1;
      }
   //getters and setters 
      public void push(char j) { //inserting into stack....
         arraystk[++top] = j;
      }
      public char pop() { //deleting the elements from stack....
         return arraystk[top--];
      }
      public char peek() {//this removes peek element from stack....
         return arraystk[top];
      }
      public boolean isEmpty() { //this method returns isEmpty status....,
         return (top == -1);
      }
   }
}