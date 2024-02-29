package game_simulator.computerControl;
import game_simulator.battleIsOver.BattleIsOver2;
import game_simulator.color.Color;
import game_simulator.coordinates2.Coordinates;
import game_simulator.interfaces.Interfaces;
import game_simulator.map2.AnotherMap;
import game_simulator.warriors2.WarriorsOne;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
public class FindBlueRedWarriors implements Interfaces.FindBlueWarriorFinder, Interfaces.FindRedWarriorFinder {
    private AnotherMap anotherMap;
    public FindBlueRedWarriors(AnotherMap anotherMap) {
        this.anotherMap = anotherMap;
    }
    @Override
    public List<WarriorsOne> findBlueWarriorCoordinates() {
        List<WarriorsOne> blueWarriors = anotherMap.getWarriorsList().stream()
                .filter(warrior -> warrior.getColor() == Color.BLUE)
                .collect(Collectors.toList());

        if (blueWarriors.isEmpty()) {
            BattleIsOver2 battleIsOver2 = new BattleIsOver2(anotherMap);
            battleIsOver2.toFinishBattle();
            return Collections.emptyList();
        }
        return blueWarriors;
    }
    @Override
    public List<WarriorsOne> findRedWarriorCoordinates() {
        List<WarriorsOne> redWarriors = anotherMap.getWarriorsList().stream()
                .filter(warrior -> warrior.getColor() == Color.RED)
                .collect(Collectors.toList());

        if (redWarriors.isEmpty()) {
            BattleIsOver2 battleIsOver2 = new BattleIsOver2(anotherMap);
            battleIsOver2.toFinishBattle();
            return Collections.emptyList();
        }
        return redWarriors;
    }
    // Method for finding the closest blue warrior to any red warrior
    public WarriorsOne findNearestBlueWarriorToRed(List<WarriorsOne> redWarriors, List<WarriorsOne> blueWarriors) {
        double minDistance = Double.MAX_VALUE;
        WarriorsOne nearestBlueWarrior = null;

        for (WarriorsOne redWarrior : redWarriors) {
            Coordinates redCoords = anotherMap.getCoordinates(redWarrior);
            for (WarriorsOne blueWarrior : blueWarriors) {
                Coordinates blueCoords = anotherMap.getCoordinates(blueWarrior);
                double distance = redCoords.distanceTo(blueCoords);
                if (distance < minDistance) {
                    minDistance = distance;
                    nearestBlueWarrior = blueWarrior;
                }
            }
        }
        return nearestBlueWarrior;
    }
    // Method for finding the closest red warrior in relation to the found blue one
    public WarriorsOne findNearestRedWarriorToBlue(WarriorsOne nearestBlueWarrior, List<WarriorsOne> redWarriors) {
        double minDistance = Double.MAX_VALUE;
        WarriorsOne nearestRedWarrior = null;

        Coordinates blueCoords = anotherMap.getCoordinates(nearestBlueWarrior);

        for (WarriorsOne redWarrior : redWarriors) {
            Coordinates redCoords = anotherMap.getCoordinates(redWarrior);
            double distance = redCoords.distanceTo(blueCoords);
            if (distance < minDistance) {
                minDistance = distance;
                nearestRedWarrior = redWarrior;
            }
        }
        return nearestRedWarrior;
    }
}