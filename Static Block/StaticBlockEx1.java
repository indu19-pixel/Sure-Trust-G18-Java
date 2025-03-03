public class StaticBlockEx1 {
    static {
        System.out.println("Good Morning");
    }
    public static void main(String args[]){
        System.out.println("Good Evening");

    }
    static {
        System.out.println("Good Night");
    }
}
