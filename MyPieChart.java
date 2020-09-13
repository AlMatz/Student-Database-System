package sample;
import javafx.scene.canvas.GraphicsContext;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

public class MyPieChart {
    private int n; //number of freq to display
    private int all_possible_freq; //total number of frequencies our case is 6 since 6 grades
    private double x, y; //center of pie chart
    private double width, height; //width and height of pie chart
    Map<String, Double> probabilities;
    Map<String, Double> sorted_prob;
    ArrayList<Double> values;
    ArrayList<String> names;

    public MyPieChart(double x, double y, double width, double height) {
        this.n=0;
        this.all_possible_freq = 6;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void setN(int new_n) {
        if (new_n > all_possible_freq) {
            System.out.println("n must be less than the total possible frequencies which is currently : " + all_possible_freq);
        } else { this.n = new_n;  }    }
    public double getN() {
        return n;
    }
    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }
    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setProbabilites(Map<String, Double> histogram) {
        double temp_total = 0; //will hold # of chars occurences
        double tot_prob = 0;
        for (String key : histogram.keySet()) { //iterates through every possible key
            temp_total += histogram.get(key); //adds each keys value to the total
        }
        //System.out.println("total amount of occurences are : " + temp_total); //to test
        probabilities = histogram;
        for(String key : histogram.keySet()) {
            double freq = histogram.get(key);
            double prob = freq/temp_total;
            probabilities.replace(key,prob);
            tot_prob += prob;
        }
        //System.out.println(frequencies);
        //System.out.println("Prob is equal to : " + tot_prob);
        sort_prob_map(probabilities);
    }

    public void sort_prob_map(Map<String, Double> W) {
        sorted_prob = probabilities.entrySet().stream().sorted(comparingByValue())
                .collect(toMap(Map.Entry::getKey,
                        Map.Entry::getValue, (e1,e2) -> e2, LinkedHashMap::new));
       // System.out.println(sorted_freq);
        ArrayList<Double> temp_hold = new ArrayList<>(all_possible_freq);
        ArrayList<String> temp_hold2 = new ArrayList<>(all_possible_freq);
        for (String key : sorted_prob.keySet()) {
          //  System.out.println(sorted_freq.get(key));
            temp_hold.add(sorted_prob.get(key));
            temp_hold2.add(key);
        }
        names = temp_hold2;
        values = temp_hold;
       // System.out.println(names);
       // System.out.println(values);
    }

    public void draw(GraphicsContext gx) {
        double startAngle = 0;
        double arcExtent = 0;
        double current_prob_sum = 0;
        int colors_count = 0;
        MyColor color_one;
        MyColor[] color = MyColor.values();
        MyArc arc = new MyArc(x,y,width,height,startAngle,arcExtent);
        DecimalFormat df = new DecimalFormat("#.##%");
        for (int i = values.size()-1; i >= (values.size()-n); i-- ) {
            current_prob_sum += values.get(i);
            arcExtent = values.get(i)*360;
                color_one=color[colors_count];
                colors_count++;
          // color_one.setColor((int)(255*Math.random()), (int)(255*Math.random()), (int)(255*Math.random()));
          //  System.out.println(color);
            arc.setColor(color_one);
            arc.setArcExtent(arcExtent);
            arc.setStartAngle(startAngle);
            arc.draw(gx);
            /*gx.setFill(color.getColor());
            gx.strokeArc(this.x - width / 2, this.y - height / 2, width, height, startAngle, arcExtent, ArcType.ROUND);
            gx.fillArc(this.x - width / 2, this.y - height / 2, width, height, startAngle, arcExtent, ArcType.ROUND);*/
            double radius = Math.sqrt((width*width)+(height*height));
            double x_pos = x + radius*Math.cos(Math.toRadians(startAngle+arcExtent/2))/2-25;
            double y_pos = y - radius*Math.sin(Math.toRadians(startAngle+arcExtent/2))/2+10;
            gx.setFill(MyColor.BLACK.getColor());
            gx.fillText(names.get(i) +", "+ df.format(values.get(i)),x_pos,y_pos);
            startAngle += arcExtent;
        }
        MyArc arc2 = new MyArc(x,y,width,height,startAngle,arcExtent);
       // System.out.println(current_prob_sum);
        if ( n != all_possible_freq) {
            MyColor color_two = MyColor.BLACK;
            double rest_of_probs = 1 - current_prob_sum;
            arcExtent = 0;
            for (int i = (int) ((all_possible_freq-n)-1); i>-1; i--) {
                arcExtent += values.get(i)*360;
            }
            //fills the rest
            arc2.setArcExtent(arcExtent);
            arc2.setStartAngle(startAngle);
            arc2.setColor(color_two);
            arc2.draw(gx);
            /*gx.setFill(color.getColor());
            gx.strokeArc(this.x - width / 2, this.y - height / 2, width, height, startAngle, arcExtent, ArcType.ROUND);
            gx.fillArc(this.x - width / 2, this.y - height / 2, width, height, startAngle, arcExtent, ArcType.ROUND);*/
            double radius = Math.sqrt((width*width)+(height*height));
            double tx = x + radius*Math.cos(Math.toRadians(startAngle+arcExtent/2))/2-25;
            double ty = y - radius*Math.sin(Math.toRadians(startAngle+arcExtent/2))/2+10;
            gx.setFill(MyColor.BLACK.getColor());
            gx.fillText("Rest, "+ df.format(rest_of_probs),tx,ty);
        }    }}