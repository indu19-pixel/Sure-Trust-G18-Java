import java.util.Scanner;
public class AreaOfSquare {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);

        System.out.println("Enter side: ");
        float side=sc.nextFloat();
       
        float area=side*side;
        System.out.println("Area of Square: "+area);
    }
}
