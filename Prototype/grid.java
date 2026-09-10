package Prototype;

import java.awt.*;


public class grid {
    Container<Animal> Animals;
    Frame f;
    
    public <T> void set(Container<Animal> i, Frame o){
        Animals = i;
        f = o;
    }

    public void newDingo(double x, double y){
        Dingo d = new Dingo();
        d.create(x,y);
        this.f.add(d);
        this.Animals.add(d);
    }

    public void newKangaroo(double x, double y){
        Kangaroo k = new Kangaroo();
        k.create(x,y);
        this.f.add(k);
        this.Animals.add(k);
    }

    public void death(Animal a) {
        Animals.remove(a);
        f.remove(a);
    }
}
