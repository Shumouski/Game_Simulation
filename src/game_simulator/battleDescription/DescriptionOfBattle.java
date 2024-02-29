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
                "CHEST - + and BOMB - o\n" +
                "In the startBattle method of the Beginning1 class in Thread.sleep(400)\n" +
                "-> 400 you can configure the map update delay exactly as you wish"+
                "2. The second card is also presented in the form of a card similar to a chessboard, only one color.\n" +
                "Objects are distinguished not by shapes, but by color, located on the battlefield in different parts of the map (similar to the location of chess).\n" +
                "This battle allows the player to control it against the computer.\n" +
                "Near field UNIT objects in the form of ● - have health points (5) and strength points (2), which together give a total stamina (7).\n" +
                "PANZER objects in the form of ▲ - have health points (5) and strength points (4) - which together gives (9).\n" +
                "Jedi objects in the form of □ - have health (5) and strength (6) - which together gives (11).\n" +
                "Objects of the UNIT type have the ability to move in all directions (vertical, horizontal and diagonal) on one cell.\n" +
                "Objects of the PANZER type have the ability to walk (vertically) both on one cell and on two, in other directions only on one\n" +
                "Objects of the JEDI type have the ability to move (vertically, horizontally) both on one cell and on two, diagonally only on one.\n" +
                "The code is original, written on the idea of creating chess by simplifying moves and changing the logic of the game.\n" +
                "There may be a coincidence between the implementation of the code and the idea of the game Chess.";

        for (int i = 0; i<message.length(); i++){
            System.out.print(message.charAt(i));
            Thread.sleep(5);
        }
    }
}
