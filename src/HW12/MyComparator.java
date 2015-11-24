package HW12;

import java.util.Comparator;

/**
 * @author Sherafgan Kandov
 *         18.11.15
 */
public class MyComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        if (o1 instanceof String && o2 instanceof String) {
            String s1 = (String) o1;
            String s2 = (String) o2;

            int n;
            if (s1.length() > s2.length()) {
                n = s2.length();
            } else {
                n = s1.length();
            }

            for (int i = 0; i < n; i++) {
                int c1;
                if (s1.charAt(i) > 90) {
                    c1 = s1.charAt(i) - 32;
                } else {
                    c1 = s1.charAt(i);
                }

                int c2;
                if (s2.charAt(i) > 90) {
                    c2 = s2.charAt(i) - 32;
                } else {
                    c2 = s2.charAt(i);
                }

                if (c1 > c2) {
                    return 1;
                } else if (c1 < c2) {
                    return -1;
                }
            }

            return 0;
        } else {
            Comparable comp1 = (Comparable) o1;
            Comparable comp2 = (Comparable) o2;

            return comp1.compareTo(comp2);
        }
    }
}
