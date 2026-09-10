package Prototype;

import java.awt.Color;

public class Dingo extends Animal {
    int BREED = 200;
    int ENERGY_LOSS = 20;
    

    
    public Dingo() {
        setBackground(new Color(255,0,0));
    }
    public void wander(grid g) {
        super.wander(g);
       alive += 1;
       age +=1;
        
        if (alive % BREED == 0) {
            g.newDingo(this.x,this.y);
        }
        if (alive % ENERGY_LOSS == 0) {
            energy -= 1;

            if (energy == 0 || age > maxAge) {
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
                    energy += 20;
                    g.death(Animals.get(i));
                }
            }
        }
    }
}
