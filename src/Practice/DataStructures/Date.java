package Practice.DataStructures;

/**
 * @author Sherafgan Kandov
 *         24.09.2015
 */
public class Date implements Comparable<Date> {
    private int day;
    private int month;
    private int year;

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public String toString() {
        return day + "/" + month + "/" + year;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    @Override
    public int compareTo(Date d2) {
        if (this.getYear() > d2.getYear()) {
            return +1;
        }
        if (this.getYear() < d2.getYear()) {
            return -1;
        }
        if (this.getMonth() > this.getMonth()) {
            return +1;
        }
        if (this.getMonth() < d2.getMonth()) {
            return -1;
        }
        if (this.getDay() > d2.getDay()) {
            return +1;
        }

        if (this.getDay() < d2.getDay()) {
            return -1;
        }
        return 0;
    }
}
