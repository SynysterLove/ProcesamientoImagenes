package domain.mask.derivativedirectionaloperator.prewitt;

import domain.mask.Mask;

public class PrewittHorizontalStraightMask extends Mask {

    public PrewittHorizontalStraightMask() {
        super(Type.DERIVATE_DIRECTIONAL_OPERATOR_KIRSH, AVAILABLE_SIZE);

        this.matrix = createMatrix(AVAILABLE_SIZE);
    }

    @Override
    protected double[][] createMatrix(int size) {
        return new double[][]{
                { 1,  1,  1},
                { 0,  0,  0},
                {-1, -1, -1}
        };
    }

    @Override
    public double getValue(int x, int y) {
        return matrix[x][y];
    }
}