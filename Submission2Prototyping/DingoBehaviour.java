package Submission2Prototyping;

public class DingoBehaviour implements Behaviour {

     private final static int kangarooFoodValue = 20;

    @Override
    public void perform(Animal animal, grid g) {
        Dingo dingo = (Dingo) animal;

        if (dingo.hunger < dingo.maxHunger) {
            hunt(dingo, g);
        }
    }

    private void hunt(Dingo dingo, grid g) {
        // if Dingo is within sensoryDistance of kangaroo, hunt is successful, kangaroo
        // dies and
        // Dingo eats, gaining hunger points from KangarooFoodValue.
        Container<Animal> animals = g.animals;
        for (int i = animals.size() - 1; i >= 0; i--) {
            if (animals.get(i) instanceof Kangaroo) {
                if (dingo.dist(dingo, animals.get(i)) < dingo.SensoryDistance) {
                    eat(dingo);
                    g.death(animals.get(i));
                    g.kangaroosEaten++;
                    System.out.println("Hunt Successful");
                    System.out.println("Kangaroo was eaten");

                    break;

                }
            }
        }
    }

    private void eat(Dingo dingo){
        dingo.hunger = Math.min(dingo.maxHunger, dingo.hunger + kangarooFoodValue);
    }
}