package game_simulator.map2;
import game_simulator.coordinates2.Coordinates;
import game_simulator.coordinates2.File;
import game_simulator.color.Color;
import game_simulator.resolveWarriorsConflict.WarriorsConflict2;
import game_simulator.warriors2.JediOne;
import game_simulator.warriors2.PanzerOne;
import game_simulator.warriors2.UnitOne;
import game_simulator.warriors2.WarriorsOne;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnotherMap {
    // This method creates a new ArrayList that contains all the values
    // from warriors (by using values()), and returns that list.
    public List<WarriorsOne> getWarriorsList() {
        return new ArrayList<>(warriors.values());
    }
    HashMap<Coordinates, WarriorsOne> warriors = new HashMap<>();
    // This method sets the coordinates for warriorOne,
    // assuming that WarriorsOne has a coordinates field where the passed coordinates can be stored.
    // It then puts warriorOne into the warriors collection, using coordinates as the key.
    public void setWarriors(Coordinates coordinates, WarriorsOne warriorOne){
        warriorOne.coordinates = coordinates;
        warriors.put(coordinates,warriorOne);
    }
    public void removeWarrior(Coordinates coordinates){
        warriors.remove(coordinates);
    }
    public void moveWarrior(Coordinates from, Coordinates to, WarriorsConflict2 warriorsConflict2) {
        WarriorsOne attacker = getWarriors(from);
        WarriorsOne defender = getWarriors(to);
        // If there is already a warrior at the final position
        if (defender != null) {
            warriorsConflict2.resolveWarriorsConflict1(attacker, defender);
        } else {
            // If the final position is free, simply move the warrior
            removeWarrior(from);
            setWarriors(to, attacker);
            attacker.coordinates = to;
        }
    }
    // This method is used to check for the presence of a warrior in a specific square on the game board
    // or in the game environment represented by the warriors collection.
    public boolean isSquareEmpty(Coordinates coordinates){
        return !warriors.containsKey(coordinates);
    }
    //The method returns an object of type WarriorsOne that corresponds to the passed coordinates.
    //It uses the get() method of the warriors collection to get the warrior object associated with the specified coordinates.
    public WarriorsOne getWarriors(Coordinates coordinates){
        return warriors.get(coordinates);
    }

    public void setUpPositions() {
        // Install Unit
        for (File file : File.values()) {
            setWarriors(new Coordinates(file, 2), new UnitOne(Color.BLUE, new Coordinates(file, 2),5,2));
            setWarriors(new Coordinates(file, 7), new UnitOne(Color.RED, new Coordinates(file, 7),5,2));
        }
        // Install Panzer и Jedi
        for (File file1 : File.values()) {
            if(file1 != File.A && file1 != File.B && file1 != File.C && file1 != File.F && file1 != File.G && file1 != File.H) {
                setWarriors(new Coordinates(file1,1),new JediOne(Color.BLUE, new Coordinates(file1,1),5,6));
                setWarriors(new Coordinates(file1,8),new JediOne(Color.RED, new Coordinates(file1,8),5,6));
            }else{
                setWarriors(new Coordinates(file1,1),new PanzerOne(Color.BLUE, new Coordinates(file1,1),5,4));
                setWarriors(new Coordinates(file1,8),new PanzerOne(Color.RED, new Coordinates(file1,8),5,4));
            }
        }
    }
    // Thus, this method is used to determine whether a given square on the game
    // board is dark (black) or light (white).
    public static boolean isSquareDark(Coordinates coordinates){
        return (((coordinates.file.ordinal() + 1) + coordinates.rank) %2 ) == 0;
    }
    // This method is used to check whether a specified square on the
    // game board is occupied by a warrior of the specified color.
    public boolean isSquareOccupiedBySameColor(Coordinates coordinates, Color color) {
        WarriorsOne warriorsOne = getWarriors(coordinates);
        return warriorsOne != null && warriorsOne.color.equals(color);
    }
    // Method for getting the warrior's coordinates
    public Coordinates getCoordinates(WarriorsOne warrior) {
        for (Map.Entry<Coordinates, WarriorsOne> entry : warriors.entrySet()) {
            if (entry.getValue().equals(warrior)) {
                return entry.getKey();
            }
        }
        return null; // warrior not found on the map
    }

}