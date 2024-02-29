package game_simulator.coordinates2;

public class CoordinatesShift {
    //Класс описывающий сдвиг горизонтали и вертикали
    public final int fileShift;
    public final int rankShift;

    public CoordinatesShift(int fileShift, int rankShift) {
        this.fileShift = fileShift;
        this.rankShift = rankShift;
    }
}
