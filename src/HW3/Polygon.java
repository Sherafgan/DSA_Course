package HW3;

/**
 * @author Sherafgan Kandov
 */
public class Polygon {
    private MyLinkedList<Point> pointsList;
    private MyLinkedList<Edge> edgesList;
    private int radius;

    public Polygon(int radius, int N) {
        this.radius = radius;
        int t = 0;
        double angle;
        pointsList = new MyLinkedList<>();
        Point point;
        edgesList = new MyLinkedList<>();
        Edge edge;
        for (int i = 0; i < N; i++) {
            angle = (2.0 * Math.PI * t) / N;
            double x = (Math.cos(angle)) * radius;
            double y = (Math.sin(angle)) * radius;
            point = new Point(x, y);
            pointsList.add(point);
            t++;
        }

        Point a;
        Point b;
        for (int i = 0; i < pointsList.size(); i++) {
            a = pointsList.get(i);
            if ((i + 1) == pointsList.size()) {
                b = pointsList.get(0);
            } else {
                b = pointsList.get(i + 1);
            }

            edge = new Edge(a, b);
            edgesList.add(edge);
        }
    }

    /**
     * Checks if point is inside polygon
     */
    public boolean inside(Point a) {
        int counterOfIntersections = 0;
        Point b = new Point(a.getX() * (2 * radius), a.getY()); //AB's horizontal right direction
        Point c;
        Point d;

        /*
        //alternative for
        for (Iterator<Edge> edgesIterator = edgesList.iterator(); edgesIterator.hasNext(); ) {
            c = edgesIterator.next().getA();
            d = edgesIterator.next().getB();
            if (intersects(a, b, c, d)) {
                counterOfIntersections++;
            }
        }
        */

        for (Edge edge : edgesList) {
            c = edge.getA();
            d = edge.getB();
            if (intersects(a, b, c, d)) {
                counterOfIntersections++;
            }
        }

        if (counterOfIntersections % 2 == 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Checks if 'ab' intersects 'cd'
     */
    private boolean intersects(Point a, Point b, Point c, Point d) {
        double[][] A = new double[2][2];
        A[0][0] = b.getX() - a.getX();
        A[1][0] = b.getY() - a.getY();
        A[0][1] = c.getX() - d.getX();
        A[1][1] = c.getY() - d.getY();

        double det0 = A[0][0] * A[1][1] - A[1][0] * A[0][1];

        double detU = (c.getX() - a.getX()) * A[1][1] - (c.getY() - a.getY()) * A[0][1];
        double detV = A[0][0] * (c.getY() - a.getY()) - A[1][0] * (c.getX() - a.getX());

        double u = detU / det0;
        double v = detV / det0;
        return u > 0 && u < 1 && v > 0 && v < 1;
    }

    /**
     * Displays points coordinates
     */
    public void displayPoints() {
        for (int i = 0; i < pointsList.size(); i++) {
            System.out.print(pointsList.get(i).toString() + " ");
            System.out.println();
        }
    }
}
