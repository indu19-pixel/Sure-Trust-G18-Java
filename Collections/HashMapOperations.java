import java.util.HashMap;
import java.util.Map;

public class HashMapOperations {
    public static void main(String[] args) {
        
        HashMap<Integer, String> map = new HashMap<>();

       
        map.put(1, "Apple");
        map.put(2, "Banana");
        map.put(3, "Cherry");
        map.put(4, "Mango");

        
        System.out.println("HashMap: " + map);

      
        System.out.println("Value of key 2: " + map.get(2));

    
        System.out.println("Contains key 3? " + map.containsKey(3));
        System.out.println("Contains value 'Apple'? " + map.containsValue("Apple"));

        
        map.put(1, "Pineapple");
        map.replace(3, "Strawberry");
        System.out.println("After update: " + map);

       
        map.remove(2);
        map.remove(4, "Mango"); 
        System.out.println("After removals: " + map);

       
        System.out.println("Size of HashMap: " + map.size());

        
        System.out.println("Using for-each (entrySet):");
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        System.out.println("Keys: " + map.keySet());
        System.out.println("Values: " + map.values());

        map.clear();
        System.out.println("Is HashMap empty? " + map.isEmpty());
    }
}
