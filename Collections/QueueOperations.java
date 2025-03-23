import java.util.*;

public class QueueOperations {
    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();

    
        queue.offer(10);
        queue.offer(20);
        queue.offer(30);
        System.out.println("Queue: " + queue);

        System.out.println("Front element (peek): " + queue.peek());


        System.out.println("Removed (poll): " + queue.poll());
        System.out.println("Queue after removal: " + queue);

        System.out.println("Is queue empty? " + queue.isEmpty());


        System.out.println("Size: " + queue.size());

        System.out.print("Elements: ");
        for (int num : queue) {
            System.out.print(num + " ");
        }
    }
}
