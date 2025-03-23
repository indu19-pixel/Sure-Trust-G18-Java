
interface Vehicle {
    void start(); 
}

class Car implements Vehicle {
    public void start() {
        System.out.println("Car starts with a key.");
    }
}

class Bike implements Vehicle {
    public void start() {
        System.out.println("Bike starts with a kick.");
    }
}


public class InterfacePolymorphism {
    public static void main(String[] args) {
        Vehicle myCar = new Car();
        myCar.start(); 

        Vehicle myBike = new Bike();
        myBike.start(); 
    }
}
