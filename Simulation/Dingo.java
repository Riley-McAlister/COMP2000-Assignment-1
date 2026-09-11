package Simulation;

import java.awt.Color;

public class Dingo extends Animal {

    public Dingo() {
        setBackground(new Color(240, 126, 38));
    }

    public void wander(grid g) {
        super.wander(g);
        update();
        ageAnimal();
        hungry();

        breed(g);
        // to wander costs energy
        if (energy > 0) {
            energy -= 35;
        }
        // dingo dies of exhaustion if energy < 0.
        if (energy <= 0) {
            g.death(this);
            System.out.println("Dingo died of exhaustion");
        }
        // dingo dies of old age if age > maxAge
        if (age > maxAge) {
            g.death(this);
            System.out.println("Dingo died of old age");
        }
        // Dingo dies of hunger if hunger <= 0;
        if (hunger <= 0) {
            g.death(this);
            System.out.println("Dingo died of hunger");
        }
        // if hunger < maxHunger Dingo hunts for food
        if (hunger < maxHunger) {
            hunt(g);
        }
    }

    public void hunt(grid g) {
        // if Dingo is within sensoryDistance of kangaroo, hunt is successful, kangaroo
        // dies and
        // Dingo eats, gaining hunger points from KangarooFoodValue.
        Container<Animal> animals = g.animals;
        for (int i = animals.size() - 1; i >= 0; i--) {
            if (animals.get(i) instanceof Kangaroo) {
                if (super.dist(this, animals.get(i)) < SensoryDistance) {
                    eat();
                    System.out.println("Hunt Successful");
                    System.out.println("Kangaroo was eaten");
                    g.death(animals.get(i));
                    break;

                }
            }
        }
    }
}
