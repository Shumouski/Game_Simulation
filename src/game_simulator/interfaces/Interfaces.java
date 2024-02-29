package game_simulator.interfaces;
import game_simulator.warriors2.WarriorsOne;
import java.util.List;
public class Interfaces {
    public interface FindBlueWarriorFinder {
        List<WarriorsOne> findBlueWarriorCoordinates();
    }
    public interface FindRedWarriorFinder {
        List<WarriorsOne> findRedWarriorCoordinates();
    }
}
