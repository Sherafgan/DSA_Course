package HW3;

/**
 * @author Sherafgan Kandov
 */
public class Edge {
    private Point a;
    private Point b;

    public Edge(Point a, Point b) {
        this.a = a;
        this.b = b;
    }

    public Point getA() {
        return a;
    }

    public Point getB() {
        return b;
    }
}
