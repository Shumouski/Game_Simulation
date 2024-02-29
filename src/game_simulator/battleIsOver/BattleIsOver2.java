package game_simulator.battleIsOver;
import game_simulator.color.Color;
import game_simulator.map2.AnotherMap;
import game_simulator.warriors2.WarriorsOne;
import java.util.List;
public class BattleIsOver2 {
    AnotherMap anotherMap;
    public BattleIsOver2(AnotherMap anotherMap) {
        this.anotherMap = anotherMap;
    }
    public boolean isBattleOver(){
        // Get a list of all warriors on the map
        List<WarriorsOne> warriors = anotherMap.getWarriorsList();

        // Check if there is at least one warrior of each color
        boolean blueTeamAlive = warriors.stream().anyMatch(w -> w.color == Color.BLUE);
        boolean redTeamAlive = warriors.stream().anyMatch(w -> w.color == Color.RED);

        // The battle is over if at least one of the teams no longer has warriors
        return !blueTeamAlive || !redTeamAlive;
    }
    public void toFinishBattle(){
        List<WarriorsOne> warriors = anotherMap.getWarriorsList();

        Color blueColor = Color.BLUE;
        Color redColor = Color.RED;

        boolean blueTeamAlive = false;
        boolean redTeamAlive = false;

        for (WarriorsOne warrior : warriors) {
            if (warrior.color.equals(blueColor)) {
                blueTeamAlive = true;
            } else if (warrior.color.equals(redColor)) {
                redTeamAlive = true;
            }
        }

        if (blueTeamAlive && !redTeamAlive) {
            System.out.println("Blue team is the winner");
            System.out.println();
        } else {
            System.out.println("Red team is the winner");
            System.out.println();
        }
    }
}
