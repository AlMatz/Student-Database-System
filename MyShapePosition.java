package sample;

public interface MyShapePosition extends MyPoint {
    MyRectangle getMyBoundingBox();
    boolean doOverlap(MyShape Shape);
}
