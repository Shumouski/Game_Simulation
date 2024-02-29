package game_simulator.resolveWarriorsConflict;
import game_simulator.map1.Map;
import game_simulator.warriors1.Warriors;
public class WarriorsConflict1 {
    Map map;
    public WarriorsConflict1(Map map) {
        this.map = map;
    }
    //This resolveConflict method is used to resolve a conflict between two warriors, warrior1 and warrior2
    //Based on their total score (the totalScore field in Warriors class objects).
    public void resolveWarriorsConflict(Warriors warrior1, Warriors warrior2) {
        // Compare the total score of warrior1 with the total score of warrior2
        if (warrior1.getTotalScore() > warrior2.getTotalScore()) {
            // This line starts the synchronization section, where map is the map object.
            // This means that only one thread can execute code in this section at a time.
            synchronized (map) {
                map.removeObject(warrior2.getX(), warrior2.getY());
                map.getWarriors().remove(warrior2);
            }
        } else if (warrior2.getTotalScore() > warrior1.getTotalScore()) {
            synchronized (map) {
                map.removeObject(warrior1.getX(), warrior1.getY());
                map.getWarriors().remove(warrior1);
            }
        }
    }
}
