import java.util.Scanner;
class IOExceptionEx { 
  
    public static void main(String[] args) 
    { 
  
        Scanner scan = new Scanner("Hello Java!"); 
 
        System.out.println("" + scan.nextLine()); 
  
        System.out.println("Exception Output: "+ scan.ioException()); 
  
        scan.close(); 
    } 
}