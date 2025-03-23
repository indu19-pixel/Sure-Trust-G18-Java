
class Animal {
    Animal() {
        System.out.println("Animal constructor called.");
    }
    void eat() {
        System.out.println("Animal is eating.");
    }
}


class Dog extends Animal {
    Dog() {
        super(); 
        System.out.println("Dog constructor called.");
    }
    void display() {
        super.eat(); 
        System.out.println("Dog is eating too.");
    }
}


public class SuperKeywordExample {
    public static void main(String[] args) {
        Dog myDog = new Dog();
        myDog.display();
    }
}
