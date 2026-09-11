package Prototype;

import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;

public class PanelDemo {

    public static void main(String[] args) {

        Frame f = new Frame("Predator Prey Simulation");

        f.setSize(600, 600);
        f.setLayout(null);
        f.setBackground(new Color(163, 46, 7));

        Container<Animal> animals = new Container<>();
        Container<Vegetation> vegetation = new Container<>();
       

        grid g = new grid();
        g.set(animals, vegetation, f);

        // Create the panels
        for(int i =0; i < 200; i++){
            double x = (int) (Math.random() * 600);
            double y = (int) (Math.random() * 600);
            g.newVegetation(x,y);
        }
        for (int i = 0; i < 5; i++) {
            double x = (int) (Math.random() * 600);
            double y = (int) (Math.random() * 600);
            g.newDingo(x, y);
        }
        for (int i = 0; i < 20; i++) {
            double x = (int) (Math.random() * 600);
            double y = (int) (Math.random() * 600);
            g.newKangaroo(x, y);
        }

        
        f.setVisible(true);

        int delay = 20;

        Timer timer = new Timer(delay, new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                try {
                    int i = animals.size();
                    // for (int i = Animals.size()-1; i >= 0; i--) {
                    while (i > 0) {
                        i--;

                        // Wrap around right edge
                        if (animals.get(i).x >= f.getWidth() || animals.get(i).x < 1) {
                            animals.get(i).moveX *= -1;
                        }

                        // Wrap around bottom
                        if (animals.get(i).y >= f.getHeight() || animals.get(i).y < 1) {
                            animals.get(i).moveY *= -1;
                        }
                        animals.get(i).wander(g);
                        

                    }

                    g.growVegetation();
                } catch (IndexOutOfBoundsException i) {
                    System.out.println(i);
                    System.out.println(animals.size());
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