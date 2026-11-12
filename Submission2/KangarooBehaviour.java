package Submission2;

public class KangarooBehaviour implements Behaviour {
    
    private final static int plantFoodValue = 20;
    private final static int forageDist = 50;
    
    @Override
    public void perform(Animal animal, grid g) {

        Kangaroo kangaroo = (Kangaroo) animal;

        boolean fleeing = flee(kangaroo, g);

        if (!fleeing && kangaroo.hunger < kangaroo.maxHunger) {
            forage(kangaroo, g);
        }
    }

    private boolean flee(Kangaroo kangaroo, grid g) {
        Container<Animal> animals = g.animals;
        for (int i = animals.size() - 1; i >= 0; i--) {
            if (animals.get(i) instanceof Dingo) {

                Dingo dingo = (Dingo) animals.get(i);
                if (kangaroo.dist(kangaroo, dingo) < kangaroo.SensoryDistance) {
                    changeDirection(kangaroo, dingo);
                    return true;
                }
            }
        }
        return false;
    }

    private void forage(Kangaroo kangaroo, grid g) {
        for (int i = g.vegetation.size() - 1; i >= 0; i--) {
            Vegetation plant = g.vegetation.get(i);
            double dist = Math.sqrt(Math.pow(plant.x - kangaroo.x, 2) + Math.pow(plant.y - kangaroo.y, 2));
            // if Kangaroo is within forage distance of a plant, the plant is eaten and the
            // kangaroo gains hunger points from plantFoodValue.
            if (dist < forageDist) {
                eat(kangaroo);
                g.eaten(plant);
                System.out.println("Forage Successful");

                break;
            }
        }

    }

    private void eat(Kangaroo kangaroo){
        kangaroo.hunger = Math.min(kangaroo.maxHunger, kangaroo.hunger+ plantFoodValue);
    }

    public void changeDirection(Kangaroo kangaroo, Dingo dingo) {
        double dx = kangaroo.x - dingo.x;
        double dy = kangaroo.y - dingo.y;

        double distance = Math.sqrt(dx * dx + dy * dy);

        if(distance > 0){
            kangaroo.moveX = (dx/distance) * kangaroo.speed;
            kangaroo.moveY = (dy/distance) * kangaroo.speed;
        }

        }
}
