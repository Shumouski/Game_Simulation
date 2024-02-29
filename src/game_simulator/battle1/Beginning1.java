package game_simulator.battle1;
import game_simulator.battleIsOver.BattleOver1;
import game_simulator.map1.Map;
import game_simulator.resolveBombConflict1.BombConflict;
import game_simulator.resolveWarriorsConflict.WarriorsConflict1;
import game_simulator.stuff1.Bomb;
import game_simulator.stuff1.Chest;
import game_simulator.select.SelectSimulation;
import game_simulator.warriors1.Warriors;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Beginning1 {
    private Map map;
    //Private boolean field isRunning that determines whether a battle is currently running.
    //Default is set to true, which means the battle has started and is ongoing.
    private boolean isRunning = true;
    public Beginning1(Map map) {         //Конструктор класса Beginning, который принимает экземпляр класса Map в качестве аргумента и инициализирует поле map.
        this.map = map;
    }
    public void startBattle() throws InterruptedException {
        map.createMapAndStuff();  //Calls the initial state of the map with placed objects
        map.printMap();           //Calls a method that displays the initial state of the map to the console.

        Random random = new Random();

        BattleOver1 battleOver = new BattleOver1(map);
        BombConflict bombConflict = new BombConflict(map);
        WarriorsConflict1 warriorsConflict = new WarriorsConflict1(map);

        //WhiLe -> starts an infinite loop that continues until
        //isRunning is true and the isBattleOver() method returns false.
        //In this cycle, the state of the map and the actions of the warriors are updated.
        while (isRunning && !battleOver.isBattleOver()) {
            //Inside the loop, a copy of the list of warriors is created,
            //to avoid errors when changing the list of warriors inside the loop.
            List<Warriors> warriorsCopy = new ArrayList<>(map.getWarriors());
            for (Warriors warrior : warriorsCopy) {
                int x, y, newX, newY;

                //The block of code is synchronized -> executed synchronously with the warrior object.
                //This means that only one thread can execute this block of code for a given warrior at a given time.
                //This is important to avoid conflicts when simultaneously accessing
                //warrior coordinates from multiple threads
                synchronized (warrior) {
                    x = warrior.getX();
                    y = warrior.getY();
                }
                do {
                    int direction = random.nextInt(4);

                    switch (direction) {        //Generate a random direction (direction)
                        case 0: // Up
                            newX = x - 1;
                            newY = y;
                            break;
                        case 1: // Down
                            newX = x + 1;
                            newY = y;
                            break;
                        case 2: // Left
                            newX = x;
                            newY = y - 1;
                            break;
                        case 3: // Right
                            newX = x;
                            newY = y + 1;
                            break;
                        default:      //If there is no available space, the object remains at its coordinates
                            newX = x;
                            newY = y;
                    }
                    //So the do-while loop will continue (or repeat) until the new coordinates
                    //newX and newY are inside the map boundaries. As soon as this condition is met
                    //(the new coordinates will be within the map),
                    //the loop will end and control will be passed on to the code.
                    //This ensures that the new coordinates for moving the warrior will be
                    // within the game map and will not go beyond its boundaries.
                } while (newX < 1 || newX >= map.getMapHeight() - 1 || newY < 1 || newY >= map.getMapWidth() - 1);
                //char currentOccupant; -> A currentOccupant variable is created that will store the symbol
                //representing the contents of the map cell that the warrior will try to move to.
                char currentOccupant;
                Chest chest = null;
                synchronized (map) {
                    currentOccupant = map.getCharMap()[newX][newY];
                    if (currentOccupant == ' ') {
                        synchronized (warrior) {
                            map.placeObject(warrior, newX, newY);
                            map.removeObject(x, y);
                            warrior.setX(newX);
                            warrior.setY(newY);
                        }
                        //If the contents of the cell are a bomb symbol, then the warrior landed on a bomb.
                        //In this case, the resolveBombExplosion method is called to handle the bomb explosion.
                        //The warrior got to the bomb position, we process the explosion
                    } else if (currentOccupant == 'o') {
                        Bomb bomb = map.getBombAtLocation(newX, newY);
                        if (bomb != null) {
                            bombConflict.resolveBombExplosion(bomb, warrior);
                        }
                    } else if (currentOccupant == '+') {
                        chest = map.getHealingPotionAtLocation(newX, newY);
                    } else {
                        Warriors occupant = map.getWarriorAtLocation(newX, newY);
                        //This checks to see if there is an occupant in the new position (if not null)
                        //and if it is the same color as the current warrior. If this is so,
                        //then a conflict has been detected between warriors of different teams.
                        //If a conflict is detected, the resolveConflict(warrior, occupant);
                        //method is called, which handles the situation.
                        if (occupant != null && !occupant.getColor().equals(warrior.getColor())) {
                            warriorsConflict.resolveWarriorsConflict(warrior, occupant);
                        } else {
                            newX = x;
                            newY = y;
                        }
                    }
                }
                if (chest != null) {
                    synchronized (map) {
                        map.removeObject(newX, newY);
                        map.getStuffs().remove(chest);
                        int currentHealth = warrior.getHealth();
                        int healingPower = chest.getPower();
                        warrior.setHealth(currentHealth + healingPower);
                    }
                }
            }
            //The method is responsible for displaying the current state of the map on the screen.
            //Thus, after all the warriors have completed their actions, an updated map is displayed on the screen,
            //showing the new positions of the warriors and other objects.
            map.printMap();
            try {
                Thread.sleep(400);
                //This adds a delay between moves
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Battle is over");
        BattleOver1 battle = new BattleOver1(map);
        battle.toWinOfTeam();
        System.out.println();
        SelectSimulation simulation = new SelectSimulation();
        simulation.toSelect();
    }
}
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
