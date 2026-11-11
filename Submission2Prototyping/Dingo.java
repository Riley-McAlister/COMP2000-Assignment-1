package Submission2Prototyping;

import java.awt.Color;

public class Dingo extends Animal {

    public Dingo() {
        setBackground(new Color(240, 126, 38));
        setBehaviour(new DingoBehaviour());
        movementEnergyCost = 35;
    }

    @Override
    public void reproduce(grid g) {
        g.newDingo(x, y);
        System.out.println("New Dingo");
    }

}
