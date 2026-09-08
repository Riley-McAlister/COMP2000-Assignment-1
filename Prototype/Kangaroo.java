package Prototype;

import java.awt.*;

public class Kangaroo extends Animal {
    int BREED = 100;
    int ENERGY_LOSS = 30;
    int energy = 7;
    int alive;
    
    public Kangaroo() {
        setBackground(new Color(0,255,0));
    }

    public void move(grid a) {
        super.move(a);
        alive += 1;
        
        if (alive % BREED==0) {
            a.newKangaroo(this.x, this.y);
        }
        if (alive % ENERGY_LOSS == 0) {
            energy -= 1;
            if (energy == 0) {

                a.death(this);
            }
        }
    }
}
