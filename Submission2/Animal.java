package Submission2;

import java.awt.*;

public abstract class Animal extends Panel {

    // move variables
    double moveX;
    double moveY;
    double x;
    double y;

    // Energy Variables
    double energy = 100;
    double restEnergy = 50;
    double maxEnergy = 100;
    double movementEnergyCost = 10;

    // hunger variables
    double hunger = 80;
    double maxHunger = 100;

    // speed variables
    double speed = energy / 18;
    double maxSpeed = maxEnergy / 18;

    // age variables
    int age;
    int maxAge = 250;

    // Breed variables
    double breedEnergy = 70;
    int breedAge = 30;
    long breedCooldown = 10000;
    long lastBreedTime = 0;

    // other variables
    int update;
    int SensoryDistance = 50;
    int Animal_Size = 25;
    
    protected CauseOfDeath causeOfDeath;
    protected Behaviour behaviour;
    

    public void setBehaviour(Behaviour behaviour) {
        this.behaviour = behaviour;
    }

    public void performBehaviour(grid g) {
        if (behaviour != null) {
            behaviour.perform(this, g);
        }
    }

    public CauseOfDeath getCauseOfDeath() {
        if (energy <= 0) {
            return CauseOfDeath.EXHAUSTION;
        }
        if (age > maxAge) {
            return CauseOfDeath.OLD_AGE;
        }
        if (hunger <= 0) {
            return CauseOfDeath.HUNGER;
        }
        return null;
    }

    public Animal() {
        setBounds((int) x, (int) y, 10, 10);

    }

    public boolean isAlive() {
        return energy > 0 && age <= maxAge && hunger > 0;
    }

    public void create(double initX, double initY) {

        x = initX;
        y = initY;

        moveY = Math.random() * speed;
        moveX = Math.random() * speed;
    }

    public void wander(grid a) {
        boolean hasMoved = false;

        // perform species specific behaviours
        performBehaviour(a);

        // if energy below rest threshold, rest
        if (energy <= restEnergy) {
            rest();
        } else {
            a.checkBounds(this);

            x += moveX;
            y += moveY;
            setBounds((int) x, (int) y, Animal_Size, Animal_Size);

            hasMoved = true;

        }
        // movement costs energy
        if (hasMoved) {
            energy -= movementEnergyCost;
        }

        update();
        ageAnimal();
        hungry();

        // remove animals if dead
        if (!isAlive()) {
            causeOfDeath = getCauseOfDeath();

            System.out.println(getClass().getSimpleName() + " died of " + causeOfDeath);
            a.deathRecords.add(causeOfDeath);
            a.death(this);
            return;
        }

        // common breeding behaviour
        breed(a);

    }

    public double dist(Animal A, Animal B) {
        // returns distance between animals A and B.
        double dx = B.x - A.x;
        double dy = B.y - A.y;

        return Math.sqrt(dx * dx + dy * dy);
    }

    public void rest() {
        // if energy <= maxEnergy animal rests to restore energy.
        if (energy <= maxEnergy) {
            energy = maxEnergy;
        }
    }

    public int ageAnimal() {
        // Ages animal by 1 when called.
        age += 1;
        return age;
    }

    public int update() {
        // update by 1 every call
        update += 1;
        return update;
    }

    public abstract void reproduce(grid g);

    public boolean canBreed() {
        // checks breeding eligibility
        long currentTime = System.currentTimeMillis();
        // if age > minimum breeding, energy > minimum energy requirement
        // Hunger > 30 and after breed cooldown, CanBreed = true
        return age >= breedAge &&
                energy >= breedEnergy &&
                currentTime - lastBreedTime >= breedCooldown &&
                hunger > 30;

    }

    public void breed(grid g) {
        // if eligible kangaroo, create new kangaroo
        // if eligible dingo, create new dingo

        if (canBreed()) {

            reproduce(g);

            // breeding costs 20 points of energy
            energy -= 20;
            // takes current time when breeding occurs.
            lastBreedTime = System.currentTimeMillis();
        }
    }

    public void hungry() {
        // hunger reduced when 1 when called.
        hunger -= 1;
    }

}
