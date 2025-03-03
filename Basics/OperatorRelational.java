//Program on operators

import java.util.*;

public class OperatorRelational  {

    public static void main(String args[]){

       Scanner sc=new Scanner(System.in);
       System.out.println("Enter first number: ");
       int a=sc.nextInt();
       System.out.println("Enter second number: ");
       int b=sc.nextInt();

       if (a==b) {
        System.out.println("a Equal to b");
       }
       else if (a>b) {
        System.out.println("a Greater than b");
       }
       else if (a<b) {
        System.out.println("a Less than b");
       }
       else if (a>=b) {
        System.out.println("a Greater than or equal to b");
       }
       else if (a<=b) {
        System.out.println("a Less than or equal to b");
       }
       else {
        System.out.println("a not Equal to b");
       }

    
    }
}
