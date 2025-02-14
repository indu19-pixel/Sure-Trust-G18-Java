import java.util.Scanner;
public class RevArray {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter n: ");
        int n=sc.nextInt();
        System.out.println("Enter array elements: ");
        int[] arr=new int[n];
        
        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }
        for(int i=n-1;i>=0;i--){
            System.out.println("Reverse array: "+arr[i]);
        }
        
    }
}
