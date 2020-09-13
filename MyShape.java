package sample;

import javafx.scene.canvas.GraphicsContext;


public abstract class MyShape implements MyShapePosition {
    protected double x;
    protected double y;
    MyColor Color;

    //constructor for MyShape
    /*
    public MyShape(double x, double y, MyColor color) {
        this.x = x;
        this.y = y;
        Color = color;
    }

     */

    //returns color associated with each shape
    public MyColor getColor() {
        return Color;
    }

    //returns the x-cord associated with each shape
        //in case of shape its the center
        //in case of line its beginning point
    public double getX() {
        return x;
    }

    //returns the y-cord associated with each shape
        //in case of shape its the center
        //in case of line its beginning point
    public double getY() {
        return y;
    }

    //Takes the center (X,Y) and the color and returns them in string form.
    //overridden in each subclass
    public String toString() {
        return "MyShape{" +
                "x=" + x +
                ", y=" + y +
                "Color=" + Color +
                '}';
    }

    //sets the color associated with each shape
    public void setColor(MyColor color) {
        Color = color;
    }

    //sets the x-cord associated with each shape
        //in case of shape its the center
        //in case of line its beginning point
    public void setX(double x) {
        this.x = x;
    }

    //sets the y-cord associated with each shape
        //in case of shape its the center
        //in case of line its beginning point
    public void setY(double y) {
        this.y = y;
    }

    //overridden in each shape class
    public abstract void draw(GraphicsContext gx);

    @Override
    public boolean doOverlap(MyShape Shape) {
        double x_top_right = this.getMyBoundingBox().getX() + this.getMyBoundingBox().getWidth()/2;
        double y_top_right = this.getMyBoundingBox().getY() - this.getMyBoundingBox().getHeight()/2;

        double y_bot_right = this.getMyBoundingBox().getY() + this.getMyBoundingBox().getHeight()/2;
        double x_bot_right = x_top_right;

        double x_top_left = this.getMyBoundingBox().getX() - this.getMyBoundingBox().getWidth()/2;
        double y_top_left = this.getMyBoundingBox().getY() - this.getMyBoundingBox().getHeight()/2;

        double x_bot_left = x_top_left;
        double y_bot_left = y_bot_right;

        double shape_x_top_right = Shape.getMyBoundingBox().getX() + Shape.getMyBoundingBox().getWidth()/2;
        double shape_y_top_right = Shape.getMyBoundingBox().getY() - Shape.getMyBoundingBox().getHeight()/2;

        double shape_x_top_left = Shape.getMyBoundingBox().getX() - Shape.getMyBoundingBox().getWidth()/2;
        double shape_y_top_left = Shape.getMyBoundingBox().getY() - Shape.getMyBoundingBox().getHeight()/2;

        double shape_x_bot_right = Shape.getMyBoundingBox().getX() + Shape.getMyBoundingBox().getWidth()/2;
        double shape_y_bot_right = Shape.getMyBoundingBox().getY() + Shape.getMyBoundingBox().getHeight()/2;
        double shape_x_bot_left = Shape.getMyBoundingBox().getX() - Shape.getMyBoundingBox().getWidth()/2;
        double shape_y_bot_left = Shape.getMyBoundingBox().getY() + Shape.getMyBoundingBox().getHeight()/2;

        if (shape_x_top_left <= x_top_right && shape_x_top_left >= x_top_left && shape_y_top_left <= y_bot_right && shape_y_top_left >= y_top_left)
            return true;
        else if (shape_x_top_right <= x_top_right && shape_x_top_right >= x_top_left && shape_y_top_right <= y_bot_right && shape_y_top_right >= y_top_left)
            return true;
        else if (shape_x_bot_left <= x_top_right && shape_x_bot_left >= x_top_left && shape_y_bot_left <= y_bot_right && shape_y_bot_left >= y_top_left)
            return true;
        else if (shape_x_bot_right <= x_top_right && shape_x_bot_right >= x_top_left && shape_y_bot_right <= y_bot_right && shape_y_bot_right >= y_top_left)
            return true;
        else if(x_top_left <= shape_x_top_right && x_top_left >= shape_x_top_left && y_top_left <= shape_y_bot_right && y_top_left >= shape_y_top_right)
            return true;
        else if(x_top_right <= shape_x_top_right && x_top_right >= shape_x_top_left && y_top_right <= shape_y_bot_right && y_top_right >= shape_y_top_right)
            return true;
        else if(x_bot_left <= shape_x_top_right && x_bot_left >= shape_x_top_left && y_bot_left <= shape_y_bot_right && y_bot_left >= shape_y_top_right)
            return true;
        else if(x_bot_right <= shape_x_top_right && x_bot_right >= shape_x_top_left && y_bot_right <= shape_y_bot_right && y_bot_right >= shape_y_top_right)
            return true;
        else if(Shape.getMyBoundingBox().getX() <= x_top_right && Shape.getMyBoundingBox().getX() >= x_top_left && Shape.getMyBoundingBox().getY() <= y_bot_right && Shape.getMyBoundingBox().getY() >= y_top_right)
            return true;
        else if(this.getMyBoundingBox().getX() <= shape_x_top_right && this.getMyBoundingBox().getX() >= shape_x_top_left && this.getMyBoundingBox().getY() <= shape_y_bot_right && this.getMyBoundingBox().getY() >= shape_y_top_right)
           return true;
        else
            return false;
    }



}
