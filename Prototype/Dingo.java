package Prototype;

import java.awt.Color;

public class Dingo extends Animal {

    public Dingo() {
        setBackground(new Color(240, 126, 38));
    }

    public void wander(grid g) {
        super.wander(g);
        update();
        ageAnimal();

        breed(g);

        if (energy > 0) {
            energy -= 35;
        }
        if (energy < 0 || age > maxAge) {
            g.death(this);
        }
        hunt(g);
    }

    public void hunt(grid g) {
        Container<Animal> animals = g.animals;
        for (int i = animals.size() - 1; i >= 0; i--) {
            if (animals.get(i) instanceof Kangaroo) {
                if (super.dist(this, animals.get(i)) < SensoryDistance) {
                    energy += foodValue;
                    System.out.println("Hunt Successful");
                    g.death(animals.get(i));
                    break;
                    
                } 
            }
        }
    }
}
