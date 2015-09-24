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
            if (s1.charAt(i) < s2.charAt(i)) {
                return 1;
            }
        }
        return 0;
    }
}
