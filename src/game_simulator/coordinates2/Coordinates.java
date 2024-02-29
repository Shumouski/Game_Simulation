package game_simulator.coordinates2;

public class Coordinates {
    public File file;
    public Integer rank;

    public Coordinates(File file, Integer rank) {
        this.file = file;
        this.rank = rank;
    }
    //Shift method calculates new coordinates, shifted by the specified values using the shift parameter.
    public Coordinates Shift(CoordinatesShift shift) {
        int newFileIndex = (this.file.ordinal() + shift.fileShift + File.values().length) % File.values().length;
        int newRank = this.rank + shift.rankShift;

        return new Coordinates(File.values()[newFileIndex], newRank);
    }
    //canShift method checks whether it is possible to shift by the specified values without going off the board.
    public boolean canShift(CoordinatesShift shift){
        int f = file.ordinal() + shift.fileShift;
        int r = rank + shift.rankShift;

        if (f < 0 || f >= File.values().length) return false;
        if (r < 1 || r > 8) return false;

        return true;
    }
    //distanceTo method calculates the distance between the current coordinates and the transmitted coordinates
    //using the classic formula for the distance between two points in a Cartesian coordinate system.
    public double distanceTo(Coordinates other) {
        int deltaX = this.file.ordinal() - other.file.ordinal();
        int deltaY = this.rank - other.rank;
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "file " + file +
                ", rank " + rank +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinates that = (Coordinates) o;

        if(file != that.file)return false;
        return rank.equals(that.rank);
    }

    @Override
    public int hashCode() {
        int result = file.hashCode();
        result = 31 * result + rank.hashCode();
        return result;
    }
}
