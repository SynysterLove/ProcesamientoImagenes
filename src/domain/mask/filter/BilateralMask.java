package domain.mask.filter;

import core.provider.ServiceProvider;
import core.service.BilateralFunctionsService;
import core.service.MatrixService;
import domain.XYPoint;
import domain.customimage.RGB;
import domain.mask.Mask;


public class BilateralMask extends Mask {

    private final BilateralFunctionsService bilateralFunctionsService;
    private final double closenessSigma;
    private final double similaritySigma;
    private final MatrixService matrixService;

    public BilateralMask(int size, double closenessSigma, double similaritySigma) {
        super(Type.BILATERAL, size);
        this.createMatrix(size);
        this.bilateralFunctionsService = ServiceProvider.provideBilateralFunctionsService();
        this.matrixService = ServiceProvider.provideMatrixService();
        this.closenessSigma = closenessSigma;
        this.similaritySigma = similaritySigma;
    }


    @Override
    protected double[][] createMatrix(int size) {
        return new double[size][size];
    }

    public double[][] applyBilateralMask(double[][] channel) {
        Integer width = channel.length;
        Integer height = channel[0].length;
        double[][] filteredChannel = new double[width][height];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                filteredChannel[x][y] = applyMaskToPixel(channel, x, y);
            }
        }

        return filteredChannel;
    }

    /* Basic convolution segment algorithm */
    public double applyMaskToPixel(double[][] channel, int x, int y) {

        double value = 0;
        this.generateMaskValues(x, y, channel, closenessSigma, similaritySigma);

        for (int j = y - (size / 2); j <= y + (size / 2); j++) {
            for (int i = x - (size / 2); i <= x + (size / 2); i++) {

                if (this.matrixService.isPositionValid(channel.length, channel[0].length, i, j)) {

                    int maskColumn = j + (size / 2) - y;
                    int maskRow = i + (size / 2) - x;
                    double maskValue = this.matrix[maskRow][maskColumn];

                    value += channel[i][j] * maskValue * this.factor;
                }
                //Ignoring the invalid positions, is equal to do a zero-padding. We're averaging zeros
            }
        }

        return value;
    }


    private void generateMaskValues(int xCenter, int yCenter, double[][] channel, double closenessSigma,
                                    double similaritySigma) {

        XYPoint center = new XYPoint(xCenter, yCenter);
        double centerValue = channel[xCenter][yCenter];

        for (int j = yCenter - (size / 2); j <= yCenter + (size / 2); j++) {
            for (int i = xCenter - (size / 2); i <= xCenter + (size / 2); i++) {

                if (this.matrixService.isPositionValid(channel.length, channel[0].length, i, j)) {

                    XYPoint current = new XYPoint(i, j);
                    double currentValue = channel[i][j];
                    this.matrix[i][j] =
                            this.bilateralFunctionsService.calculateCloseness(center, current, closenessSigma)
                            * this.bilateralFunctionsService.calculateSimilarity(centerValue, currentValue, similaritySigma);

                } else this.matrix[i][j] = 0;
            }
            this.calculateFactor();

        }

    }

    private void calculateFactor() {

        double sum = 0;
        for (int i=0; i < size; i++) {
            for (int j=0; j < size; j++) {
                sum += this.matrix[i][j];
            }
        }
        this.factor = 1/sum;

    }

}