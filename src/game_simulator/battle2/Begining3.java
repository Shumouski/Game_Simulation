package game_simulator.battle2;
import game_simulator.battleIsOver.BattleIsOver2;
import game_simulator.select.SelectSimulation;
import game_simulator.color.Color;
import game_simulator.coordinates2.Coordinates;
import game_simulator.coordinates2.InputCoordinates;
import game_simulator.map2.AnotherMap;
import game_simulator.map2.AnotherMapConsolRenderer;
import game_simulator.resolveWarriorsConflict.WarriorsConflict2;
import game_simulator.warriors2.WarriorsOne;
import java.util.Set;

    public class Begining3 {
        private final AnotherMap anotherMap;
        private final WarriorsConflict2 warriorsConflict2;
        private AnotherMapConsolRenderer consolRenderer = new AnotherMapConsolRenderer();

        public Begining3(AnotherMap anotherMap, WarriorsConflict2 warriorsConflict2) {
            this.anotherMap = anotherMap;
            this.warriorsConflict2 = warriorsConflict2;
        }
        //For a game of two people
        public void toBegin3() throws InterruptedException {
            boolean whoTakeMove = true;

            while (true) {
                // render
                consolRenderer.render(anotherMap);

                if (whoTakeMove) {
                    System.out.println("Blue to move");
                } else {
                    System.out.println("Red to move");
                }
                // input
                Coordinates sourceCoordinates = InputCoordinates.inputWarriorsOnecoordinatesForColor(
                        whoTakeMove ? Color.BLUE : Color.RED, anotherMap);

                WarriorsOne warriorsOne = anotherMap.getWarriors(sourceCoordinates);
                Set<Coordinates> availableMoveSquare = warriorsOne.getAvailableMoveSquare(anotherMap);

                Coordinates targetCoordinates = InputCoordinates.inputAvailableSquare(availableMoveSquare, anotherMap, whoTakeMove ? Color.BLUE : Color.RED);

                // make move
                anotherMap.moveWarrior(sourceCoordinates, targetCoordinates, warriorsConflict2);

                // pass move
                whoTakeMove = !whoTakeMove;

                BattleIsOver2 battleIsOver2 = new BattleIsOver2(anotherMap);
                if(battleIsOver2.isBattleOver()){
                    battleIsOver2.toFinishBattle();
                    break;
                }
            }
            SelectSimulation simulation = new SelectSimulation();
            simulation.toSelect();
        }
    }
