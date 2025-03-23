import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class ArrayListOperations {
    public static void main(String[] args) {
        
        ArrayList<String> fruits = new ArrayList<>();

     
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Cherry");
        fruits.add("Mango");

      
        System.out.println("ArrayList: " + fruits);

       
        fruits.add(1, "Grapes");
        System.out.println("After adding Grapes at index 1: " + fruits);

  
        System.out.println("Element at index 2: " + fruits.get(2));

        
        fruits.set(2, "Orange");
        System.out.println("After updating index 2: " + fruits);

       
        fruits.remove("Banana");
        System.out.println("After removing Banana: " + fruits);

        
        System.out.println("Contains Mango? " + fruits.contains("Mango"));

        
        System.out.println("Size of ArrayList: " + fruits.size());

        
        System.out.println("Using for-each loop:");
        for (String fruit : fruits) {
            System.out.println(fruit);
        }

       
        Collections.sort(fruits);
        System.out.println("Sorted ArrayList: " + fruits);

        fruits.clear();
        System.out.println("Is ArrayList empty? " + fruits.isEmpty());
    }
}
