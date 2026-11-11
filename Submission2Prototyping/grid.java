package Submission2Prototyping;

import java.awt.*;
import java.util.Map;
import java.util.stream.Collectors;

public class grid {

    Container<Animal> animals;
    Container<Vegetation> vegetation;
    Frame f;

    int VegetationTimer = 0;
    int VegetationGrowthTime = 1;
    int kangaroosEaten = 0;

    public <T> void set(Container<Animal> i, Container<Vegetation> v, Frame o) {

        animals = i;
        vegetation = v;
        f = o;
    }

    public void newDingo(double x, double y) {

        // create new dingo
        Dingo d = new Dingo();

        d.create(x, y);
        this.f.add(d);
        this.animals.add(d);
    }

    public void newKangaroo(double x, double y) {
        // create new kangaroo
        Kangaroo k = new Kangaroo();

        k.create(x, y);
        this.f.add(k);
        this.animals.add(k);
    }

    public void newVegetation(double x, double y) {
        // create new vegetation
        Vegetation v = new Vegetation(x, y);

        this.f.add(v);
        this.vegetation.add(v);
    }

    public void death(Animal a) {
        // Animal dies and is removed
        animals.remove(a);
        f.remove(a);
    }

    public void checkBounds(Animal a) {
        if (a.x + a.Animal_Size >= f.getWidth() || a.x < 1) {
            a.moveX *= -1;
        }
        if (a.y + 2 * a.Animal_Size >= f.getHeight() || a.y < 1) {
            a.moveY *= -1;
        }
    }

    public void eaten(Vegetation v) {
        // vegetation is eaten and is removed
        vegetation.remove(v);
        f.remove(v);
    }

    public void growVegetation() {
        // vegetation is regenerated
        VegetationTimer++;

        if (VegetationTimer >= VegetationGrowthTime) {

            double x = Math.random() * 790 + 5;
            double y = Math.random() * 500 + 90;

            newVegetation(x, y);
            VegetationTimer = 0;
        }
    }

    Container<CauseOfDeath> deathRecords = new Container<>();

    public double deathPercentage(CauseOfDeath cause) {
        if (deathRecords.size() == 0) {
            return 0;
        }

        return deathRecords.stream().filter(death -> death == cause).count() * 100.0 / deathRecords.size();
    }

    public long getKangarooCount() {
        return animals.stream().filter(animal -> animal instanceof Kangaroo).count();
    }

    public long GetDingoCOunt() {
        return animals.stream().filter(animal -> animal instanceof Dingo).count();
    }

    public long getVegetationCount() {
        return vegetation.stream().count();
    }

    public int getKangaroosEaten() {
        return kangaroosEaten;
    }

    public Map<CauseOfDeath, Long> getDeathsByCause() {
        return deathRecords.stream().collect(Collectors.groupingBy(cause -> cause, Collectors.counting()));
    }

    public Map<String, Map<String, Double>> getAverageStats() {
        return animals.stream()
                .collect(Collectors.groupingBy(animal -> animal.getClass().getSimpleName(),
                        Collectors.collectingAndThen(Collectors.toList(), group -> Map.of("Energy",
                                group.stream().collect(Collectors.averagingDouble(animal -> animal.energy)), "Hunger",
                                group.stream().collect(Collectors.averagingDouble(animal -> animal.hunger)), "Age",
                                group.stream().collect(Collectors.averagingDouble(animal -> animal.age))))));
    }
}
