package Prototype;

import java.awt.*;

public class Animal extends Panel {

    double moveX;
    double moveY;

    double x;
    double y;

    double energy = 100;
    double restEnergy = 50;
    double maxEnergy = 100;

   
    double speed = energy / 30;
    double maxSpeed = maxEnergy/30;

    int age;
    int maxAge = 200; 
    int alive;
    int RANGE = 50;

    public Animal() {
        setBounds((int)x, (int)y, 10, 10);

    }

    public void create(double initX, double initY) {
    //create new object at initial X position and initial Y Position
        x = initX;
        y = initY;

    //Set initial speed - random speed between 0 and maxSpeed
        moveY = Math.random() * speed;

        // Make the total speed equal to 2
        moveX = Math.random() * speed;
        //Math.sqrt(4 - Math.pow(moveY, 2));

        
        int direction = ((int)Math.ceil(Math.random()*4));
        if (direction == 1 || direction == 2){ 
            moveX *= -1;
    }
        if (direction == 2 || direction == 4){
            moveY *= -1;
        }
    }

    public void wander(grid a) {
//animal wanders around, direction determined in create

        if(energy <= restEnergy){
            rest(a);
            return;
        }
        x += moveX;
        y += moveY;
        
        setBounds((int)x, (int)y, 25, 25);
    }

    public double dist(Animal A, Animal B) {
        double dx = B.x - A.x;
        double dy = B.y - A.y;

        return Math.sqrt(dx * dx + dy * dy);
    }

    public void rest(grid a){
        if(energy <= maxEnergy){
            energy +=10;
        }
    }
}