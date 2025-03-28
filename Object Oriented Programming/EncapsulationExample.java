
class Person {
    
    private String name;
    private int age;

    
    public String getName() {
        return name;
    }


    public void setName(String newName) {
        this.name = newName;
    }
    public int getAge() {
        return age;
    }

    public void setAge(int newAge) {
        if (newAge > 0) { 
            this.age = newAge;
        } else {
            System.out.println("Age must be positive.");
        }
    }
}

public class EncapsulationExample {
    public static void main(String[] args) {
        Person person = new Person();

        
        person.setName("John Doe");
        person.setAge(25);

        
        System.out.println("Name: " + person.getName());
        System.out.println("Age: " + person.getAge());
    }
}
