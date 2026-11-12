package Submission2;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.Map;

public class Simulation {
    public static void main(String[] args) {
        JFrame f = new JFrame("Ecosystem Simulation");
        f.setSize(800, 600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(null);
        f.getContentPane().setBackground(new Color(163, 46, 7));

        Container<Animal> animals = new Container<>();
        Container<Vegetation> vegetation = new Container<>();

        grid g = new grid();

        //Set initial starting positions
        g.set(animals, vegetation, f);

        for (int i = 0; i < 300; i++) {
            double x = (int) (Math.random() * 790) + 5;
            double y = (int) (Math.random() * 500) + 90;
            ;
            g.newVegetation(x, y);
        }
        for (int i = 0; i < 2; i++) {
            double x = (int) (Math.random() * 750) + 25;
            double y = (int) (Math.random() * 485) + 90;
            g.newDingo(x, y);
        }
        for (int i = 0; i < 10; i++) {
            double x = (int) (Math.random() * 750) + 25;
            double y = (int) (Math.random() * 485) + 90;
            g.newKangaroo(x, y);
        }

        //create statistics Panel
        Panel statisticsPanel = new Panel();
        statisticsPanel.setBounds(0, 5, 800, 80);
        statisticsPanel.setBackground(new Color(50, 50, 50));
        statisticsPanel.setLayout(null);

        // First row: Population statistics
        Label populationLabel = new Label();
        populationLabel.setForeground(Color.WHITE);
        populationLabel.setBounds(0, 5, 800, 25);
        statisticsPanel.add(populationLabel);

        // Second row: Causes of Death
        Label deathLabel = new Label();
        deathLabel.setForeground(Color.WHITE);
        deathLabel.setBounds(0, 30, 800, 25);
        statisticsPanel.add(deathLabel);

        // Third Row: Average animal statistics
        Label averageStatLabel = new Label();
        averageStatLabel.setForeground(Color.WHITE);
        averageStatLabel.setBounds(0, 55, 800, 25);
        statisticsPanel.add(averageStatLabel);

        f.add(statisticsPanel);

        Timer timer = new Timer(30, e -> {

            try {
                int i = animals.size();
                while (i > 0) {
                    i--;

                    animals.get(i).wander(g);
                }
                g.growVegetation();

                populationLabel.setText("Kangaroos: " + g.getKangarooCount() +
                        " | Dingoes: " + g.GetDingoCOunt() +
                        " | Plants: " + g.getVegetationCount());

                Map<CauseOfDeath, Long> deaths = g.getDeathsByCause();
                Map<String, Map<String, Double>> averageStats = g.getAverageStats();
                Map<String, Double> kangarooStats = averageStats.getOrDefault("Kangaroo", Collections.emptyMap());
                Map<String, Double> dingoStats = averageStats.getOrDefault("Dingo", Collections.emptyMap());

                deathLabel.setText(
                        "Deaths by hunger: " + deaths.getOrDefault(CauseOfDeath.HUNGER, 0L) +
                                " | Deaths by exhaustion: " + deaths.getOrDefault(CauseOfDeath.EXHAUSTION, 0L) +
                                " | Deaths by old-age: " + deaths.getOrDefault(CauseOfDeath.OLD_AGE, 0L) +
                                " | Kangaroos Eaten: " + g.getKangaroosEaten());

                averageStatLabel.setText(
                        "Average energy - Kangaroos: "
                                + String.format("%.1f", kangarooStats.getOrDefault("Energy", 0.0)) +
                                " | Dingoes: " + String.format("%.1f", dingoStats.getOrDefault("Energy", 0.0)) +
                                " | Average hunger - Kangaroos: "
                                + String.format("%.1f", kangarooStats.getOrDefault("Hunger", 0.0)) +
                                " | Dingoes: " + String.format("%.1f", dingoStats.getOrDefault("Hunger", 0.0)) +
                                " | Average Age - Kangaroos: "
                                + String.format("%.1f", kangarooStats.getOrDefault("Age", 0.0)) +
                                " | Dingoes: " + String.format("%.1f", dingoStats.getOrDefault("Age", 0.0)));

                f.repaint();

            } catch (IndexOutOfBoundsException ex) {
                System.out.println(ex);
                System.out.println(animals.size());
            }

        });

        f.setVisible(true);
        timer.start();
    }
}
