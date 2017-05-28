package com.github.lwhite1.tablesaw.mapping;

import com.github.lwhite1.tablesaw.api.DateTimeColumn;
import com.github.lwhite1.tablesaw.api.DoubleColumn;
import com.github.lwhite1.tablesaw.columns.packeddata.PackedLocalDateTime;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.assertEquals;

/**
 * Tests for DateTimeMapUtils
 */
public class DateTimeMapUtilsTest {

    private DateTimeColumn startCol = DateTimeColumn.create("start");
    private DateTimeColumn stopCol = DateTimeColumn.create("stop");
    private LocalDateTime start = LocalDateTime.now();


    @Test
    public void testDifferenceInMilliseconds() throws Exception {
        long pStart = PackedLocalDateTime.pack(start);
        LocalDateTime stop = start.plus(100_000L, ChronoUnit.MILLIS);
        long pStop = PackedLocalDateTime.pack(stop);

        startCol.add(start);
        stopCol.add(stop);

        assertEquals(100_000L, startCol.difference(pStart, pStop, ChronoUnit.MILLIS));
        DoubleColumn result = startCol.differenceInMilliseconds(stopCol);
        assertEquals(100_000L, result.firstElement(), 0.0001);
    }

    @Test
    public void testDifferenceInSeconds() throws Exception {
        LocalDateTime stop = start.plus(100_000L, ChronoUnit.SECONDS);

        startCol.add(start);
        stopCol.add(stop);

        DoubleColumn result = startCol.differenceInSeconds(stopCol);
        assertEquals(100_000L, result.firstElement(), 0.0001);
    }

    @Test
    public void testDifferenceInMinutes() throws Exception {
        LocalDateTime stop = start.plus(100_000L, ChronoUnit.MINUTES);

        startCol.add(start);
        stopCol.add(stop);

        DoubleColumn result = startCol.differenceInMinutes(stopCol);
        assertEquals(100_000L, result.firstElement(), 0.0001);
    }

    @Test
    public void testDifferenceInHours() throws Exception {
        LocalDateTime stop = start.plus(100_000L, ChronoUnit.HOURS);

        startCol.add(start);
        stopCol.add(stop);

        DoubleColumn result = startCol.differenceInHours(stopCol);
        assertEquals(100_000L, result.firstElement(), 0.0001);

    }

    @Test
    public void testDifferenceInDays() throws Exception {
        LocalDateTime stop = start.plus(100_000L, ChronoUnit.DAYS);

        startCol.add(start);
        stopCol.add(stop);

        DoubleColumn result = startCol.differenceInDays(stopCol);
        assertEquals(100_000L, result.firstElement(), 0.0001);
    }

    @Test
    public void testDifferenceInYears() throws Exception {

        LocalDateTime stop = start.plus(10_000L, ChronoUnit.YEARS);
        startCol.add(start);
        stopCol.add(stop);

        DoubleColumn result = startCol.differenceInYears(stopCol);
        assertEquals(10_000L, result.firstElement(), 0.0001);
    }

    @Test
    public void testHour() throws Exception {
        startCol.add(LocalDateTime.of(1984, 12, 12, 7, 30));
        DoubleColumn hour = startCol.hour();
        assertEquals(7, hour.firstElement(), 0.0001);
    }
}