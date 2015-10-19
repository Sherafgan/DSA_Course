package Practice.Comparators;

import java.util.Comparator;

/**
 * @author Sherafgan Kandov
 *         24.09.2015
 */
public class MyFloatComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Float num1 = (Float) o1;
        Float num2 = (Float) o2;

        if (num1 > num2) {
            return 1;
        } else {
            return 0;
        }
    }
}