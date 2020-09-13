package sample;
import javafx.scene.paint.Color;

public enum MyColor {
    //list out multiple colors with their rgb values
    LIGHTPINK(230, 145, 176), ORANGE(255,128,0),
    RED(255, 0, 0), PINK(255,0,255),
    BLUE(0,0,255), GREEN(0, 255,0),
    TAN(232,173,106), BURGUNDY(51,0,25),
    BROWN(102,51,0), AQUA(106,232,198),
    YELLOW(255,255,0), VIOLET(158,151,185),
    GREY(128,128,128), PINEGREEN(63,125,65),
    LIGHTBLUE(51,153,255), BRICKRED(137,50,79),
    LIGHTGREEN(51,255,51), LIGHTGREY(192,192,192),
    LIGHTPURPLE(166,64,203), GOLD(168,168,39),
    DARKPURPLE(84,25,105), NAVY(0,25,51),
    DARKYELLOW(153,153,0), SKYBLUE(122,203,255),
    LILLAC(203,18,249), BABYGREEN(51,255,53), WHITE(0,0,0),
    BLACK(0,0,0);

    private int red, green, blue;

    //constructor
    MyColor(int red, int green, int blue) {
       this.red = red;
       this.green = green;
       this.blue = blue; }

       //can set the color
    public void setColor (int red, int blue, int green) {
        this.red = red;
        this.green = green;
        this.blue = blue; }

    //to return a Color type from JavaFX
    public Color getColor () {
        Color color  = Color.rgb(this.red, this.green, this.blue);
        return color; }
}
