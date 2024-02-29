package game_simulator.warriors2;
import game_simulator.color.Color;
import game_simulator.coordinates2.Coordinates;
import game_simulator.coordinates2.CoordinatesShift;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
public class UnitOne extends WarriorsOne {
    // super Constructor
    public UnitOne(Color color, Coordinates coordinates, int health, int power) {
        super(color, coordinates, health, power);
    }
    @Override // Methods getTotalScore() from abstract class WarriorsOne
    public int getTotalScore() {
        return super.getTotalScore();
    }
    @Override // Methods decrementTotalScore() from abstract class WarriorsOne
    public int decrementTotalScore() {
        return super.decrementTotalScore();
    }
    @Override // getPieceMove() describes possible coordinate shifts for a given type of object
    public Set<CoordinatesShift> getPieceMove() {
        return new HashSet<>(Arrays.asList(
                new CoordinatesShift(1, 1),  // Move forward and right
                new CoordinatesShift(1, 0),  // Move forward and left
                new CoordinatesShift(1, -1), // Move backwards and to the right
                new CoordinatesShift(0, 1),  // Move backward and left
                new CoordinatesShift(0, -1), // Move right and forward
                new CoordinatesShift(-1, 1), // Move right and back
                new CoordinatesShift(-1, 0), // Move left and forward
                new CoordinatesShift(-1, -1) // Move left and back
        ));
    }
}
