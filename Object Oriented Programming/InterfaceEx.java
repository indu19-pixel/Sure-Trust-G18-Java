
interface Flyable {
    void fly();
}


interface Swimable {
    void swim();
}


class Bird implements Flyable, Swimable {
    public void fly() {
        System.out.println("Bird can fly.");
    }
    public void swim() {
        System.out.println("Bird can also swim.");
    }
}


public class InterfaceEx {
    public static void main(String[] args) {
        Bird myBird = new Bird();
        myBird.fly();
        myBird.swim();
    }
}
