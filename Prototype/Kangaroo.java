package Prototype;

import java.awt.*;

public class Kangaroo extends Animal {

    public Kangaroo() {
        setBackground(new Color(150, 118, 108));
    }

    public void wander(grid a) {

        super.wander(a);
        update();
        ageAnimal();

        breed(a);

        if (energy > 0) {
            energy -= 10;
        }
        if (energy < 0 || age > maxAge) {
            a.death(this);
        }
        forage(a);

    }

    public void flee(grid g) {
        Container<Animal> animals = g.animals;
        for (int i = animals.size() - 1; i >= 0; i--) {
            if (animals.get(i) instanceof Dingo) {
                if (super.dist(this, animals.get(i)) < SensoryDistance) {
                    speed = maxSpeed;
                }
            }
        }
    }

    public void forage(grid g){
        for(int i = g.vegetation.size()-1; i >= 0; i--){
            Vegetation plant = g.vegetation.get(i);
                double Dist = Math.sqrt( Math.pow(plant.x - x, 2) + Math.pow(plant.y - y, 2));

                if(Dist < forageDist){
                    energy += foodValue;
                    g.eaten(plant);
                    System.out.println("Forage Successful");
                    
                    break;
                }
        }

    }
}
