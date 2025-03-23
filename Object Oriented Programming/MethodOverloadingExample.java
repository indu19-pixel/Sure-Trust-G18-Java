
class MathOperations {

    int add(int a, int b) {
        return a + b;
    }

    int add(int a, int b, int c) {
        return a + b + c;
    }

    double add(double a, double b) {
        return a + b;
    }
}

public class MethodOverloadingExample {
    public static void main(String[] args) {
        MathOperations obj = new MathOperations();
        
        System.out.println("Sum (int): " + obj.add(5, 10));          
        System.out.println("Sum (3 int): " + obj.add(5, 10, 15));    
        System.out.println("Sum (double): " + obj.add(5.5, 2.5));   
    }
}
