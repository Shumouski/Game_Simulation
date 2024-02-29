package game_simulator.battleIsOver;
import game_simulator.color.Color;
import game_simulator.warriors1.Warriors;
import java.util.List;
import game_simulator.map1.Map;
public class BattleOver1 {
    Map map;
    public BattleOver1(Map map) {
        this.map = map;
    }
    //The private boolean isBattleOver() method is used to determine whether the battle on the game map has ended.
    //This method checks for the presence of warriors of two different colors (red and blue)
    //on the map and returns true if at least one of them is not on the map,
    // and false if both colors of warriors are still present on the map.
    public boolean isBattleOver() {
        List<Warriors> warriors = map.getWarriors();
        boolean redExists = false;
        boolean blueExists = false;

        for (Warriors warrior : warriors) {
            if (warrior.getColor() == Color.RED) {
                redExists = true;
            } else if (warrior.getColor() == Color.BLUE) {
                blueExists = true;
            }
        }
        return !(redExists && blueExists);
    }
    public void toWinOfTeam(){
        List<Warriors> warriors = map.getWarriors();
        int blueCount = 0;
        int redCount = 0;

        for (Warriors warrior : warriors) {
            if (warrior.getColor() == Color.BLUE) {
                blueCount++;
            } else {
                redCount++;
            }
        }
        if (blueCount == warriors.size()) {
            System.out.println("Blue team is winner");
        } else if (redCount == warriors.size()) {
            System.out.println("Red team is winner");
        }
    }
    //That is, while in the main method while (isRunning && !isBattleOver())
    //by controlling objects on the map, the condition of the isBattleOver() method is satisfied
    //The battle does not end, both teams are present on the map and continue to move
}
