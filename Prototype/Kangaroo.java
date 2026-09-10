package Prototype;

import java.awt.*;

public class Kangaroo extends Animal {
    int BREED = 70;
    int ENERGY_LOSS = 30;
    
    public Kangaroo() {
        setBackground(new Color(0,255,0));
    }

    public void wander(grid a) {
        super.wander(a);
        alive += 1;
        age += 1;
        
        if (alive % BREED==0) {
            a.newKangaroo(this.x, this.y);
        }
        if (alive % ENERGY_LOSS == 0) {
            energy -= 1;
            if (energy == 0 || age == maxAge) {

                a.death(this);
            }
        }
    }
    public void flee(grid g) {
        Container<Animal> Animals = g.Animals;
            for (int i = Animals.size()-1; i >=0;i--) {
                if (Animals.get(i)instanceof Dingo) {
                    if (super.dist(this, Animals.get(i))< RANGE) {
                        speed = maxSpeed;
                }
            }
        }
    }
}
