import java.util.LinkedList;
import java.util.Collections;
import java.util.Iterator;

public class LinkedListOperations {
    public static void main(String[] args) {
        LinkedList<String> fruits = new LinkedList<>();

        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Cherry");
        fruits.add("Mango");

        System.out.println("LinkedList: " + fruits);

        fruits.add(1, "Grapes");
        fruits.addFirst("Pineapple");
        fruits.addLast("Orange"); 
        System.out.println("After additions: " + fruits);

   
        System.out.println("First Element: " + fruits.getFirst());
        System.out.println("Last Element: " + fruits.getLast());

        fruits.set(2, "Strawberry");
        System.out.println("After update: " + fruits);

       
        fruits.remove("Banana");
        fruits.removeFirst();
        fruits.removeLast();
        System.out.println("After removals: " + fruits);

    
        System.out.println("Contains Mango? " + fruits.contains("Mango"));

     
        System.out.println("Size of LinkedList: " + fruits.size());

      
        System.out.println("Using for-each loop:");
        for (String fruit : fruits) {
            System.out.println(fruit);
        }

        Collections.sort(fruits);
        System.out.println("Sorted LinkedList: " + fruits);


        Collections.reverse(fruits);
        System.out.println("Reversed LinkedList: " + fruits);

        fruits.clear();
        System.out.println("Is LinkedList empty? " + fruits.isEmpty());
    }
}
