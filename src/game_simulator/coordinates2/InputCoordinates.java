package game_simulator.coordinates2;
import game_simulator.color.Color;
import game_simulator.map2.AnotherMap;
import game_simulator.warriors2.WarriorsOne;
import java.util.Scanner;
import java.util.Set;

public class InputCoordinates {
    static Scanner scanner = new Scanner(System.in);
    public static Coordinates input(){
        while (true){
            System.out.println("Please enter coordinates (ex. a1)");
            String line = scanner.nextLine();
            if(line.length() != 2){
                System.out.println("Invalid format");
                continue;
            }
            char fileChar = line.charAt(0);
            char rankChar = line.charAt(1);

            if(!Character.isLetter(fileChar)){
                System.out.println("Invalid format");
                continue;
            }
            if(!Character.isDigit(rankChar)){
                System.out.println("Invalid format");
                continue;
            }
            int rank = Character.getNumericValue(rankChar);
            if(rank < 1 || rank > 8){
                System.out.println("Invalid format");
                continue;
            }
            File file = File.fromChar(fileChar);
            if(file == null){
                System.out.println("Invalid format");
                continue;
            }
            return new Coordinates(file, rank);
        }
    }
    public static Coordinates inputWarriorsOnecoordinatesForColor(Color color, AnotherMap anotherMap){

        while (true){
            System.out.println("Enter coordinates for a warrior to move");
            Coordinates coordinates = input();

            if(anotherMap.isSquareEmpty(coordinates)){
                System.out.println("Empty square");
                continue;
            }
            WarriorsOne warriorsOne = anotherMap.getWarriors(coordinates);
            if(warriorsOne.color != color){
                System.out.println("Wrong color");
                continue;
            }
            Set<Coordinates> availableMoveSquare = warriorsOne.getAvailableMoveSquare(anotherMap);
            if(availableMoveSquare.size()  == 0){
                System.out.println("Warrior is blocked");
                continue;
            }
            return coordinates;
        }
    }
    public static Coordinates inputAvailableSquare(Set<Coordinates>coordinates, AnotherMap anotherMap, Color color){
        while (true){
            System.out.println("Enter your move to selected warrior");
            Coordinates input = input();
            if(!coordinates.contains(input)){
                System.out.println("Non-available square");
                continue;
            }
            if (anotherMap.isSquareOccupiedBySameColor(input, color)) {
                System.out.println("Cannot move to a square occupied by a warrior of the same color");
                continue;
            }
            return input;
        }
    }
}
