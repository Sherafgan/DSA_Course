package HW3;

import javax.swing.*;
import java.awt.*;
import java.awt.Polygon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PolygonGUI extends JPanel {
    private int R;
    private Point a;
    private MyLinkedList<Point> pointsList;
    private MyLinkedList<Point> pointsListOfBs;

    private static final int MOVE_SPACE = 200;
    private static final int SAFE_RANGE = 100;

    public PolygonGUI(MyLinkedList<Point> pointsList, Point a, MyLinkedList<Point> pointsListOfBs, int R) {
        this.pointsList = pointsList;
        this.pointsListOfBs = pointsListOfBs;
        this.a = a;
        this.R = R;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Polygon p = new Polygon();
        for (int i = 0; i < pointsList.size(); i++)
            p.addPoint((int) pointsList.get(i).getX() + MOVE_SPACE, (int) pointsList.get(i).getY() + MOVE_SPACE);
        g.drawPolygon(p);
        Point b;
        for (int i = 0; i < pointsListOfBs.size(); i++) {
            b = pointsListOfBs.get(i);
            int x1 = (int) a.getX() + MOVE_SPACE;
            int y1 = (int) a.getY() + MOVE_SPACE;
            int x2 = (int) b.getX() + MOVE_SPACE;
            int y2 = (int) b.getY() + MOVE_SPACE;
            g.drawLine(x1, y1, x2, y2);
        }
//        int x1 = (int) a.getX() + MOVE_SPACE;
//        int y1 = (int) a.getY() + MOVE_SPACE;
//        int x2 = x1 + (R * SAFE_RANGE);
//        int y2 = y1 + (R * SAFE_RANGE / 2);
//        g.drawLine(x1, y1, x2, y2);
    }

    public void displayGUI() {
        JFrame frame = new JFrame();
        frame.setTitle("Polygon");
        frame.setSize(500, 500);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        Container contentPane = frame.getContentPane();
        contentPane.add(new PolygonGUI(pointsList, a, pointsListOfBs, R));
        frame.setVisible(true);
    }
}
