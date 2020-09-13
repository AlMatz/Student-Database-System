package sample;
import javafx.scene.canvas.GraphicsContext;

public class MyCircle extends MyOval {
    private double radius;
    private double area;
    private double circum;
    private double height; //diameter
    private double width;

    public MyCircle(double xc, double yc, double radius, MyColor color) {
        super(xc,yc,radius,radius,color);
        this.area = Math.PI * Math.pow(radius,2);
        this.radius = radius;
        this.circum = Math.PI * 2 * radius;
        this.height = 2*radius;
        this.width = 2*radius;
        this.Color = color;
    }

    public double getPerimeter() {
        return circum;
    }
    public double getRadius() {
        return radius;
    }
    public double getArea() {
        return area;
    }

    @Override
    public String toString() {
        return "MyCircle{" +
                "x=" + x +
                ", y=" + y +
                ", color=" + Color +
                ", radius=" + radius +
                ", area=" + area +
                ", perimeter=" + circum +
                '}'; }

    @Override
    public void draw(GraphicsContext gx) {
        gx.setFill(Color.getColor());
        //x,y are the center we want.
        double xnew = x-radius;
        double ynew = y-radius;
        gx.fillOval(xnew,ynew,width,height);
    }
}
