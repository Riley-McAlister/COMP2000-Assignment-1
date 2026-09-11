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

        // to wander costs energy - more efficient than dingos.
        if (energy > 0) {
            energy -= 10;
        }
        // Kangaroo dies of exhaustion if energy <= 0
        if (energy <= 0) {
            a.death(this);
            System.out.println("Kangaroo died of exhaustion");
        }
        // Kangaroo dies of old age if age > maxAge
        if (age > maxAge) {
            a.death(this);
            System.out.println("Kangaroo died of old age");
        }
        // Kangaroo dies of hunger if hunger <=0
        if (hunger <= 0) {
            System.out.println("Kangaroo died of hunger");
        }
        // Kangaroo forages for food if hunger < maxHunger
        if (hunger < maxHunger) {
            forage(a);
        }

    }

    public void flee(grid g) {
        // if kangaroo is within sensoryDistance of a dingo it changes directions to
        // flee.
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
            // if Kangaroo is within forage distance of a plant, the plant is eaten and the
            // kangaroo gains hunger points from plantFoodValue.
            if (Dist < forageDist) {
                eat();
                g.eaten(plant);
                System.out.println("Forage Successful");

                break;
            }
        }

    }
}
