import java.util.Scanner;
public class LinearSearch {
    
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter target: ");
        int target=sc.nextInt();
        System.out.println("Enter the size of an array: ");
        int n=sc.nextInt();
        System.out.println("Enter array Elements: ");
        int[] arr=new int[n];
        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }
        int position=linearSearch(arr,n,target);
        if(position==-1){
            System.out.println("Element not found");
        }
        else{
            System.out.println("Element found at index "+position);
        }
        

    }
    public static int linearSearch(int arr[],int n,int target) {
        for(int i=0;i<n;i++){
            if(arr[i]==target){
                return i;
            }
        }
        return -1;
    }
   
}
