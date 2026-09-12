package Simulation;

import java.awt.*;
import javax.swing.*;

public class Simulation {
    public static void main(String[] args) {
        JFrame f = new JFrame("Advanced Predator-Prey Simulation");
        f.setSize(600, 600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel canvas = new JPanel() {
            @Override
            protected void paintComponent(Graphics graphics) {
                super.paintComponent(graphics);
                graphics.setColor(new Color(139, 69, 19)); 
                graphics.fillRect(0, 0, getWidth(), getHeight());
                graphics.setColor(Color.WHITE);
                graphics.drawString("Simulation Status: Active", 20, 20);
            }
        };
        canvas.setLayout(null);
        f.setContentPane(canvas);

        Container<Animal> animals = new Container<>();
        Container<Vegetation> vegetation = new Container<>();

        grid g = new grid(); 
        g.set(animals, vegetation, f);

        for (int i = 0; i < 300; i++) {
            g.newVegetation(Math.random() * 600, Math.random() * 600);
        }
        for (int i = 0; i < 2; i++) {
            g.newDingo(Math.random() * 600, Math.random() * 600);
        }
        for (int i = 0; i < 10; i++) {
            g.newKangaroo(Math.random() * 600, Math.random() * 600);
        }

        f.setVisible(true);

        Timer timer = new Timer(20, e -> {
            try {
                int i = animals.size();
                while (i > 0) {
                    i--;
                    Animal current = animals.get(i);
                    if (current.x >= f.getWidth() || current.x < 1) current.moveX *= -1;
                    if (current.y >= f.getHeight() || current.y < 1) current.moveY *= -1;
                    current.wander(g);
                }
                g.growVegetation();
                f.repaint();
            } catch (IndexOutOfBoundsException ex) {
                System.out.println("Handled concurrent modification: " + ex.getMessage());
            }
        });
        timer.start();
    }
}
