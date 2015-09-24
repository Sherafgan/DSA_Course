package Practice;

import java.util.Comparator;

/**
 * @author Sherafgan Kandov
 *         24.09.2015
 */
public class MyDateComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Date d1 = (Date) o1;
        Date d2 = (Date) o2;

        if (d1.getYear() > d2.getYear()) {
            return +1;
        }
        if (d1.getYear() < d2.getYear()) {
            return -1;
        }
        if (d1.getMonth() > d1.getMonth()) {
            return +1;
        }
        if (d1.getMonth() < d2.getMonth()) {
            return -1;
        }
        if (d1.getDay() > d2.getDay()) {
            return +1;
        }

        if (d1.getDay() < d2.getDay()) {
            return -1;
        }
        return 0;
    }
}
