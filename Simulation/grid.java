package Simulation;



import java.awt.*;



public class grid {

    Container<Animal> animals;

    Container<Vegetation> vegetation;



    Frame f;



    int VegetationTimer = 0;

    int VegetationGrowthTime = 1;



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



    public void eaten(Vegetation v) {

        // vegetation is eaten and is removed

        vegetation.remove(v);

        f.remove(v);

    }



    public void growVegetation() {

        // vegetation is regenerated

        VegetationTimer++;



        if (VegetationTimer >= VegetationGrowthTime) {

            double x = Math.random() * 590;

            double y = Math.random() * 590;

            newVegetation(x, y);

            VegetationTimer = 0;

        }

    }

} 

