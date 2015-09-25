package Practice;

import java.util.Comparator;

/**
 * @author Sherafgan Kandov
 *         24.09.2015
 */
public class MyIntegerComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Integer num1 = (Integer) o1;
        Integer num2 = (Integer) o2;

        if (num1 > num2) {
            return 1;
        } else {
            return 0;
        }
    }
}
