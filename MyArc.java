package sample;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;

public class MyArc extends MyShape {
    double width, height, arcExtent, startAngle;

    public MyArc(double x, double y, double width, double height, double arcExtent, double angle) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.arcExtent = arcExtent;
        this.startAngle = angle;
    }
    public void setArcExtent(double arcExtent) { this.arcExtent = arcExtent; }

    public void setStartAngle(double startAngle) { this.startAngle = startAngle; }

    public void setColor(MyColor color) {
        this.Color = color;
    }

    @Override
    public void draw(GraphicsContext gx) {
        gx.setFill(Color.getColor());
        gx.strokeArc(this.x - width / 2, this.y - height / 2, width, height, startAngle, arcExtent, ArcType.ROUND);
        gx.fillArc(this.x - width / 2, this.y - height / 2, width, height, startAngle, arcExtent, ArcType.ROUND);
    }




    @Override
    public MyRectangle getMyBoundingBox() {
        return null;
    }

    @Override
    public void moveTo(double x, double y) {

    }

    @Override
    public double distanceTo(double x, double y) {
        return 0;
    }

    @Override
    public void setPoint(double x, double y) {

    }

    @Override
    public double[] getPoint() {
        return new double[0];
    }
}
