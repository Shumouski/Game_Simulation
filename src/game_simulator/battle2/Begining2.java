package game_simulator.battle2;
import game_simulator.computerControl.FindBlueRedWarriors;
import game_simulator.computerControl.FindShortPath;
import game_simulator.computerControl.MoveRedWarrior;
import game_simulator.battleIsOver.BattleIsOver2;
import game_simulator.coordinates2.InputCoordinates;
import game_simulator.select.SelectSimulation;
import game_simulator.color.Color;
import game_simulator.coordinates2.Coordinates;
import game_simulator.map2.AnotherMap;
import game_simulator.map2.AnotherMapConsolRenderer;
import game_simulator.resolveWarriorsConflict.WarriorsConflict2;
import game_simulator.warriors2.WarriorsOne;
import java.util.List;
import java.util.Set;

public class Begining2 {
    private final AnotherMap anotherMap;
    private final WarriorsConflict2 warriorsConflict2;
    private AnotherMapConsolRenderer consolRenderer = new AnotherMapConsolRenderer();

    public Begining2(AnotherMap anotherMap, WarriorsConflict2 warriorsConflict2) {
        this.anotherMap = anotherMap;
        this.warriorsConflict2 = warriorsConflict2;
    }
    public void toBegin() throws InterruptedException {
        boolean whoTakeMove = true;
        while (true) {
            consolRenderer.render(anotherMap);

            if (whoTakeMove) {
                System.out.println("Blue to move");
                // input
                //if the whoTakeMove variable is true, then the color will be Color.BLUE,
                //otherwise it will be Color.RED. Essentially, depending on the value of whoTakeMove,
                //the color that will be used to enter the coordinates of the warriors is selected.
                Coordinates sourceCoordinates = InputCoordinates.inputWarriorsOnecoordinatesForColor(
                        whoTakeMove ? Color.BLUE : Color.RED, anotherMap);

                WarriorsOne warriorsOne = anotherMap.getWarriors(sourceCoordinates);
                Set<Coordinates> availableMoveSquare = warriorsOne.getAvailableMoveSquare(anotherMap);

                Coordinates targetCoordinates = InputCoordinates.inputAvailableSquare(
                        availableMoveSquare, anotherMap, whoTakeMove ? Color.BLUE : Color.RED);
                // make move
                anotherMap.moveWarrior(sourceCoordinates, targetCoordinates, warriorsConflict2);
            } else {
                System.out.println("Red to move");
                FindBlueRedWarriors findBlueRedWarriors = new FindBlueRedWarriors(anotherMap);
                // Find all the blue and red warriors on the map
                List<WarriorsOne> blueWarriors = findBlueRedWarriors.findBlueWarriorCoordinates();
                List<WarriorsOne> redWarriors = findBlueRedWarriors.findRedWarriorCoordinates();
                // Find the closest blue warrior to the red one and the closest red one to the blue one
                WarriorsOne nearestBlueWarrior = findBlueRedWarriors.findNearestBlueWarriorToRed(redWarriors, blueWarriors);
                WarriorsOne nearestRedWarrior = findBlueRedWarriors.findNearestRedWarriorToBlue(nearestBlueWarrior, redWarriors);
                //Found an intermediate path from red to blue
                FindShortPath findShortPath = new FindShortPath(anotherMap);
                findShortPath.findShortestPath(nearestRedWarrior, nearestBlueWarrior);
                List<Coordinates> shortestPath = findShortPath.findShortestPath(nearestRedWarrior, nearestBlueWarrior);
                //The progress of the Red War taking into account the data
                MoveRedWarrior moveRedWarrior = new MoveRedWarrior(anotherMap);
                moveRedWarrior.toMove(nearestRedWarrior,nearestBlueWarrior,shortestPath);
                }
                whoTakeMove = !whoTakeMove;

                BattleIsOver2 battleIsOver2 = new BattleIsOver2(anotherMap);
                if (battleIsOver2.isBattleOver()) {
                    battleIsOver2.toFinishBattle();
                    break;
                }
            }
            SelectSimulation simulation = new SelectSimulation();
            simulation.toSelect();
        }
    }
