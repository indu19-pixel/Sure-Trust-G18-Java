
class Animal {
    void eat() {
        System.out.println("This animal eats food.");
    }
}


class Dog extends Animal {
    void bark() {
        System.out.println("Dog barks.");
    }
}


class Cat extends Animal {
    void meow() {
        System.out.println("Cat meows.");
    }
}


public class HierarchicalInheritance {
    public static void main(String[] args) {
        Dog myDog = new Dog();
        myDog.eat();  
        myDog.bark(); 

        Cat myCat = new Cat();
        myCat.eat();  
        myCat.meow(); 
    }
}
