package game_simulator.battleDescription;

public class DescriptionOfBattle {
    public static void toWatchDescription() throws InterruptedException {
        String message = "You have the choice to take part in two battles. The cards represent two simulators.\n" +
                "1. The first is a simulator of two teams, which provides the opportunity to see the random movement of objects.\n" +
                "In this simulator, objects are placed randomly on the map.\n" +
                "Several first aid kits and bombs are also placed to intervene in the confrontation.\n" +
                "Objects have health and strength which adds up to represent the object's total stamina.\n" +
                "As a result of two objects from different teams getting into the same position, endurance is compared.\n" +
                "The winner is the team that is the first to destroy the objects of the opposing team.\n" +
                "Object designation: UNIT - ● and ⦿\n" +
                "PANZER - T and Y\n" +
                "JEDI - J and S\n" +
                "CHEST - ⨁ and BOMB - ⏣\n" +
                "In the startBattle method of the Beginning1 class in Thread.sleep(400)\n" +
                "-> 400 you can configure the map update delay exactly as you wish"+
                "2. The second card is also presented in the form of a card similar to a chessboard, only one color.\n" +
                "Objects are distinguished not by shapes, but by color, located on the battlefield in different parts of the map (similar to the location of chess).\n" +
                "This battle allows the player to control it against the computer.\n" +
                "UNIT near-field objects in the form of ● - have health points (1) and strength points (2) - which together give overall endurance (3).\n" +
                "PANZER objects in the form of ▲ - have health points (2) and strength points (3) - which together gives (5).\n" +
                "JEDI objects in the form of □ - have health (3) and strength (3) - which together gives (6).";

        for (int i = 0; i<message.length(); i++){
            System.out.print(message.charAt(i));
            Thread.sleep(5);
        }
    }
}
