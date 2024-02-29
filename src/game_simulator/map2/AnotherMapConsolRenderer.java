package game_simulator.map2;
import game_simulator.coordinates2.Coordinates;
import game_simulator.coordinates2.File;
import game_simulator.color.Color;
import game_simulator.warriors2.JediOne;
import game_simulator.warriors2.PanzerOne;
import game_simulator.warriors2.UnitOne;
import game_simulator.warriors2.WarriorsOne;
public class AnotherMapConsolRenderer {
    public static final String ANSI_RESET = "\u001B[0m"; // to reset the color
    public static final String ANSI_BLUE_WARRIOR_COLOR = "\u001B[34m"; // Blue pieces
    public static final String ANSI_RED_WARRIOR_COLOR = "\u001B[31m"; // Red pieces
    public static final String ANSI_WHITE_SQUARE_BACKGROUND = "\u001B[47m"; // White background
    // This code generates a string that lists all the game map files with spaces between them.
    // So this piece of code serves to create a top label on the game map
    // that makes it easy to identify which file (column) is at which position.
    public void render(AnotherMap anotherMap) {
        // Display the top label (horizontal)
        System.out.print("  ");
        for (File file : File.values()) {
            System.out.print(" " + file.name() + " ");
        }
        System.out.println();

        // Display map with side label (vertical)
        for (int rank = 8; rank >= 1; rank--) {
            String line = rank + " ";
            for (File file : File.values()) {
                Coordinates coordinates = new Coordinates(file, rank);
                if (anotherMap.isSquareEmpty(coordinates)) {
                    line += getSpriteForEmptyPlace(coordinates);
                } else {
                    line += getWarriorSprite(anotherMap.getWarriors(coordinates));
                }
            }
            line += ANSI_RESET;
            System.out.println(line);
        }
    }
    // This method is designed to change the appearance of symbols depending on the color of the warrior
    // and the background color of the cell on the game card.
    private String colorizeSprite(String sprite, Color warriorColor, boolean isSquareDark) {
        // format = background color + font color + text
        String result = sprite;
        if (warriorColor == Color.BLUE) {
            result = ANSI_BLUE_WARRIOR_COLOR + result;
        } else {
            result = ANSI_RED_WARRIOR_COLOR + result;
        }
        if (isSquareDark) {
            result = ANSI_WHITE_SQUARE_BACKGROUND + result;
        } else {
            result = ANSI_WHITE_SQUARE_BACKGROUND + result;
        }
        return result;
    }
    // this method returns a character representing an empty cell, colored blue,
    // taking into account the background color, depending on whether the cell is dark or light.
    private String getSpriteForEmptyPlace(Coordinates coordinates) {
        return colorizeSprite("   ", Color.BLUE, AnotherMap.isSquareDark(coordinates));
    }
    // Setting the object symbol
    private String selectUnicodeSpriteForWarriors(WarriorsOne warriorsOne) {
        if (warriorsOne instanceof UnitOne) {
            return "●";
        } else if (warriorsOne instanceof PanzerOne) {
            return "▲";
        } else if (warriorsOne instanceof JediOne) {
            return "□";
        } else {
            System.out.println("\nThe sprite is not found\n");
            return "";
        }
    }
    // this method returns a symbol representing the warrior,
    // colored in his color and displayed against the background depending on whether the cell is dark or light.
    private String getWarriorSprite(WarriorsOne warriorsOne) {
        return colorizeSprite(" " + selectUnicodeSpriteForWarriors(warriorsOne) + " ", warriorsOne.color, AnotherMap.isSquareDark(warriorsOne.coordinates));
    }
}