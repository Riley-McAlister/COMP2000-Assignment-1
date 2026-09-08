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
        Dingo foo = new Dingo();
        foo.create(x,y);
        this.f.add(foo);
        this.Animals.add(foo);
    }

    public void newKangaroo(double x, double y){
        Kangaroo foo = new Kangaroo();
        foo.create(x,y);
        this.f.add(foo);
        this.Animals.add(foo);
    }

    public void death(Animal a) {
        Animals.remove(a);
        f.remove(a);
    }
}
