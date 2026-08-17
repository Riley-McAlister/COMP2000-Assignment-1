public class Animal{

    public int health;
    public int topSpeed;
    public int age;
    public int breedingRate;
    public int energy;

    //eat doubles energy, capped at 100, if energy below 0 animal dies.
    void eat(){
        energy = Math.clamp(energy*2, 0, 100);
    }
    //sleep doubles current health, capped at 100, if health below 0, animal dies.
    void Sleep() {
        health = Math.clamp(health*2, 0, 100);
    }

}

class Dingo extends Animal{
    //distance at which predator detects prey
    int scentRange;

    //object position change, rate of movement capped at topSpeed;
    void hop(){
    }
}

class Kangaroo extends Animal{
    //distance at which prey detects a predator
    int predatorDetectionRange;

    //object position change, rate of movement capped at topSpeed;
    void run(){
    }
}

