package Prototype;

import java.awt.Color;

public class Dingo extends Animal {
    int BREED = 180;
    int ENERGY_LOSS = 20;
    int energy = 5;
    int alive;
    int RANGE = 50;

    
    public Dingo() {
        setBackground(new Color(255,0,0));
    }
    public void move(grid g) {
        super.move(g);
        alive += 1;
        
        if (alive % BREED == 0) {
            g.newDingo(this.x,this.y);
        }
        if (alive % ENERGY_LOSS == 0) {
            energy -= 1;

            if (energy == 0) {
                g.death(this);
            }
        }
        hunt(g);

    }
    public void hunt(grid g) {
        Container<Animal> Animals = g.Animals;
        for (int i = Animals.size()-1; i >=0;i--) {
            if (Animals.get(i)instanceof Kangaroo) {
                if (super.dist(this, Animals.get(i))< RANGE) {
                    energy += 4;
                    g.death(Animals.get(i));
                }
            }
        }
    }
}
