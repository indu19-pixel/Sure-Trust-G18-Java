public class StaticBlockEx2 {
    static int a=50;
    static {
        System.out.println("a= "+a);
    }
    public static void main(String args[]){
        int b=100;
        System.out.println("a= "+a);
        System.out.println("b= "+b);

    }
   
}
