package game_simulator.coordinates2;

public class CoordinatesShift {
    //Class describing the horizontal and vertical shift
    public final int fileShift;
    public final int rankShift;

    public CoordinatesShift(int fileShift, int rankShift) {
        this.fileShift = fileShift;
        this.rankShift = rankShift;
    }
}
