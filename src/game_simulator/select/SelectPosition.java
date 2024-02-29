package game_simulator.select;
import game_simulator.battle2.Begining2;
import game_simulator.battle2.Begining3;
import game_simulator.map2.AnotherMap;
import game_simulator.resolveWarriorsConflict.WarriorsConflict2;
import java.util.Scanner;
public class SelectPosition {
    public void toSelect2(){
        System.out.println("""
                1.Two players
                2.One player""");
        Scanner scanner = new Scanner(System.in);         // Calling the scanner
        try {                                             // Try block to catch invalid input from the console
            int command = scanner.nextInt();
            switch (command) {
                // First case for calling game with two players
                case 1:
                    AnotherMap anotherMap = new AnotherMap();
                    anotherMap.setUpPositions();  // Calling the method of installing objects on their positions
                    WarriorsConflict2 warriorsConflict2 = new WarriorsConflict2(anotherMap);
                    Begining3 begining = new Begining3(anotherMap, warriorsConflict2);
                    begining.toBegin3();  // Calling the main method
                    break;
                // Second case for playing with computer
                case 2:
                    AnotherMap anotherMap1 = new AnotherMap();
                    anotherMap1.setUpPositions();  // Calling the method of installing objects on their positions
                    WarriorsConflict2 warriorsConflict3 = new WarriorsConflict2(anotherMap1);
                    Begining2 begining1 = new Begining2(anotherMap1, warriorsConflict3);
                    begining1.toBegin();  // Calling the main method
                    break;
                default:
                    System.out.println("Invalid choice");
                    System.out.println();
                    SelectPosition selectPosition = new SelectPosition();
                    selectPosition.toSelect2();
            }
        } catch (java.util.InputMismatchException e) {
            System.out.println("Error! Please enter the number.\n");
            scanner.nextLine();
            SelectPosition selectPosition = new SelectPosition();
            selectPosition.toSelect2();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
