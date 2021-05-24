import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SILab2Test {

    private static final SILab2 siLab2 = new SILab2();

    private List<Time> createList(Time... elems) {
        return new ArrayList<>(Arrays.asList(elems));
    }

    private List<Integer> createIntList(Integer... elems) {
        return new ArrayList<>(Arrays.asList(elems));
    }

    @Test
    void everyBranchTest() {
        Time time = new Time(-1, 0, 0);
        List<Time> timesList = createList(time);
        assertThrows(RuntimeException.class, () -> siLab2.function(timesList), "The hours are smaller than the minimum");

        time = new Time(25, 0, 0);
        List<Time> timesList2 = createList(time);
        assertThrows(RuntimeException.class, () -> siLab2.function(timesList2), "The hours are grater than the maximum");

        time = new Time(23, -1, 0);
        List<Time> timeList3 = createList(time);
        assertThrows(RuntimeException.class, () -> siLab2.function(timeList3), "The minutes are not valid!");

        time = new Time(23, 10, -1);
        List<Time> timeList4 = createList(time);
        assertThrows(RuntimeException.class, () -> siLab2.function(timeList4), "The seconds are not valid");

        time = new Time(24, 0, 0);
        List<Time> timeList5 = createList(time);
        assertEquals(createIntList(86400), siLab2.function(timeList5));

        time = new Time(23, 10, 10);
        Time time2 = new Time(24, 10, 10);
        List<Time> timeList6 = createList(time, time2);
        assertThrows(RuntimeException.class, () -> siLab2.function(timeList6), "The time is greater than the maximum");
    }

    @Test
    public void multipleConditionsTest() {
        // if (hr < 0 || hr > 24)
        Time time = new Time(23, 10, 10);
        List<Time> timesList = createList(time);
        assertEquals(createIntList(83410), siLab2.function(timesList));

        // if (min < 0 || min > 59)
        time = new Time(23, 60, 10);
        List<Time> timeList2 = createList(time);
        assertThrows(RuntimeException.class, () -> siLab2.function(timeList2), "The minutes are not valid!");

        // if (sec >= 0 && sec <= 59)
        time = new Time(23, 10, 60);
        List<Time> timeList3 = createList(time);
        assertThrows(RuntimeException.class, () -> siLab2.function(timeList3), "The seconds are not valid");

        // if (hr == 24 && min == 0 && sec == 0)
        time = new Time(24, 0, 1);
        List<Time> timeList4 = createList(time);
        assertThrows(RuntimeException.class, () -> siLab2.function(timeList4), "The time is greater than the maximum");

        time = new Time(24, 10, 10);
        List<Time> timeList5 = createList(time);
        assertThrows(RuntimeException.class, () -> siLab2.function(timeList5), "The time is greater than the maximum");
    }
}