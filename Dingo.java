public class Dingo extends Animal{
    
    public void hunt(){
        //Dingo searches for kangaroos to eat
        useEnergy();
    }

    public void chase(){
        //if kangaroo within sensoryRadius, chase after kangaroo
        useEnergy();
    }
    public void detectPrey(){
        chase();
    }
    public void reproduceDingo(){
        if(canReproduce()){
            //create new Dingo object.
        }
        useEnergy();
    }
}
