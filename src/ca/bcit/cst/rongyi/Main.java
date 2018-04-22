package ca.bcit.cst.rongyi;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * @author Rongyi Chen
 * @version 1.0
 */
public class Main extends Application {

    public static final int APP_WIDTH = 800;
    public static final int APP_HEIGHT = 500;

    /**
     * amount to rotate per key pressed.
     */
    private static final double RADIAN_TO_ROTATE_PER_PRESSED = 1 * Math.PI / 180.0;
    /**
     * amount to rotate per pixel mouse dragged.
     */
    private static final double RADIAN_TO_ROTATE_PER_PIXEL = 2 * Math.PI / 50000.0;


    /**
     * the origin to rotate.
     */
    private Point3D origin = new Point3D(APP_WIDTH / 3, APP_HEIGHT / 3, 0.0);
    /**
     * The contents of the application scene.
     */
    private Group root;
    /**
     * The 3D model to be displayed
     **/
    private Model model = new Model(origin);
    /**
     * center point.
     */
    private Point2D center;

    /**
     * Displays an initially empty scene, waiting for the user to draw lines
     * with the mouse.
     *
     * @param primaryStage a Stage
     */
    public void start(Stage primaryStage) {
        setModel();

        root = new Group(model);
        Scene scene = new Scene(root, APP_WIDTH, APP_HEIGHT, Color.BLACK);
        scene.setOnMousePressed(this::processMousePress);
        scene.setOnMouseDragged(this::processMouseDragged);

        scene.setOnKeyPressed(new KeyEventHandler());

        primaryStage.setTitle("Equilateral Triangle");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void setModel() {
        double[][] base =
                {
                        {0, 0, 0},
                        {0, 200, 0},
                        {200, 200, 0},
                        {200, 0, 0},
                };
        double[][] face1 =
                {
                        {0, 0, 0},
                        {0, 200, 0},
                        {100, 100, 150}
                };
        double[][] face2 =
                {
                        {0, 0, 0},
                        {200, 0, 0},
                        {100, 100, 150}
                };
        double[][] face3 =
                {
                        {200, 200, 0},
                        {0, 200, 0},
                        {100, 100, 150}
                };
        double[][] face4 =
                {
                        {200, 200, 0},
                        {200, 0, 0},
                        {100, 100, 150}
                };
        model.addPolygon3D(new Polygon3D(base, Color.RED));
        model.addPolygon3D(new Polygon3D(face1, Color.BLUE));
        model.addPolygon3D(new Polygon3D(face2, Color.YELLOW));
        model.addPolygon3D(new Polygon3D(face3, Color.GREEN));
        model.addPolygon3D(new Polygon3D(face4, Color.PINK));
        model.drawPolygon();
    }

    /**
     * Set the center point of the triangle when mouse pressed.
     *
     * @param event mouse event
     */
    private void processMousePress(MouseEvent event) {
        center = new Point2D(event.getX(), event.getY());
    }

    /**
     * Method will be trigger when mouse dragged.
     *
     * @param event MouseEvent
     */
    private void processMouseDragged(MouseEvent event) {
        double x = event.getX() - center.getX();
        double y = center.getY() - event.getY();

        double radianPerPixel = RADIAN_TO_ROTATE_PER_PIXEL;

        rotatePolygon(y * radianPerPixel, -x * radianPerPixel, 0);
    }

    private void rotatePolygon(double x, double y, double z) {
        model.rotate(x, y, z);
    }

    private class KeyEventHandler implements EventHandler<KeyEvent> {
        @Override
        public void handle(KeyEvent event) {
            switch (event.getCode()) {
                case UP:
                    rotatePolygon(-RADIAN_TO_ROTATE_PER_PRESSED, 0, 0);
                    break;
                case DOWN:
                    rotatePolygon(RADIAN_TO_ROTATE_PER_PRESSED, 0, 0);
                    break;
                case LEFT:
                    rotatePolygon(0, -RADIAN_TO_ROTATE_PER_PRESSED, 0);
                    break;
                case RIGHT:
                    rotatePolygon(0, RADIAN_TO_ROTATE_PER_PRESSED, 0);
                    break;
                case A:
                    rotatePolygon(0, 0, RADIAN_TO_ROTATE_PER_PRESSED);
                    break;
                case D:
                    rotatePolygon(0, 0, -RADIAN_TO_ROTATE_PER_PRESSED);
                    break;
                default:
                    break;
            }

        }
    }

    /**
     * Launches the JavaFX application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }


}

