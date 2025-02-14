import java.util.Scanner;
public class AreaOfRect {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter length: ");
        float length=sc.nextFloat();
        System.out.println("Enter Width: ");
        float width=sc.nextFloat();
        float area=length*width;
        System.out.println("Area of Rectangle: "+area);
    }
}
