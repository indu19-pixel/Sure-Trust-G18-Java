//Program on operators

import java.util.*;

public class OperartorArithmetic {

    public static void main(String args[]){

       Scanner sc=new Scanner(System.in);
       System.out.println("Enter a: ");
       int a=sc.nextInt();
       System.out.println("Enter b: ");
       int b=sc.nextInt();

       int res1=a+b;                                    // Addition
       System.out.println("Addition of a+b = "+res1);

       int res2=a-b;                                   // Subtraction
       System.out.println("Subtraction of a-b = "+res2);

       int res3=a*b;                                   // Multiplication
       System.out.println("Multiplication of a*b = "+res3);

       int res4=a/b;                                   //Division(quotient)
       System.out.println("Division of a/b = "+res4);

       int res5=a%b;                                   //Modulus(remainder)
       System.out.println("Modulus of a%b = "+res5);

        
    }
}

