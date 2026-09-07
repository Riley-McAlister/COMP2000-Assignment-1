public class Animal{
//Variables
        public int age;
        public int maxAge;

        public double health;
        public double maxHealth;

        public double hunger;
        public double maxHunger;

        public double speed;
        public double maxSpeed;

        public double energy;
        public double maxEnergy;

        public double sensoryDistance;

        public int foodValue;

        public int reproductiveAge;
        public double reproductiveHealthThreshold;
        public double reproductiveEnergyThreshold;

        public boolean alive;

//Functions
        public void wander(){
        //Animal moves around the environment

        }

        public void currentSpeed(){
        //speed corolates to energy level, e.g. 100% energy = 100% maxSpeed, 50% energy = 50% maxSpeed
                if(energy > 0){
                        speed = maxSpeed * (energy/ maxEnergy);
                }
        }

        public void eat(){
        //animal consumes food & reduces hunger
                hunger -= foodValue;

                if(hunger < 0){
                        hunger = 0;
                }
        }


        public void findMate(){
        //animal searches for eligible mate of same species.

        }

        public void increaseHunger(){
        //Increases an animals hunger level over time.
                if (hunger < maxHunger){
                        hunger++;
                }
        }

      
        public void useEnergy(){
        //Reduces an animals energy level
                if (energy > 0){
                        energy--;
                }
        }

        public void rest(){
        //Increases an animals energy level
                if (energy < maxEnergy) {
                        energy++;
                }
        }

        public void ageAnimal(){
        //increases an animals age
                if (alive && age < maxAge){
                        age++;
                }
        }

        public boolean canReproduce(){
        //Checks reproductive conditions 
                if (alive && age >= reproductiveAge 
                && health >= reproductiveHealthThreshold 
                && energy >= reproductiveEnergyThreshold){
                       return true;
                }
                return false;
        }

}
