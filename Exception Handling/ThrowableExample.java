public class ThrowableExample {
    public static void main(String[] args) {
        try {
            int result = divide(10, 0); 
            System.out.println("Result: " + result);
        } catch (Throwable t) { 
            System.out.println("Caught an issue: " + t);
        }
    }

    public static int divide(int a, int b) {
        return a / b; 
    }
}

