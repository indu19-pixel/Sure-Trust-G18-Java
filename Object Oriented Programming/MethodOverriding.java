

class Animal {
    void speak() {
        System.out.println("Animal makes a sound.");
    }
}


class Dog extends Animal {
    @Override
    void speak() {
        System.out.println("Dog barks.");
    }
}

public class MethodOverriding {
    public static void main(String[] args) {
        Animal myAnimal = new Animal();
        myAnimal.speak(); 

        Dog myDog = new Dog();
        myDog.speak(); 
    }
}
