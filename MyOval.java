package sample;

import javafx.scene.canvas.GraphicsContext;

public class MyOval extends MyShape{
    private MyRectangle rectangle; //bounding rectangle
    private double perimeter; //circumference of oval
    private double area; //area of oval

    //constructor
    public MyOval(double x, double y, double height, double width, MyColor color) {
        this.x = x; //sets x center
        this.y = y; //sets y center
        this.Color = color; //sets color
        rectangle = new MyRectangle(x,y,height,width,color); //creates a rectangle
    }

    //gets perimeter and sets perimeter with math
    public double getPerimeter() {
        double major_axis = Math.pow(rectangle.getHeight(),2);
        double minor_axis = Math.pow(rectangle.getWidth(),2);
        double sum = major_axis+minor_axis;
        this.perimeter= 2*Math.PI*Math.sqrt(sum/2); //formula for circumference of an oval
        return perimeter;
    }

    //gets area and sets it
    public double getArea() {
        double a = rectangle.getHeight()/2;
        double b = rectangle.getWidth()/2;
        this.area = a*b*Math.PI;
        return area;
    }

    //width and height getters
    public double getWidth() {
        return this.rectangle.getWidth();
    }

    public double getHeight() {
        return this.rectangle.getHeight();
    }

    @Override
    public String toString() {
        return "MyOval{" +
                "height=" + rectangle.getHeight() +
                ", width=" + rectangle.getWidth() +
                ", perimeter=" + perimeter +
                ", area=" + area +
                '}';
    }

    @Override
    public void draw(GraphicsContext gx) {
        gx.setFill(Color.getColor());
        double temp = rectangle.getHeight()/2;
        double temp2 = rectangle.getWidth()/2;
        gx.fillOval(rectangle.getX()-temp2, rectangle.getY()-temp, rectangle.getWidth(), rectangle.getHeight());
    }

    @Override
    public MyRectangle getMyBoundingBox() {
        return this.rectangle;
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
