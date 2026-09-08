package Prototype;

import java.awt.*;

public class Animal extends Panel {

    double mx;
    double my;

    double x;
    double y;

    public Animal() {
        setBounds((int)x, (int)y, 10, 10);

    }

    public void create(double xx, double yy) {
        x = xx;
        y = yy;
        // Random number between 0.5 and 1.5
        my = Math.random() + 0.5;

        // Make the total speed equal to 2
        mx = Math.sqrt(4 - Math.pow(my, 2));

        int t = ((int)Math.ceil(Math.random()*4));
        if (t < 3) {mx *= -1;}
        if (t%2 == 0) {my *=-1;}
    }

    public void move(grid a) {

        x += mx;
        y += my;
        
        setBounds((int)x, (int)y, 25, 25);
    }

    public double dist(Animal A, Animal B) {
        double dx = B.x - A.x;
        double dy = B.y - A.y;

        return Math.sqrt(dx * dx + dy * dy);
    }
}