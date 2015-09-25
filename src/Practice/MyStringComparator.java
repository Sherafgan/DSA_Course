package Practice;

import java.util.Comparator;

/**
 * @author Sherafgan Kandov
 *         24.09.2015
 */
public class MyStringComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
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
    }
}
