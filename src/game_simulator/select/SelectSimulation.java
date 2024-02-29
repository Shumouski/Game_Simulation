package game_simulator.select;
import game_simulator.battle1.Beginning1;
import game_simulator.map1.Map;
import java.util.Scanner;
import static game_simulator.battleDescription.DescriptionOfBattle.toWatchDescription;
public class SelectSimulation {
    public void toSelect() throws InterruptedException {
        String message = "Welcome to the Battle System.\n" +
                "Select which battle you want to get?";
        for (int i = 0; i < message.length(); i++) {
            System.out.print(message.charAt(i));
            Thread.sleep(10);
        }
        System.out.println();
        System.out.println();
        System.out.println("""
                1.Enter the first battle
                2.Enter the second battle
                3.See battle description""");
        Scanner scanner = new Scanner(System.in);
        try {   // Try block to catch invalid input from the console
            int command = scanner.nextInt();
            switch (command) {
                // First case for calling first game simulation
                case 1:
                    Map map = new Map();
                    Beginning1 beginning = new Beginning1(map);
                    beginning.startBattle();
                    break;
                // Second case next select
                case 2:
                    SelectPosition selectPosition = new SelectPosition();
                    selectPosition.toSelect2();
                    break;
                // This case for watching descriptions
                case 3:
                    toWatchDescription();
                default:
                    System.out.println("Invalid choice");
                    System.out.println();
                    SelectSimulation simulation = new SelectSimulation();
                    simulation.toSelect();
            }
        } catch (java.util.InputMismatchException e) {
            System.out.println("Error! Please enter the number.\n");
            scanner.nextLine();
            SelectSimulation simulation = new SelectSimulation();
            simulation.toSelect();
        }
    }
}
