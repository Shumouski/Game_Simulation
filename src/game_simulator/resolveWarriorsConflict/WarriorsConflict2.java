package game_simulator.resolveWarriorsConflict;
import game_simulator.coordinates2.Coordinates;
import game_simulator.map2.AnotherMap;
import game_simulator.warriors2.WarriorsOne;
import java.util.Random;
public class WarriorsConflict2 {
    AnotherMap anotherMap;
    Random random;
    public WarriorsConflict2(AnotherMap anotherMap) {
        this.anotherMap = anotherMap;
        this.random = new Random();
    }
    // A method for resolving a conflict between objects of different teams at the moment they hit the same coordinate
    public void resolveWarriorsConflict1(WarriorsOne attacker, WarriorsOne defender) {
        synchronized (anotherMap) {
            if (!attacker.color.equals(defender.color) && attacker.getTotalScore() > defender.getTotalScore()) {
                moveWarriorToDefeatedSquare(attacker, defender);
            } else if(!attacker.color.equals(defender.color) && attacker.getTotalScore() < defender.getTotalScore()){
                moveWarriorToDefeatedSquare1(defender, attacker);
            }else if (!attacker.color.equals(defender.color) && attacker.getTotalScore() == defender.getTotalScore()){
                resolveRandomWinner(attacker,defender);
            }
        }
    }
    // This method sends a war to the position of another war,
    // removes it from the old coordinates and calls the health subtraction method
    private void moveWarriorToDefeatedSquare(WarriorsOne winner, WarriorsOne defeated) {
        Coordinates to = defeated.coordinates;
        removeWarrior(winner);
        setWarriors(to, winner);
        winner.coordinates = to;
        winner.decrementTotalScore();
    }
    private void moveWarriorToDefeatedSquare1(WarriorsOne winner, WarriorsOne defeated) {
        removeWarrior(defeated);
        winner.decrementTotalScore();
    }
    // A method for randomly resolving a conflict between wars of different teams,
    // in the case when their getTotalScore() is the same
    private void resolveRandomWinner(WarriorsOne warrior1, WarriorsOne warrior2) {
        boolean randomWinner = random.nextBoolean();
        Coordinates to = warrior2.coordinates;

        if (randomWinner) {
            // The winner takes the position of the loser
            removeWarrior(warrior1);
            setWarriors(to,warrior1);
            warrior1.coordinates = to;
            warrior1.decrementTotalScore();
        } else {
            // The winner does not take the position of the loser, but is removed
            removeWarrior(warrior1);
            warrior2.decrementTotalScore();
        }
    }
    // Method of removing a warrior from his position
    private void removeWarrior(WarriorsOne warrior) {
        anotherMap.removeWarrior(warrior.coordinates);
    }
    // Method of installing a warrior to position
    private void setWarriors(Coordinates coordinates, WarriorsOne warrior) {
        warrior.coordinates = coordinates;
        anotherMap.setWarriors(coordinates, warrior);
    }
}




