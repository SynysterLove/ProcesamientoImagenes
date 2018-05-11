package domain.mask.derivativedirectionaloperator.sobel;

import domain.mask.Mask;

public class SobelVerticalStraightMask extends Mask {

    public SobelVerticalStraightMask() {
        super(Type.DERIVATE_DIRECTIONAL_OPERATOR_KIRSH, AVAILABLE_SIZE);

        this.matrix = createMatrix(AVAILABLE_SIZE);
    }

    @Override
    protected double[][] createMatrix(int size) {
        return new double[][]{
                {1, 0, -1},
                {2, 0, -2},
                {1, 0, -1}
        };
    }

    @Override
    public double getValue(int x, int y) {
        return matrix[x][y];
    }
}