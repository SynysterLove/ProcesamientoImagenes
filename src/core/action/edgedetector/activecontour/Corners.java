package core.action.edgedetector.activecontour;

public class Corners {

    private final int firstRow;
    private final int secondRow;
    private final int firstColumn;
    private final int secondColumn;

    public Corners(int firstRow, int secondRow, int firstColumn, int secondColumn) {
        this.firstRow = firstRow;
        this.secondRow = secondRow;
        this.firstColumn = firstColumn;
        this.secondColumn = secondColumn;
    }

    public int getFirstRow() {
        return firstRow;
    }

    public int getSecondRow() {
        return secondRow;
    }

    public int getFirstColumn() {
        return firstColumn;
    }

    public int getSecondColumn() {
        return secondColumn;
    }
}