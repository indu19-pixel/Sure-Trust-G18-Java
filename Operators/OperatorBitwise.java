//Program on operators

import java.util.*;

public class OperatorBitwise {

    public static void main(String args[]){

       Scanner sc=new Scanner(System.in);
       System.out.println("Enter a: ");
       int a=sc.nextInt();
       System.out.println("Enter b: ");
       int b=sc.nextInt();

       int res1=a&b;
       System.out.println("Bitwise And = "+res1);
       int res2=a|b;
       System.out.println("Bitwise Or = "+res2);
       int res3=~a;
       System.out.println("Bitwise Not = "+res3);
       int res4=a^b;
       System.out.println("Bitwise Xor= "+res4);
       int res5=a<<b;
       System.out.println("Bitwise leftshift = "+res5);
       int res6=a>>b;
       System.out.println("Bitwise rightshift = "+res6);

    
    }
}
