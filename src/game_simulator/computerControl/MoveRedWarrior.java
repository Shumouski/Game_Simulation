package game_simulator.computerControl;
import game_simulator.coordinates2.Coordinates;
import game_simulator.map2.AnotherMap;
import game_simulator.resolveWarriorsConflict.WarriorsConflict2;
import game_simulator.warriors2.WarriorsOne;
import java.util.List;

public class MoveRedWarrior {
    private AnotherMap anotherMap;

    public MoveRedWarrior(AnotherMap anotherMap) {
        this.anotherMap = anotherMap;
    }

    public void toMove(WarriorsOne redWarrior, WarriorsOne blueWarrior, List<Coordinates> path) {
        // Получаем текущие координаты красного воина
        Coordinates currentCoords = anotherMap.getCoordinates(redWarrior);

        // Получаем координаты синего воина
        Coordinates blueCoords = anotherMap.getCoordinates(blueWarrior);

        // Проверяем, находится ли синий воин на соседней клетке по горизонтали, вертикали или диагонали
        if (isAdjacent(currentCoords, blueCoords)) {
            // Если синий воин находится на соседней клетке, занимаем его позицию
            WarriorsConflict2 warriorsConflict2 = new WarriorsConflict2(anotherMap);
            warriorsConflict2.resolveWarriorsConflict1(redWarrior, blueWarrior);
        } else {
            // Если синий воин далеко, перемещаем красного воина по найденному пути
            if (!path.isEmpty()) {
                Coordinates nearestCoord = path.get(0);
                removeWarrior(redWarrior);
                setWarriors(nearestCoord, redWarrior);
            } else {
                System.out.println("No path found for red warrior to move.");
            }
        }
    }

    private boolean isAdjacent(Coordinates coords1, Coordinates coords2) {
        int deltaX = Math.abs(coords1.file.ordinal() - coords2.file.ordinal());
        int deltaY = Math.abs(coords1.rank - coords2.rank);
        return (deltaX <= 1 && deltaY <= 1);
    }
    private void removeWarrior(WarriorsOne warrior) {
        anotherMap.removeWarrior(anotherMap.getCoordinates(warrior));
    }
    private void setWarriors(Coordinates coordinates, WarriorsOne warrior) {
        warrior.coordinates = coordinates;
        anotherMap.setWarriors(coordinates, warrior);
    }
}