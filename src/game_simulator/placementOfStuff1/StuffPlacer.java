package game_simulator.placementOfStuff1;
import game_simulator.map1.Map;
import game_simulator.stuff1.Bomb;
import game_simulator.stuff1.Chest;
import game_simulator.stuff1.Stuff;
import java.util.List;
import java.util.Random;

public class StuffPlacer {
    //The StuffPlacer class is responsible for placing items
    //(in this case, first aid kits and bombs) on the game map.
    private Map map;

    private List<Stuff> stuffs;

    public StuffPlacer(Map map, List<Stuff> stuffs) {
        this.map = map;
        this.stuffs = stuffs;
    }
    //This method -> public void placeChests(), is responsible for placing chests on the game map.
    //The method randomly selects positions on the map
    //(horizontal and vertical x and y coordinates) and creates chest objects,
    //which are then added as items to the stuffs list.
    //Each chest has a '+' symbol and its position on the map is updated.
    public void placeChests() {
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            int x, y;
            do {
                x = random.nextInt(map.getMapHeight() - 2) + 1;
                y = random.nextInt(map.getMapWidth() - 2) + 1;
            } while (map.getMap()[x][y] != ' ');

            //the condition != ' ' (not equal to the space character) ensures that the loop will be executed until
            //until an empty cell is found on the map to place a first aid kit

            Chest chest = new Chest(3, '+');
            chest.setX(x);
            chest.setY(y);
            map.getMap()[x][y] = chest.getSymbol();
            stuffs.add(chest);
        }
    }

    // The same as for the first aid kit
    public void placeBomb(){
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            int x, y;
            do {
                x = random.nextInt(map.getMapHeight() - 2) + 1;
                y = random.nextInt(map.getMapWidth() - 2) + 1;
            } while (map.getMap()[x][y] != ' ');

            Bomb bomb = new Bomb(2, 'o');
            bomb.setX(x);
            bomb.setY(y);
            map.getMap()[x][y] = bomb.getSymbol();
            stuffs.add(bomb);
        }
}
}
//-----------------------------------------------------------------------------------------------------
//Both methods (placeChests and placeBomb) use a loop that runs a specified number of times
//(4 times for chests and 8 times for bombs)
//to place the required number of items on the map. Before placing each item, a check is made that the
//selected position on the map is empty (map.getMap()[x][y] != ' ').
//This avoids placing items in occupied or invalid positions.
//So the StuffPlacer class provides functionality for placing chests and bombs on
//the game map and updating the list of stuffs items.