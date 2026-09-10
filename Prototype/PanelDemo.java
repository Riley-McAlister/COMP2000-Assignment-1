package Prototype;

import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;

public class PanelDemo {

    public static void main(String[] args) {

        Frame f = new Frame("Example");

        f.setSize(600, 600);
        f.setLayout(null);

        Container<Animal> Animals = new Container<>();

        grid g = new grid();
        g.set(Animals, f);

        // Create the panels
        for (int i = 0; i < 10; i++) {
            double x = (int)(Math.random() * 600);
            double y = (int)(Math.random() * 600);
            g.newDingo(x,y);
        }
        for (int i = 0; i < 20; i++) {
            double x = (int)(Math.random() * 600);
            double y = (int)(Math.random() * 600);
            g.newKangaroo(x,y);
        }

        f.setVisible(true);

        int delay = 20;

        Timer timer = new Timer(delay, new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                try {
                    int i=Animals.size();
                //for (int i = Animals.size()-1; i >= 0; i--) {
                    while (i >0) {
                    i--;

                    // Wrap around right edge
                    if (Animals.get(i).x >= f.getWidth() || Animals.get(i).x < 1) {
                        Animals.get(i).moveX *= -1;
                    }

                    // Wrap around bottom
                    if (Animals.get(i).y >= f.getHeight() || Animals.get(i).y < 1) {
                        Animals.get(i).moveY *= -1;
                    }
                    Animals.get(i).wander(g);
                    //i--;

                    
                }
                //g.die();
                } catch(IndexOutOfBoundsException i) {
                    System.out.println(i);
                    System.out.println(Animals.size());
                }
            }
        });

        timer.start();

        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }
}