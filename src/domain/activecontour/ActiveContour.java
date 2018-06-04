package domain.activecontour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import domain.customimage.CustomImage;

public class ActiveContour {

    private static final int OBJECT_VALUE = -3;
    private static final int BACKGROUND_VALUE = 3;
    private static final int L_IN_VALUE = -1;
    private static final int L_OUT_VALUE = 1;

    private final Integer width;
    private final Integer height;
    private final int backgroundGrayAverage;
    private final int objectGrayAverage;
    private final List<XYPoint> lOut;
    private final List<XYPoint> lIn;
    private final SelectionSquare selectionSquare;
    private int[][] content;

    public ActiveContour(Integer width, Integer height, SelectionSquare selectionSquare, int backgroundGrayAverage, int objectGrayAverage) {
        this.width = width;
        this.height = height;
        this.selectionSquare = selectionSquare;
        this.backgroundGrayAverage = backgroundGrayAverage;
        this.objectGrayAverage = objectGrayAverage;

        int firstRow = selectionSquare.getFirstRow();
        int secondRow = selectionSquare.getSecondRow();
        int firstColumn = selectionSquare.getFirstColumn();
        int secondColumn = selectionSquare.getSecondColumn();

        this.lOut = addPoints(firstRow, secondRow, firstColumn, secondColumn);
        this.lIn = addPoints(firstRow + 1, secondRow - 1, firstColumn + 1, secondColumn - 1);
        this.content = initializeContent(firstRow + 2, secondRow - 2, firstColumn + 2, secondColumn - 2);
    }

    private ActiveContour(Integer width, Integer height, SelectionSquare selectionSquare, int backgroundGrayAverage, int objectGrayAverage,
            List<XYPoint> lOut, List<XYPoint> lIn, int[][] content) {
        this.width = width;
        this.height = height;
        this.selectionSquare = selectionSquare;
        this.backgroundGrayAverage = backgroundGrayAverage;
        this.objectGrayAverage = objectGrayAverage;

        this.lOut = lOut;
        this.lIn = lIn;
        this.content = content;
    }

    public static ActiveContour copy(ActiveContour activeContour) {
        return new ActiveContour(activeContour.getWidth(), activeContour.getHeight(), activeContour.getSelectionSquare(),
                activeContour.getBackgroundGrayAverage(), activeContour.getObjectGrayAverage(), new ArrayList<>(activeContour.getlOut()),
                new ArrayList<>(activeContour.getlIn()), Arrays.copyOf(activeContour.getContent(), activeContour.getContent().length));
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }

    public List<XYPoint> getlOut() {
        return lOut;
    }

    public List<XYPoint> getlIn() {
        return lIn;
    }

    public int getBackgroundGrayAverage() {
        return backgroundGrayAverage;
    }

    public int getObjectGrayAverage() {
        return objectGrayAverage;
    }

    private List<XYPoint> addPoints(int firstRow, int secondRow, int firstColumn, int secondColumn) {
        List<XYPoint> positions = new CopyOnWriteArrayList<>();

        for (int i = firstRow; i <= secondRow; i++) {
            positions.add(new XYPoint(i, firstColumn));
            positions.add(new XYPoint(i, secondColumn));
        }

        for (int i = firstColumn; i <= secondColumn; i++) {
            positions.add(new XYPoint(firstRow, i));
            positions.add(new XYPoint(secondRow, i));
        }

        return positions;
    }

    private int[][] initializeContent(int firstRowObject, int secondRowObject, int firstColumnObject, int secondColumnObject) {
        int matrix[][] = new int[width][height];

        // Fill matrix with background value
        for (int row = 0; row < width; row++) {
            for (int column = 0; column < height; column++) {
                matrix[row][column] = BACKGROUND_VALUE;
            }
        }

        // Set edges
        lIn.forEach(xyPoint -> matrix[xyPoint.getX()][xyPoint.getY()] = L_IN_VALUE);
        lOut.forEach(xyPoint -> matrix[xyPoint.getX()][xyPoint.getY()] = L_OUT_VALUE);

        // Set object
        for (int row = firstRowObject; row <= secondRowObject; row++) {
            for (int column = firstColumnObject; column <= secondColumnObject; column++) {
                matrix[row][column] = OBJECT_VALUE;
            }
        }
        return matrix;
    }

    public void moveInvalidLInToObject() {
        for (int i = 0; i < lIn.size(); i++) {
            XYPoint xyPoint = lIn.get(i);
            if (hasAllNeighborsWithValueLowerThanZero(xyPoint)) {
                lIn.remove(xyPoint);
                content[xyPoint.getX()][xyPoint.getY()] = OBJECT_VALUE;
            }
        }
    }

    public void moveInvalidLOutToBackground() {
        for (int i = 0; i < lOut.size(); i++) {
            XYPoint xyPoint = lOut.get(i);
            if (hasAllNeighborsWithValueHigherThanZero(xyPoint)) {
                lOut.remove(xyPoint);
                content[xyPoint.getX()][xyPoint.getY()] = BACKGROUND_VALUE;
            }
        }
    }

    private boolean hasAllNeighborsWithValueLowerThanZero(XYPoint xyPoint) {
        int row = xyPoint.getX();
        int column = xyPoint.getY();
        return hasValueLowerThanZero(row - 1, column) &&
                hasValueLowerThanZero(row + 1, column) &&
                hasValueLowerThanZero(row, column - 1) &&
                hasValueLowerThanZero(row, column + 1);
    }

    private boolean hasAllNeighborsWithValueHigherThanZero(XYPoint xyPoint) {
        int row = xyPoint.getX();
        int column = xyPoint.getY();
        return hasValueHigherThanZero(row - 1, column) &&
                hasValueHigherThanZero(row + 1, column) &&
                hasValueHigherThanZero(row, column - 1) &&
                hasValueHigherThanZero(row, column + 1);
    }

    private boolean hasValueLowerThanZero(int row, int column) {
        return hasValidPosition(row, column) && content[row][column] < 0;
    }

    private boolean hasValueHigherThanZero(int row, int column) {
        return hasValidPosition(row, column) && content[row][column] > 0;
    }

    public Set<XYPoint> getNeighbors(XYPoint xyPoint) {
        Set<XYPoint> neighbors = new HashSet<>();
        int row = xyPoint.getX();
        int column = xyPoint.getY();
        neighbors.add(new XYPoint(row - 1, column));
        neighbors.add(new XYPoint(row + 1, column));
        neighbors.add(new XYPoint(row, column - 1));
        neighbors.add(new XYPoint(row, column + 1));
        return neighbors;
    }

    public boolean hasValidPosition(int row, int column) {
        boolean rowIsValid = row < this.width && 0 <= row;
        boolean columnIsValid = column < this.height && 0 <= column;
        return rowIsValid && columnIsValid;
    }

    public boolean belongToBackground(XYPoint xyPoint) {
        return hasValue(xyPoint, BACKGROUND_VALUE);
    }

    public boolean belongToObject(XYPoint xyPoint) {
        return hasValue(xyPoint, OBJECT_VALUE);
    }

    private boolean hasValue(XYPoint xyPoint, int value) {
        return content[xyPoint.getX()][xyPoint.getY()] == value;
    }

    public void addLInToMatrix(XYPoint xyPoint) {
        content[xyPoint.getX()][xyPoint.getY()] = L_IN_VALUE;
    }

    public void addLOutToMatrix(XYPoint xyPoint) {
        content[xyPoint.getX()][xyPoint.getY()] = L_OUT_VALUE;
    }

    public void addLIn(List<XYPoint> toAddToLIn) {
        lIn.addAll(toAddToLIn);
    }

    public void removeLIn(List<XYPoint> toRemoveFromLIn) {
        lIn.removeAll(toRemoveFromLIn);
    }

    public void addLOut(List<XYPoint> toAddToLOut) {
        lOut.addAll(toAddToLOut);
    }

    public void removeLOut(List<XYPoint> toRemoveFromLOut) {
        lOut.removeAll(toRemoveFromLOut);
    }

    private SelectionSquare getSelectionSquare() {
        return selectionSquare;
    }

    private int[][] getContent() {
        return content;
    }

    public boolean hasAllValidLInPoints(CustomImage customImage) {
        return lIn.stream().allMatch(xyPoint -> {
            boolean b = hasAllNeighborsWithFdFunctionValueHigherThanZero(xyPoint, customImage);
            System.out.println("object point: " + "x= " + xyPoint.getX() + " y= " + xyPoint.getY() + " result= " + b);
            return b;
        });
    }

    public boolean hasAllValidLOutPoints(CustomImage customImage) {
        return lOut.stream().allMatch(xyPoint -> {
            boolean b = hasAllNeighborsWithFdFunctionValueLowerThanZero(xyPoint, customImage);
            System.out.println("background point: " + "x= " + xyPoint.getX() + " y= " + xyPoint.getY() + " result= " + b);
            return b;
        });
    }

    private boolean hasAllNeighborsWithFdFunctionValueHigherThanZero(XYPoint xyPoint, CustomImage customImage) {
        int row = xyPoint.getX();
        int column = xyPoint.getY();
        return hasFdFunctionValueHigherThanZero(row - 1, column, customImage) &&
                hasFdFunctionValueHigherThanZero(row + 1, column, customImage) &&
                hasFdFunctionValueHigherThanZero(row, column - 1, customImage) &&
                hasFdFunctionValueHigherThanZero(row, column + 1, customImage);
    }

    private boolean hasAllNeighborsWithFdFunctionValueLowerThanZero(XYPoint xyPoint, CustomImage customImage) {
        int row = xyPoint.getX();
        int column = xyPoint.getY();
        return hasFdFunctionValueLowerThanZero(row - 1, column, customImage) &&
                hasFdFunctionValueLowerThanZero(row + 1, column, customImage) &&
                hasFdFunctionValueLowerThanZero(row, column - 1, customImage) &&
                hasFdFunctionValueLowerThanZero(row, column + 1, customImage);
    }

    private boolean hasFdFunctionValueHigherThanZero(int row, int column, CustomImage customImage) {
        boolean checkFdFunction = FdFunction.classicHigher(customImage.getAverageValue(row, column), objectGrayAverage, backgroundGrayAverage);
        return hasValidPosition(row, column) && checkFdFunction;
    }

    private boolean hasFdFunctionValueLowerThanZero(int row, int column, CustomImage customImage) {
        boolean checkFdFunction = FdFunction.classicLower(customImage.getAverageValue(row, column), objectGrayAverage, backgroundGrayAverage);
        return hasValidPosition(row, column) && checkFdFunction;
    }
}
