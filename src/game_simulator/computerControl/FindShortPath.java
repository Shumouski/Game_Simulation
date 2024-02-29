package game_simulator.computerControl;
import game_simulator.coordinates2.Coordinates;
import game_simulator.coordinates2.File;
import game_simulator.map2.AnotherMap;
import game_simulator.warriors2.WarriorsOne;
import java.util.ArrayList;
import java.util.List;

public class FindShortPath {
    private AnotherMap anotherMap;

    public FindShortPath(AnotherMap anotherMap) {
        this.anotherMap = anotherMap;
    }

    public List<Coordinates> findShortestPath(WarriorsOne redWarrior, WarriorsOne blueWarrior) {
        // Получаем координаты начального и конечного воинов
        Coordinates startCoords = anotherMap.getCoordinates(redWarrior);
        Coordinates endCoords = anotherMap.getCoordinates(blueWarrior);
        // Применяем алгоритм поиска кратчайшего пути
        List<Coordinates> shortestPath = algorithmToFindShortestPath(startCoords, endCoords);

        return shortestPath;
    }

    private List<Coordinates> algorithmToFindShortestPath(Coordinates startCoords, Coordinates endCoords) {
        List<Coordinates> allFreeCoords = new ArrayList<>();

        // Проходим по всем клеткам на карте
        for (File file : File.values()) {
            for (int rank = 1; rank <= 8; rank++) {
                Coordinates currentCoords = new Coordinates(file, rank);
                // Проверяем, является ли текущая клетка свободной
                if (anotherMap.isSquareEmpty(currentCoords)) {
                    allFreeCoords.add(currentCoords);
                }
            }
        }

        // Находим ближайшую свободную координату к красному воину по прямой траектории к синему воину
        Coordinates nearestFreeCoord = null;
        double minDistance = Double.MAX_VALUE;
        for (Coordinates freeCoord : allFreeCoords) {
            // Проверяем, находится ли текущая координата на прямой траектории к конечной координате
            int deltaX = endCoords.file.ordinal() - startCoords.file.ordinal();
            int deltaY = endCoords.rank - startCoords.rank;
            int deltaFreeX = freeCoord.file.ordinal() - startCoords.file.ordinal();
            int deltaFreeY = freeCoord.rank - startCoords.rank;

            if (((deltaFreeX == 0 || deltaFreeY == 0 || Math.abs(deltaFreeX) == Math.abs(deltaFreeY)) &&
                    (deltaFreeX != 0 || deltaFreeY != 0) &&   // исключаем текущую координату
                    (deltaX == 0 || deltaFreeX == 0 || Math.signum(deltaX) == Math.signum(deltaFreeX)) &&
                    (deltaY == 0 || deltaFreeY == 0 || Math.signum(deltaY) == Math.signum(deltaFreeY))) ||
                    ((Math.abs(deltaFreeX) == 1 && Math.abs(deltaFreeY) == 1) &&  // добавляем условие для диагонального хода
                            (deltaX != 0 && deltaY != 0))) { // проверяем, что это диагональный ход
                double distance = freeCoord.distanceTo(startCoords);
                if (distance < minDistance) {
                    minDistance = distance;
                    nearestFreeCoord = freeCoord;
                }
            }
        }

        // Создаем список, содержащий только одну найденную координату
        List<Coordinates> shortestPath = new ArrayList<>();
        if (nearestFreeCoord != null) {
            shortestPath.add(nearestFreeCoord);
        }

        return shortestPath;
    }
}