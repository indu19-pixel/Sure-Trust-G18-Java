import java.util.Vector;
import java.util.Collections;
import java.util.Iterator;

public class VectorOperations {
    public static void main(String[] args) {
        
        Vector<String> fruits = new Vector<>();

        // Adding elements
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Cherry");
        fruits.add("Mango");

      
        System.out.println("Vector: " + fruits);

        
        fruits.add(1, "Grapes");
        fruits.insertElementAt("Pineapple", 0);
        System.out.println("After additions: " + fruits);

        System.out.println("First Element: " + fruits.firstElement());
        System.out.println("Last Element: " + fruits.lastElement());

       
        fruits.set(2, "Strawberry");
        System.out.println("After update: " + fruits);

        
        fruits.remove("Banana");
        fruits.removeElementAt(0);
        fruits.remove(fruits.size() - 1);
        System.out.println("After removals: " + fruits);


        System.out.println("Contains Mango? " + fruits.contains("Mango"));

       
        System.out.println("Size of Vector: " + fruits.size());

        
        System.out.println("Using for-each loop:");
        for (String fruit : fruits) {
            System.out.println(fruit);
        }

        
        Collections.sort(fruits);
        System.out.println("Sorted Vector: " + fruits);

       
        Collections.reverse(fruits);
        System.out.println("Reversed Vector: " + fruits);

       
        fruits.clear();
        System.out.println("Is Vector empty? " + fruits.isEmpty());
    }
}
