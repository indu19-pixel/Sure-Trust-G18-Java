import java.util.Scanner;
public class PrimeComposite {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter n: ");
        int n=sc.nextInt();
        int fact=0;
        for(int i=1;i<=n;i++){
            if (n%i==0){
                fact=fact+1;
            }
        }
        if (fact==2){
            System.out.println("Prime Number");
        }
        else{
            System.out.println("Composite Number");
        }
    }
}
