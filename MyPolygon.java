package sample;

import javafx.scene.canvas.GraphicsContext;

public class MyPolygon extends MyShape {
    private int N; //number of sides
    private double radius; // radius which its inscribed
    private double area;
    private double perimeter;
    private double angle; //all interior angles are equal
    private double side_length;
    private double[] xcords;
    private double[] ycords;
    private double apothem;



    public MyPolygon(double xc, double yc, int n, double r, MyColor color) {
        //super(xc,yc,color);
        assert(n>2); //N must be greater than 2 or else not a polygon

        this.Color = color;
        this.x = xc; //center of polygon
        this.y = yc; //center of polygon
        this.N = n; //N must be greater than 2
        this.radius = r;
        this.angle = ((N-2)*180)/N; // formula for finding measure of interior angle of an N sided polygon.

        xcords = new double[N];
        ycords = new double [N];
        setCords(); // want to set our x,y coordinates

        // formula for length is sqrt((diff x)^2 + (diff y)^2)
        //since its a regular polygon, all sides have the same length!
        this.side_length = find_distance(xcords[0],ycords[0],xcords[1],ycords[1]);
        this.perimeter = side_length*N; // perimeter is the sidelength times the number of sides.

        //to find the area we must do some math. First find the apothem
        //apothem is the distance from the center to any side that creates a perpendicular line

        //find the midpoint of a side
        double midpoint_x = (xcords[0]+xcords[1])/2;
        double midpoint_y = (ycords[0]+ycords[1])/2;
        //now find the distance to the center x,y also called the apothem!
        this.apothem =  find_distance(x,y,midpoint_x,midpoint_y);
        this.area = (N * apothem * side_length ) / 2;
    }

    public double find_distance (double x1, double y1, double x2, double y2) {
        double distance;
        distance = Math.sqrt(Math.pow((x1-x2),2)+Math.pow((y1-y2),2));
        return distance;
    }

    public double getApothem() {
        return apothem;
    }

    public int getN() {
        return N;
    }

    public double getArea() { return area; }

    public double getPerimeter() {
        return perimeter;
    }

    public double getAngle() {
        return angle;
    }

    public double getSide_length() {
        return side_length;
    }

    public double[] getXcords() {
        this.setCords();
        return xcords;
    }

    public double[] getYcords() {
        this.setCords();
        return ycords;
    }

    public void setCords() {
        for (int i = 0; i < N; i++)
        {
            xcords[i] = x + (radius*Math.sin((i*(2*Math.PI)/N))); // formula xcord = x+rsin(Theta)
            ycords[i] = y + (radius*Math.cos((i*(2*Math.PI)/N))); // formula ycord = y+rcos(Theta) both theta is some multiple of 2Pi/N
        }
    }

    @Override
    public String toString() {
        return "MyPolygon{" +
                "area=" + area +
                ", perimeter=" + perimeter +
                ", angle=" + angle +
                ", side_length=" + side_length +
                '}';
    }

    @Override
    public void draw(GraphicsContext gx) {
        setCords();
        gx.setFill(Color.getColor());
        gx.fillPolygon(xcords,ycords,N);
    }

    @Override
    //is this correct for bounding box of a polygon?
    public MyRectangle getMyBoundingBox() {
        MyRectangle boundingbox = new MyRectangle(this.x, this.y, 2*this.radius, 2*this.getApothem(), this.Color);
        return boundingbox;
    }
    @Override
    public void moveTo(double x, double y) {
        this.x = this.x+x;
        this.y = this.y+y;
        this.setCords();
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
        this.setCords();
    }
    @Override
    public double[] getPoint() {
        double[] arr = new double[2];
        arr[0] = this.x;
        arr[1] = this.y;
        return arr;
    }
}
