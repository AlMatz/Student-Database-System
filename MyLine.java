package sample;

import javafx.scene.canvas.GraphicsContext;


public class MyLine extends MyShape {

    private double x2; //endpoint x
    private double y2; //endpoint y
    private double lwidth; //thickness of the line
    private double length; //length of the line
    private double xAngle; //angle with x axis

    //constructor
    public MyLine(double x, double y, double x2, double y2, MyColor color, double lwidth) {
        this.x = x;
        this.y = y;
        this.lwidth = lwidth;
        this.Color = color;
        this.x2 = x2;
        this.y2 = y2;
        this.length = Math.sqrt(Math.pow((x2-x),2)+Math.pow((y2-y),2)); // formula for length is sqrt((diff x)^2 + (diff y)^2)
        this.xAngle = Math.atan((y2-y)/(x2-x)); // formula for angle with x axis is atan (diff y / diff x) = angle
    }

    //returns x-ednpoint
    public double getX2() {
        return x2;
    }

    //returns y endpoint
    public double getY2() {
        return y2;
    }

    //returns length of line
    public double getLength() {
        this.length = Math.sqrt(Math.pow((x2-x),2)+Math.pow((y2-y),2));
        return length; }

    //returns angle with x axis of the line
    public double getxAngle() {
        this.xAngle = Math.atan((y2-y)/(x2-x));
        return xAngle; }

    //converts to a string.
    @Override
    public String toString() {
        this.xAngle = Math.atan((y2-y)/(x2-x));
        this.length = Math.sqrt(Math.pow((x2-x),2)+Math.pow((y2-y),2));
        return "MyLine{" +
                "x1=" + x +
                ", y1 =" + y +
                ", x2=" + x2 +
                ", y2=" + y2 +
                ", length=" + length +
                ", xAngle=" + xAngle +
                '}';
    }

    //draws a line with specified color, thickness and endpoints.
    @Override
    public void draw(GraphicsContext gx) {
        gx.setStroke(Color.getColor());
        gx.setLineWidth(lwidth);
        gx.strokeLine(x,y,x2,y2);
    }

    @Override
    public MyRectangle getMyBoundingBox() {
        //it is the box that connects the two end points, i.e. the line goes through the corners.
        //find midpoint of line, create rectangle with that as midpoint.
        double xmid = (this.x2 + this.x)/2;
        double ymid = (this.y2 + this.y)/2;
        double w = this.x2 - this.x;
        double h = this.y2 - this.y;
        //to find the height and width we have to do some math with the points, fix FRIDAY
        MyRectangle boundingbox = new MyRectangle(xmid,ymid,h,w,Color);
        return boundingbox;
    }

    @Override
    public void moveTo(double x, double y) {
        this.x = this.x+x;
        this.y = this.y+y;
    }

    @Override
    public double distanceTo(double x, double y) {
        double distance = Math.sqrt(Math.pow((this.x-x),2)+Math.pow((this.y-y),2));
        return distance;
    }

    @Override
    public void setPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public double[] getPoint() {
        double[] arr = new double[2];
        arr[0] = this.x;
        arr[1] = this.y;
        return arr;
    }
}
