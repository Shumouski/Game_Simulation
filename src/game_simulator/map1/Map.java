package game_simulator.map1;
import game_simulator.placementOfObjects1.WarriorsPlacer;
import game_simulator.placementOfStuff1.StuffPlacer;
import game_simulator.stuff1.Bomb;
import game_simulator.stuff1.Chest;
import game_simulator.stuff1.Stuff;
import game_simulator.warriors1.Warriors;
import java.util.ArrayList;
import java.util.List;

public class Map {
    private int mapHeight = 11;
    private int mapWidth = 15;
    private char[][] map;
    private List<Warriors> warriors = new ArrayList<>();
    public List<Warriors> getWarriors() {                 //  - Определение геттера для получения списка предметов (warriors).
        return warriors;
    }
    private List<Stuff>stuffs =new ArrayList<>();

    public List<Stuff> getStuffs() {    //  - Определение геттера для получения списка предметов (stuffs).
        return stuffs;
    }
    public char[][] getMap() {         //   - Определение геттера для получения карты (map).
        return map;
    }
    public int getMapHeight() {       //    - Определение геттера для получения высоты карты (mapHeight).
        return mapHeight;
    }
    public int getMapWidth() {       //     - Определение геттера для получения ширины карты (mapWidth).
        return mapWidth;
    }
//-------------------------------------------------------------------------------------------------------------------
// Define a getCharMap() method to create and return a character map matrix.
// Necessary to provide external code and the ability to get the current state of the map in symbolic form
// what is displayed in the console. The method shows the current state of the game card and helps to understand
// where warriors, items and other objects are located.
// Returning the map's character matrix via getCharMap() allows this information to be made available
// outside the Map class, which may be useful for other game components or for external scripts,
// related to the display and analysis of the game map.
    public char[][] getCharMap() {
        //Inside the getCharMap() method, the character matrix is filled
        //charMap, where ' ' represents empty space and '.' represents the border of the map.
        char[][] charMap = new char[mapHeight][mapWidth];
        for (int i = 0; i < mapHeight; i++) {
            for (int j = 0; j < mapWidth; j++) {
                if (i == 0 || i == mapHeight - 1 || j == 0 || j == mapWidth - 1) {
                    charMap[i][j] = '.';
                } else {
                    charMap[i][j] = ' ';
                }
            }
        }
        //In cycles, the symbolic matrix is filled with objects of warriors and stuffs, based on their coordinates.
        for (Warriors warrior : warriors) {
            charMap[warrior.getX()][warrior.getY()] = warrior.getSymbol();
        }
        for (Stuff stuff : stuffs) {
            charMap[stuff.getX()][stuff.getY()] = stuff.getSymbol();
        }
        //Each getX(), getY() are variables, coordinates of the Warriors and Stuff classes
        return charMap;
    }
    //------------------------------------------------------------------------------------------------------------
    // Define the placeObject() method to place an object (warrior) at given coordinates.
    public void placeObject(Warriors object, int x, int y) {
        map[x][y] = object.getSymbol();
    }
    //To remove an object (warrior) from the given coordinates.
    public void removeObject(int x, int y) {
        map[x][y] = ' ';
    }
    //-------------------------------------------------------------------------------------------------------------

    // - Defining the placeChests() method for placement
    public void placeWarriors(){
        WarriorsPlacer warriorsPlacer = new WarriorsPlacer(this,warriors);
        warriorsPlacer.placeWarriors();
    }
    //this - in this context refers to the current Map object, which is the object on which the placeChests() method is called.
    public void placeChests() {
        StuffPlacer stuffPlacer = new StuffPlacer(this, stuffs);
        stuffPlacer.placeChests();
    }
    public void placeBomb() {
        StuffPlacer stuffPlacer = new StuffPlacer(this, stuffs);
        stuffPlacer.placeBomb();
    }
    //------------------------------------------------------------------------------------------------------------
    // Define a createMapAndStuff() method to create a map and place objects on it,
    // including unit, panzer, jedi, chest and bomb.
    public void createMapAndStuff() {
        //string map = new char[mapHeight][mapWidth]; - creates a new empty map,
        //represented by a two-dimensional character array,
        //with dimensions defined in mapHeight and mapWidth.
        map = new char[mapHeight][mapWidth];
        //Game logic can use this empty map to check availability
        //certain coordinates for placing objects, avoiding conflicts and collisions.
        for (int i = 0; i < mapHeight; i++) {
            for (int j = 0; j < mapWidth; j++) {
                if (i == 0 || i == mapHeight - 1 || j == 0 || j == mapWidth - 1) {
                    map[i][j] = '.'; // Граница карты
                } else {
                    map[i][j] = ' '; // Пустое место
                }
            }
        }
        //Call methods placeWarriors(); placeChests(); and placeBomb();
        //for placing first aid kits and mines on the war map
        placeWarriors();
        placeChests();
        placeBomb();
    }
    //--------------------------------------------------------------------------------------------------------
    //In the printMap() method, first obtains a character representation of the map (charMap)
    //by calling the getCharMap() method.Then the character matrix is printed to the console.
    public void printMap() {
        char[][] charMap = getCharMap();
        for (int i = 0; i < mapHeight; i++) {
            for (int j = 0; j < mapWidth; j++) {
                System.out.print(charMap[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    //--------------------------------------------------------------------------------------------------------
    //Definition of the getWarriorAtLocation() method for searching and obtaining a warrior
    //at specified coordinates and return this object
    public Warriors getWarriorAtLocation(int x, int y) {
        for (Warriors warrior : warriors) {
            if (warrior.getX() == x && warrior.getY() == y) {
                return warrior;
            }
        }
        return null;
    }
    //-----------------------------------------------------------------------------------------------------------
    //Method is designed to search and return a bomb object (Bomb type)
    // at the specified coordinates (x, y) on the game map.
    public Bomb getBombAtLocation(int x, int y) {
        for (Stuff stuff : stuffs) {
            if (stuff instanceof Bomb && stuff.getX() == x && stuff.getY() == y) {
                return (Bomb) stuff;
            }
        }
        return null;
    }
    //In this context, the instanceof operator checks whether the stuff
    //object is an instance of the Bomb class or a subclass of it.
    //This method allows you to search for and obtain a bomb object at specified coordinates on the game map.
    //for further use and definition of the logic for working with Bomb
    //---------------------------------------------------------------------------------------------------------
    //Method is designed to search and return a first aid kit object
    //(Chest type) at the specified coordinates (x, y) on the game map same as getBombAtLocation
    public Chest getHealingPotionAtLocation(int x, int y) {
        for (Stuff stuff : stuffs) {
            if (stuff instanceof Chest && stuff.getX() == x && stuff.getY() == y) {
                return (Chest) stuff;
            }
        }
        return null;
    }
}
//----------DIFFERENCE IN THE METHODS createMapAndStuff() and getCharMap()---------------------------

//The createMapAndStuff() and getCharMap() methods perform different functions in the Map class.

//createMapAndStuff(): This method is responsible for initializing the game map and placing
//game objects (warriors, items, etc.) on this map. It creates a new empty card
//and places the initial game objects on it.
// This method should be called once at the start of the game to prepare the game world.

//getCharMap(): This method provides the current character representation of the game map as a character matrix.
// It does not change the state of the map, but only returns its current state in text form.
// This is useful for example for displaying a map in a UI, debugging, or analysis.

//So, createMapAndStuff() is used to create and initially configure the game map,
//while getCharMap() provides the current state of the map without making changes.
// Both methods can be useful in different scenarios, and they serve different functions in the context of the game.
//provides the current state of the map without making changes. Both methods can be
// are useful in different scenarios, and they serve different functions in the context of the game.
//-----------------------------------------------------------------------------------------------------