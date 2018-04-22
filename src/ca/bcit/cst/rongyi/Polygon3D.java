package ca.bcit.cst.rongyi;

import javafx.geometry.Point2D;
import javafx.geometry.Point3D;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;

/**
 * ca.bcit.cst.rongyi.Polygon3D
 *
 * @author Rongyi Chen
 * @version 2017
 */
public class Polygon3D {

    private Matrix matrix;
    private Paint color;

    /**
     * Constructs an object of type ca.bcit.cst.rongyi.Polygon3D.
     *
     * @param coordinates
     */
    public Polygon3D(double[][] coordinates) {
        if (coordinates.length != 0 && coordinates[0].length != 3) {
            throw new IllegalArgumentException("Must have 3 rows for x, y, z.");
        }
        setMatrix(new Matrix(coordinates));
    }

    /**
     * Constructs an object of type ca.bcit.cst.rongyi.Polygon3D.
     *
     * @param coordinates
     * @param color
     */
    public Polygon3D(double[][] coordinates, Paint color) {
        this(coordinates);
        this.color = color;
    }

    public void setMatrix(Matrix matrix) {
        this.matrix = matrix;
    }

    /**
     * Rotates the shape about x-axis, y-axis and z-axis at given radians.
     *
     * @param x amount to be rotated about x-axis in radian
     * @param y amount to be rotated about y-axis in radian
     * @param z amount to be rotated about z-axis in radian
     */
    public void rotate(double x, double y, double z) {
        Matrix matrix = new Matrix(this.matrix);
        matrix = matrix.multiply(getXRotationMatrix(x));
        matrix = matrix.multiply(getYRotationMatrix(y));
        matrix = matrix.multiply(getZRotationMatrix(z));
        setMatrix(matrix);
    }

    public Polygon toPolygon() {
        Polygon polygon = new Polygon();
        polygon.setFill(color);
        polygon.setStroke(color);
        Point2D[] points = pointsTo2DArray();
        for (int i = 0; i < points.length; i++) {
            Point2D point = points[i];
            polygon.getPoints().addAll(point.getX(), point.getY());
        }
        return polygon;
    }

    public Point3D getPoint(int index) {
        double[] coordinates = matrix.getRow(index);
        return new Point3D(coordinates[0], coordinates[1], coordinates[2]);
    }

    public Point3D[] pointsToArray() {
        Point3D[] arr = new Point3D[matrix.numberOfRows()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = getPoint(i);
        }
        return arr;
    }

    public Point2D get2DPoint(int index) {
        double[] coordinates = matrix.getRow(index);
        return new Point2D(coordinates[0], coordinates[1]);
    }

    public Point2D[] pointsTo2DArray() {
        Point2D[] arr = new Point2D[matrix.numberOfRows()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = get2DPoint(i);
        }
        return arr;
    }

    /**
     * Returns the matrix for this ca.bcit.cst.rongyi.Polygon3D.
     *
     * @return the matrix
     */
    public Matrix getMatrix() {
        return matrix;
    }

    /**
     * Returns a rotation matrix.
     *
     * @param angle double
     * @return ca.bcit.cst.rongyi.Matrix
     */
    public static Matrix getXRotationMatrix(double angle) {
        double[][] arr = {
                {1, 0, 0},
                {0, Math.cos(angle), -1 * Math.sin(angle)},
                {0, Math.sin(angle), Math.cos(angle)}};
        Matrix m = new Matrix(arr);
        return m;
    }

    /**
     * Returns a rotation matrix.
     *
     * @param angle double
     * @return ca.bcit.cst.rongyi.Matrix
     */
    public static Matrix getYRotationMatrix(double angle) {
        double[][] arr = {
                {Math.cos(angle), 0, Math.sin(angle)},
                {0, 1, 0},
                {-1 * Math.sin(angle), 0, Math.cos(angle)}
        };
        Matrix m = new Matrix(arr);
        return m;
    }

    /**
     * Returns a rotation matrix.
     *
     * @param angle double
     * @return ca.bcit.cst.rongyi.Matrix
     */
    public static Matrix getZRotationMatrix(double angle) {
        double[][] arr = {
                {Math.cos(angle), -1 * Math.sin(angle), 0},
                {Math.sin(angle), Math.cos(angle), 0},
                {0, 0, 1}
        };
        Matrix m = new Matrix(arr);
        return m;
    }

}
