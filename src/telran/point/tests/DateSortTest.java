package telran.point.tests;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class DateSortTest {

    private static LocalDate parseDate(String date) {
        return date.contains("-")
                ? LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                : LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    @Test
    void test() {
        String[] dates = { "07-05-1990", "28-01-2010", "11-08-1990", "15-01-2010", "16/06/1970" };
        String[] expected = { "16/06/1970", "07-05-1990", "11-08-1990", "15-01-2010", "28-01-2010" };
        Comparator<String> comp = (d1, d2) -> parseDate(d1).compareTo(parseDate(d2));       // TODO: implement comparator
        Arrays.sort(dates, comp);
        assertArrayEquals(expected, dates);
    }
}
