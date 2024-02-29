package game_simulator.placementOfObjects1;
import game_simulator.map1.Map;
import game_simulator.color.Color;
import game_simulator.warriors1.Jedi;
import game_simulator.warriors1.Panzer;
import game_simulator.warriors1.Unit;
import game_simulator.warriors1.Warriors;
import java.util.List;
import java.util.Random;
public class WarriorsPlacer {
    //The ObjectsPlacer class is responsible for placing objects on the game map.
    private Map map;
    private List<Warriors> warriors;
    public WarriorsPlacer(Map map, List<Warriors> warriors) {
        this.map = map;
        this.warriors = warriors;
    }
    public void placeWarriors() {
        Random random = new Random();
        //Inside the loop, 3 objects will be created and placed for the RED command.
        for (int i = 0; i < 3; i++) {
            int x, y;
            do {
                x = random.nextInt(map.getMapHeight()- 2) + 1;
                y = random.nextInt(map.getMapWidth() - 2) + 1;

            } while (map.getMap()[x][y] != ' ');

            Warriors redWarrior = new Unit(Color.RED, 2, 2, '\u29BF');
            redWarrior.setX(x);
            redWarrior.setY(y);
            map.getMap()[x][y] = redWarrior.getSymbol();
            warriors.add(redWarrior);

        }
        // Create and place objects for the Unit BLUE command the same as for RED
        for (int i = 0; i < 3; i++) {
            int x, y;
            do {
                x = random.nextInt(map.getMapHeight() - 2) + 1;
                y = random.nextInt(map.getMapHeight() - 2) + 1;
            } while (map.getMap()[x][y] != ' ');

            Warriors blueWarrior = new Unit(Color.BLUE, 2, 2, '\u25CF');
            blueWarrior.setX(x);
            blueWarrior.setY(y);
            map.getMap()[x][y] = blueWarrior.getSymbol();
            warriors.add(blueWarrior);
        }
        // Create and place one Panzer object for each team
        // for the RED command
        int x, y;
        do {
            x = random.nextInt(map.getMapHeight() - 2) + 1;
            y = random.nextInt(map.getMapWidth() - 2) + 1;
        } while (map.getMap()[x][y] != ' ');

        Panzer redPanzer = new Panzer(Color.RED, 4, 4, 'Y');
        redPanzer.setX(x);
        redPanzer.setY(y);
        map.getMap()[x][y] = redPanzer.getSymbol();
        warriors.add(redPanzer);

        // for BLUE command
        do {
            x = random.nextInt(map.getMapHeight() - 2) + 1;
            y = random.nextInt(map.getMapHeight() - 2) + 1;
        } while (map.getMap()[x][y] != ' ');

        Panzer bluePanzer = new Panzer(Color.BLUE, 4, 4, 'T');
        bluePanzer.setX(x);
        bluePanzer.setY(y);
        map.getMap()[x][y] = bluePanzer.getSymbol();
        warriors.add(bluePanzer);

        // Create and place one Jedi object for each command
        // for the RED command
        do {
            x = random.nextInt(map.getMapHeight() - 2) + 1;
            y = random.nextInt(map.getMapWidth() - 2) + 1;
        } while (map.getMap()[x][y] != ' ');

        Jedi redJedi = new Jedi(Color.RED, 4, 4, 'S');
        redJedi.setX(x);
        redJedi.setY(y);
        map.getMap()[x][y] = redJedi.getSymbol();
        warriors.add(redJedi);

        // for BLUE command
        do {
            x = random.nextInt(map.getMapHeight() - 2) + 1;
            y = random.nextInt(map.getMapWidth() - 2) + 1;
        } while (map.getMap()[x][y] != ' ');

        Jedi blueJedi = new Jedi(Color.BLUE, 3, 6, 'J');
        blueJedi.setX(x);
        blueJedi.setY(y);
        map.getMap()[x][y] = blueJedi.getSymbol();
        warriors.add(blueJedi);
    }
}
