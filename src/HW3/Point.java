package HW3;

import java.text.DecimalFormat;

/**
 * @author Sherafgan Kandov
 */
public class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String toString() {
        return "(" + new DecimalFormat("##.##").format(x) + " , " + new DecimalFormat("##.##").format(y) + ")";
    }
}
