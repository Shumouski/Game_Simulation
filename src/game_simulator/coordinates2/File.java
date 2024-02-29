package game_simulator.coordinates2;

public enum File {
    A, B, C, D, E, F, G, H;
    //The method allows you to get the value of the File enumeration based on
    // the character (letter) that represents the file.
    public static File fromChar(char c) {
        try {
            return File.valueOf(String.valueOf(c).toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
    }
