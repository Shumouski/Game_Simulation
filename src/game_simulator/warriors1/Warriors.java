package game_simulator.warriors1;
import game_simulator.color.Color;
public abstract class Warriors {
    private int x;
    private int y;
    private Color color;
    private int health;
    private int power;
    private char symbol;

    public Warriors(Color color, int health, int power, char symbol) {       // Constructor
        this.color = color;
        this.health = health;
        this.power = power;
        this.symbol = symbol;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getHealth() {
        return health;
    }                                 // Getters and Setters for all variables

    public void setHealth(int health) {
        this.health = health;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
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

    public int getTotalScore() {
        return health + power;
    }
}