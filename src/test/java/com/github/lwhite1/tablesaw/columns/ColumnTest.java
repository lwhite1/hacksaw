package com.github.lwhite1.tablesaw.columns;

import com.github.lwhite1.tablesaw.api.CategoryColumn;
import com.github.lwhite1.tablesaw.api.ColumnType;
import com.github.lwhite1.tablesaw.api.DateColumn;
import com.github.lwhite1.tablesaw.api.DoubleColumn;
import com.github.lwhite1.tablesaw.api.Table;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

/**
 * Tests for Column functionality that is common across column types
 */
public class ColumnTest {

    private Table table;

    @Before
    public void setUp() throws Exception {
        table = Table.createFromCsv("data/BushApproval.csv");
    }

    @Test
    public void testFirst() throws Exception {
        // test with dates
        DateColumn first = (DateColumn) table.dateColumn("date").first(3);
        assertEquals(LocalDate.parse("2004-02-04"), first.get(0));
        assertEquals(LocalDate.parse("2004-01-21"), first.get(1));
        assertEquals(LocalDate.parse("2004-01-07"), first.get(2));

        // test with ints
        DoubleColumn first2 = (DoubleColumn) table.numericColumn("approval").first(3);
        assertEquals(53, first2.get(0), 0.001);
        assertEquals(53, first2.get(1), 0.001);
        assertEquals(58, first2.get(2), 0.001);

        // test with categories
        CategoryColumn first3 = (CategoryColumn) table.categoryColumn("who").first(3);
        assertEquals("fox", first3.get(0));
        assertEquals("fox", first3.get(1));
        assertEquals("fox", first3.get(2));
    }

    @Test
    public void testLast() throws Exception {

        // test with dates
        DateColumn last = (DateColumn) table.dateColumn("date").last(3);
        assertEquals(LocalDate.parse("2001-03-27"), last.get(0));
        assertEquals(LocalDate.parse("2001-02-27"), last.get(1));
        assertEquals(LocalDate.parse("2001-02-09"), last.get(2));

        // test with ints
        DoubleColumn last2 = (DoubleColumn) table.numericColumn("approval").last(3);
        assertEquals(52, last2.get(0), 0.001);
        assertEquals(53, last2.get(1), 0.001);
        assertEquals(57, last2.get(2), 0.001);

        // test with categories
        CategoryColumn last3 = (CategoryColumn) table.categoryColumn("who").last(3);
        assertEquals("zogby", last3.get(0));
        assertEquals("zogby", last3.get(1));
        assertEquals("zogby", last3.get(2));
    }

    @Test
    public void testName() throws Exception {
        Column c = table.numericColumn("approval");
        assertEquals("approval", c.name());
    }

    @Test
    public void testComment() throws Exception {
        Column c = table.numericColumn("approval");
        c.setComment("Dumb comment");
        assertEquals("Dumb comment", c.comment());
    }

    @Test
    public void testType() throws Exception {
        Column c = table.numericColumn("approval");
        assertEquals(ColumnType.DOUBLE, c.type());
    }
}
