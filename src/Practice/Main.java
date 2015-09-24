package Practice;

public class Main {
    public static double squareRoot2(int deep) {
        if (deep == 22) return 1;
// deep == 10 - diff in 10th sign after point
// deep == 20 - diff in 16th sign after point
// deep == 22 - equal in double
        double value = 1 / (2 + squareRoot2(deep + 1));
        if (deep == 0) value += 1;
        return value;
    }

    public static void main(String[] args) {
        double a = squareRoot2(6);
        System.out.println(a);
        System.out.println(a * a);
    }
}
