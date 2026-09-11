package Prototype;

import java.awt.*;

public class Animal extends Panel {

    double moveX;
    double moveY;

    double x;
    double y;

    double energy = 100;
    double restEnergy = 50;
    double maxEnergy = 100;

    double speed = energy / 20;
    double maxSpeed = maxEnergy / 20;

    int age;

    int maxAge = 200;
    int update;
    int SensoryDistance = 50;

    double breedEnergy = 60;
    int breedAge = 30;
    long breedCooldown = 15000;
    long lastBreedTime = 0;

    int foodValue = 20;
    int forageDist = 50;

    

    public Animal() {
        setBounds((int) x, (int) y, 10, 10);

    }

    public void create(double initX, double initY) {
        // create new object at initial X position and initial Y Position
        x = initX;
        y = initY;

        // Set initial speed - random speed between 0 and maxSpeed
        moveY = Math.random() * speed;

        // Make the total speed equal to 2
        moveX = Math.random() * speed;
        // Math.sqrt(4 - Math.pow(moveY, 2));

        // Four different directions, if math.random returns a 1 or a 2, moveX is
        // negated
        // if math.random returns 2 or 4, moveY is negated
        int direction = ((int) Math.ceil(Math.random() * 4));
        if (direction == 1 || direction == 2) {
            moveX *= -1;
        }
        if (direction == 2 || direction == 4) {
            moveY *= -1;
        }
    }

    public void wander(grid a) {
        // animal wanders around, direction determined in create

        // if energy < restEnergy, forces animal to rest to regain energy.
        if (energy <= restEnergy) {
            rest(a);
            return;
        }
        x += moveX;
        y += moveY;

        setBounds((int) x, (int) y, 25, 25);
    }

    public double dist(Animal A, Animal B) {
        // returns distance between animals A and B.
        double dx = B.x - A.x;
        double dy = B.y - A.y;

        return Math.sqrt(dx * dx + dy * dy);
    }

    public void rest(grid a) {
        // if energy <= maxEnergy animal rests to restore energy.
        if (energy <= maxEnergy) {
            energy = maxEnergy;
        }
    }

    public int ageAnimal() {
        age += 1;
        return age;
    }

    public int update() {
        update += 1;
        return update;
    }

    public void breed(grid g) {
        
         if(canBreed()){

                    if(this instanceof Kangaroo){
                        g.newKangaroo(x, y);
                        System.out.println("New Kangaroo");
                    }
                    if(this instanceof Dingo){
                        g.newDingo(x,y);
                        System.out.println("New Dingo");
                    }

            energy -= 20;
            lastBreedTime = System.currentTimeMillis();
        }
    }
    public boolean canBreed(){
        long currentTime = System.currentTimeMillis();
        return age >= breedAge &&
                energy >= breedEnergy &&
                currentTime - lastBreedTime >= breedCooldown;
                    
    } 
}


