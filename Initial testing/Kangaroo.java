public class Kangaroo extends Animal{
    
    public void forage(){
        //Search for vegetation to consume
        useEnergy();
    }

    public void flee(){
        //Runs from dingo
        useEnergy();
    }

    public void detectPredator(){
        //flee if predator detected
        flee();
    }

    public void reproduceKangaroo(){
        if(canReproduce()){
            //create new kangaroo object.
        }
        useEnergy();
    }
}
