package ca.bcit.cst.rongyi;

import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.shape.Polygon;

import java.util.ArrayList;
import java.util.List;

public class Model extends Group {

    private Point3D position;
    private List<Polygon3D> polygon3DList = new ArrayList<>();

    public Model(Point3D position) {
        this.position = position;
    }

    public Model(List<Polygon3D> polygon3DList, Point3D position) {
        this.polygon3DList = polygon3DList;
        this.position = position;

    }

    public void drawPolygon() {
        this.getChildren().clear();
        for (Polygon3D p3d : polygon3DList) {
            Polygon p = p3d.toPolygon();
            p.setTranslateX(position.getX());
            p.setTranslateY(position.getY());
            p.setTranslateZ(position.getZ());
            this.getChildren().add(p);
        }
    }

    public void addPolygon3D(Polygon3D polygon3D) {
        polygon3DList.add(polygon3D);
    }

    public void rotate(double x, double y, double z) {
        for (Polygon3D p3d : polygon3DList) {
            p3d.rotate(x, y, z);
        }
        drawPolygon();
    }
}
