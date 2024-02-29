package game_simulator.resolveBombConflict1;
import game_simulator.map1.Map;
import game_simulator.stuff1.Bomb;
import game_simulator.warriors1.Warriors;

public class BombConflict {
    Map map;
    public BombConflict(Map map) {
        this.map = map;
    }
    // Method for resolving a conflict between an object and a bomb
    public void resolveBombExplosion(Bomb bomb, Warriors warrior) {
        // Transmit new coordinates and check if there is a bomb there
        int x = bomb.getX();
        int y = bomb.getY();
        // Provide thread synchronization and safe access to the map object.
        synchronized (map) {
            map.removeObject(x, y);
            map.getStuffs().remove(bomb);
            // Check if the warrior is in the list of warriors obtained from the map object
            if (map.getWarriors().contains(warrior)) {
                // We get the health of the war, the power of the bomb and set the war value
                // after interacting with it when entering a position
                int currentHealth = warrior.getHealth();
                int bombPower = bomb.getPower();
                warrior.setHealth(currentHealth - bombPower);
                // If a warrior's health becomes <= 0, remove him from the map and from the list of warriors
                if (warrior.getHealth() <= 0) {
                    map.removeObject(warrior.getX(), warrior.getY());
                    map.getWarriors().remove(warrior);
                }
            }
        }
    }
}
