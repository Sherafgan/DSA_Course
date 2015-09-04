package HW3;

import java.util.Scanner;

/**
 * @author Sherafgan Kandov
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter radius: ");
        int radius = scanner.nextInt();
        System.out.println("Enter n(amount of points): ");
        int N = scanner.nextInt();
        System.out.println("Enter point coordinates: ");
        double x = scanner.nextDouble();
        double y = scanner.nextDouble();

        Polygon polygon = new Polygon(radius, N);
        polygon.displayPoints();

        System.out.println(polygon.inside(new Point(x, y)));
        System.out.println();
        System.out.println(polygon.area());
    }
}