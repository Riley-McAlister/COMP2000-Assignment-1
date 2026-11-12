package Submission2;

import java.awt.*;

public class Kangaroo extends Animal {

    public Kangaroo() {
        setBackground(new Color(150, 118, 108));
        setBehaviour(new KangarooBehaviour());
        movementEnergyCost = 10;
    }

    @Override
    public void reproduce(grid g){
        g.newKangaroo(x,y);
        System.out.println("New Kangaroo");
    }

}