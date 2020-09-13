package sample;

import javafx.scene.canvas.GraphicsContext;

public class MyRectangle extends MyShape {
    private double width; //rectangle width
    private double height; //rectangle height
    private double area; //holds area
    private double perimeter; //holds perimeter

    public MyRectangle(double x, double y, double height, double width, MyColor color) {
        this.x = x;
        this.y = y;
        this.Color = color;
        this.height = height;
        this.width = width;
    }
    public double getArea() {
        this.area = width*height;
        return area;
    }

    public double getPerimeter() {
        this.perimeter = (2*width) + (2*height);
        return perimeter;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setArea() { this.area = width*height; }

    public void setPerimeter() {
        this.perimeter = (2*width) + (2*height);
    }

    @Override
    public String toString() {
        return "MyRectangle{" +
                "width=" + width +
                ", height=" + height +
                ", area=" + area +
                ", perimeter=" + perimeter +
                '}';
    }

    @Override
    public void draw(GraphicsContext gx) {
        gx.setFill(Color.getColor());
        double temp = width/2;
        double temp2 = height/2;
        gx.fillRect(x-temp,y-temp2,width,height);
    }

    @Override
    //rectangle bounding box is, well itself, so create itself!
    public MyRectangle getMyBoundingBox() {
        MyRectangle boundingbox = new MyRectangle(this.x,this.y,this.height,this.width,this.Color);
        return boundingbox;
    }
    @Override
    //moves the shape by adding x and y to this.x and this.y
    public void moveTo(double x, double y) {
        this.x = this.x+x;
        this.y = this.y+y;
    }
    @Override
    //distance from center of shape to a point
    public double distanceTo(double x, double y) {
        double distance = Math.sqrt(Math.pow((this.x-x),2)+Math.pow((this.y-y),2));
        return distance;
    }
    @Override
    //sets the center of the shape to a new place
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
