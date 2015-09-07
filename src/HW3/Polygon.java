package HW3;

/**
 * @author Sherafgan Kandov
 */
public class Polygon {
    private MyLinkedList<Point> pointsList;
    private MyLinkedList<Edge> edgesList;
    private int radius;
    private int N; // number of points

    public Polygon(int radius, int N) {
        this.radius = radius;
        this.N = N;
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
        int amountOfRays = 10;
        int amountOfRaysIntersectedPolygon = 0;
        MyLinkedList<Point> pointsListOfBs = new MyLinkedList<>();
        Point b;
        boolean opChanger = true;
        for (int i = 50; i <= amountOfRays * 50; i += 50) {
            double x;
            double y;
            if (opChanger) {
                x = a.getX() + (5.0 * radius);
                y = -(1.0 * 2.5 * i);
                opChanger = false;
            } else {
                x = a.getX() + (5.0 * radius);
                y = 1.0 * 2.5 * i;
                opChanger = true;
            }
            b = new Point(x, y);
            pointsListOfBs.add(b);
            Point c;
            Point d;

            /*
            //alternative For-Each Loop
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
            if (counterOfIntersections % 2 != 0) {
                amountOfRaysIntersectedPolygon++;
            }
            counterOfIntersections = 0;
        }
        displayGUI(a, pointsListOfBs);

        if (amountOfRaysIntersectedPolygon > (amountOfRays / 2)) {
            return true;
        } else {
            return false;
        }
    }

    private void displayGUI(Point a, MyLinkedList<Point> pointsListOfBs) {
        PolygonGUI polygonGUI = new PolygonGUI(pointsList, a, pointsListOfBs, radius);
        polygonGUI.displayGUI();
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
     * Returns the are of polygon
     */
    public double area() {
        double sum = 0.0;
        Point a;
        Point b;
        for (int i = 0; i < N; i++) {
            a = pointsList.get(i);
            if (i + 1 == N) {
                b = pointsList.get(0);
            } else {
                b = pointsList.get(i + 1);
            }
            sum = sum + (a.getX() * b.getY()) - (a.getY() * b.getX());
        }
        return 0.5 * sum;
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
