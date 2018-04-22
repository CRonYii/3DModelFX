package ca.bcit.cst.rongyi;

import java.util.Arrays;

/**
 * The matrix can do adds and MULTIPLICATION!.
 *
 * @author Rongyi Chen
 * @version 2017
 */
public class Matrix {

    /**
     * matrix represented by a 2D array.
     */
    private double[][] matrix;

    /**
     * Constructs an object of type ca.bcit.cst.rongyi.Matrix.
     *
     * @param rows    int
     * @param columns int
     */
    public Matrix(int rows, int columns) {
        if (rows <= 0 || columns <= 0) {
            throw new IllegalArgumentException("Must not be zero or negative.");
        }
        matrix = new double[rows][columns];
    }

    public Matrix(Matrix m) {
        double[][] matrix = m.getMatrix();
        this.matrix = new double[matrix.length][];
        for (int i = 0; i < matrix.length; i++) {
            this.matrix[i] = Arrays.copyOf(matrix[i], matrix[i].length);
        }
    }

    /**
     * Constructs an object of type ca.bcit.cst.rongyi.Matrix.
     *
     * @param matrix int[][]
     */
    public Matrix(double[][] matrix) {
        for (int i = 0; i < matrix.length - 1; i++) {
            if (matrix[i].length != matrix[i + 1].length) {
                throw new IllegalArgumentException(
                        "Each row must have identical length.");
            }
        }
        this.matrix = matrix;
    }

    public double[] getRow(int index) {
        return matrix[index];
    }

    public double[] getColumn(int index) {
        double[] column = new double[numberOfRows()];
        for (int i = 0; i < column.length; i++) {
            column[i] = getValue(i, index);
        }
        return column;
    }

    /**
     * Returns how many rows the matrix has.
     *
     * @return int
     */
    public int numberOfRows() {
        return matrix.length;
    }

    /**
     * Return how many columns the matrix has.
     *
     * @return int
     */
    public int numberOfColumns() {
        if (numberOfRows() == 0) {
            return 0;
        }
        return matrix[0].length;
    }

    /**
     * Returns the value at the given position.
     *
     * @param row    int
     * @param column int
     * @return int
     */
    public double getValue(int row, int column) {
        return matrix[row][column];
    }

    public double[][] to2DArray() {
        return matrix;
    }

    /**
     * Sets the value to the given position.
     *
     * @param row    int
     * @param column int
     * @param value  int
     */
    public void setValue(int row, int column, double value) {
        matrix[row][column] = value;
    }

    /**
     * Returns a matrix that is the sum of this and the passedin matrix.
     *
     * @param another ca.bcit.cst.rongyi.Matrix
     * @return ca.bcit.cst.rongyi.Matrix
     */
    public Matrix add(Matrix another) {
        if (this.numberOfRows() != another.numberOfRows()
                || this.numberOfColumns() != another.numberOfColumns()) {
            throw new IllegalArgumentException(
                    "The two matrices are not the same size");
        }
        Matrix added = new Matrix(numberOfRows(), numberOfColumns());
        for (int r = 0; r < numberOfRows(); r++) {
            for (int c = 0; c < numberOfColumns(); c++) {
                added.matrix[r][c] = matrix[r][c] + another.matrix[r][c];
            }
        }

        return added;
    }

    /**
     * Returns a matrix that is the product of the two matrices.
     *
     * @param another ca.bcit.cst.rongyi.Matrix
     * @return ca.bcit.cst.rongyi.Matrix
     */
    public Matrix multiply(Matrix another) {
        if (this.numberOfColumns() != another.numberOfRows()) {
            throw new IllegalArgumentException("The sizes does not"
                    + " meet the requirment for multiplication: " + this.numberOfColumns() + ", " + another.numberOfRows());
        }
        Matrix result = new Matrix(numberOfRows(), another.numberOfColumns());
        for (int r = 0; r < numberOfRows(); r++) {
            for (int i = 0; i < another.numberOfColumns(); i++) {
                double sum = 0;
                for (int c = 0; c < numberOfColumns(); c++) {
                    sum += matrix[r][c] * another.matrix[c][i];
                }
                result.matrix[r][i] = sum;
            }
        }
        return result;
    }

    public double[][] getMatrix() {
        return matrix;
    }

    @Override
    public String toString() {
        String str = "";
        for (int r = 0; r < numberOfRows(); r++) {
            for (int c = 0; c < numberOfColumns(); c++) {
                str += matrix[r][c] + "\t";
            }
            str += "\n";
        }
        return str;
    }
}
