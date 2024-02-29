package game_simulator.stuff1;

public abstract class Stuff {
    private int x;
    private int y;                // Abstract class for placing bonus objects
    private final int power;
    private char symbol;

    public Stuff(int power, char symbol) {
        this.power = power;
        this.symbol = symbol;
    }

    public int getPower() {
        return power;
    }
    public char getSymbol() {
        return symbol;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
}
