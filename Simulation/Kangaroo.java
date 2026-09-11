package Simulation;

import java.awt.*;

public class Kangaroo extends Animal {

    public Kangaroo() {
        setBackground(new Color(150, 118, 108));
    }

    public void wander(grid a) {
        flee(a);
        super.wander(a);
        update();
        ageAnimal();
        hungry();

        breed(a);
        
    

        if (energy > 0) {
            energy -= 10;
        }
        if (energy <= 0) {
            a.death(this);
            System.out.println("Kangaroo died of exhaustion");
        }
        if (age > maxAge) {
            a.death(this);
            System.out.println("Kangaroo died of old age");
        }
        if (hunger <= 0) {
            System.out.println("Kangaroo died of hunger");
        }
        if (hunger < maxHunger) {
            forage(a);
        }

    }

    public void flee(grid g) {
        Container<Animal> animals = g.animals;
        for (int i = animals.size() - 1; i >= 0; i--) {
            if (animals.get(i) instanceof Dingo) {
                if (super.dist(this, animals.get(i)) < SensoryDistance) {
                    changeDirection();
                    break;
                }
            }
        }
    }

    public void forage(grid g) {
        for (int i = g.vegetation.size() - 1; i >= 0; i--) {
            Vegetation plant = g.vegetation.get(i);
            double Dist = Math.sqrt(Math.pow(plant.x - x, 2) + Math.pow(plant.y - y, 2));

            if (Dist < forageDist) {
                eat();
                g.eaten(plant);
                System.out.println("Forage Successful");

                break;
            }
        }

    }
}
