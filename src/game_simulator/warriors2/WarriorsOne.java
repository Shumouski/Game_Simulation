package game_simulator.warriors2;
import game_simulator.color.Color;
import game_simulator.coordinates2.Coordinates;
import game_simulator.coordinates2.CoordinatesShift;
import game_simulator.map2.AnotherMap;
import java.util.*;
public abstract class WarriorsOne {
    public final Color color;
    public Coordinates coordinates;                   // Abstract class where we create warriors
    private int health;
    private int power;

    public WarriorsOne(Color color, Coordinates coordinates, int health, int power ) {
        this.color = color;
        this.coordinates = coordinates;               // Constructor
        this.health = health;
        this.power = power;
    }

    public int getPower() {                           // Getter for power
        return power;
    }
    public Color getColor() {                         // Getter for color
        return this.color;
    }
    public Coordinates getCoordinates() {             // Getter for coordinates
        return coordinates;
    }
    public int getTotalScore() {                      // Getter for totalScore
        return health + power;
    }
    public int decrementTotalScore(){                 // Method for subtracting health
        this.health--;
        return getTotalScore();
    }
    // This getAvailableMoveSquare() method returns a set of coordinates (Set<Coordinates>) representing the available moves for a piece at a specific position on the map.
    // It then iterates through each offset and checks whether it is possible to make that offset from the shape's current position (represented by the coordinates object).
    // If possible, it shifts the coordinates and checks whether the square at the new coordinates is available to move using the isSquareAvailableMove() method.
    // If the square is available, the new coordinates are added to the result. At the end, the method returns the set of all available coordinates for the move.
    public Set<Coordinates> getAvailableMoveSquare(AnotherMap anotherMap) {
        Set<Coordinates> result = new HashSet<>();
        Set<CoordinatesShift> pieceMove = getPieceMove();

        if (pieceMove != null) {
            for (CoordinatesShift shift : pieceMove) {
                if (coordinates.canShift(shift)) {
                    Coordinates newCoordinates = coordinates.Shift(shift);

                    if (isSquareAvailableMove(newCoordinates, anotherMap)) {
                        result.add(newCoordinates);
                    }
                }
            }
        }
        return result;
    }
    // This method checks whether a certain square is available for a move.
    // It takes the coordinates of the square to be checked (coordinates) and an AnotherMap object representing the map
    private boolean isSquareAvailableMove(Coordinates coordinates, AnotherMap anotherMap){
        WarriorsOne warriorsOne = anotherMap.getWarriors(coordinates);
        return anotherMap.isSquareEmpty(coordinates) || (warriorsOne != null && warriorsOne.color != color);
    }
    public abstract Set<CoordinatesShift> getPieceMove();
}
   // getPieceMove() - еo determine available moves
   // This method is implemented by subclasses
   // getPieceMove() - returns a set of shifts (CoordinatesShift) that the pieces can make.
