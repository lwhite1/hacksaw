package com.github.lwhite1.tablesaw.filters;

import com.github.lwhite1.tablesaw.api.DateColumn;
import com.github.lwhite1.tablesaw.api.Table;
import com.github.lwhite1.tablesaw.columns.ColumnReference;
import com.github.lwhite1.tablesaw.columns.packeddata.PackedLocalDate;
import com.github.lwhite1.tablesaw.columns.packeddata.PackedLocalDateTime;
import com.github.lwhite1.tablesaw.filtering.DateBiFilter;
import com.github.lwhite1.tablesaw.filtering.DateFilter;
import com.github.lwhite1.tablesaw.filtering.predicates.LocalDatePredicate;
import com.github.lwhite1.tablesaw.filtering.predicates.LongLongPredicate;
import com.github.lwhite1.tablesaw.util.Selection;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 *
 */
public class LocalDateFilterTest {

    private DateColumn localDateColumn = DateColumn.create("testing");
    private Table table = Table.create("test");

    @Before
    public void setUp() throws Exception {
        localDateColumn.append(LocalDate.of(2016, 2, 28));
        localDateColumn.append(LocalDate.of(2016, 2, 29));
        localDateColumn.append(LocalDate.of(2016, 3, 1));
        table.addColumn(localDateColumn);
    }

    @Test
    public void testIsSunday() {
        ColumnReference reference = new ColumnReference("testing");
        DateFilter isSunday = new DateFilter(reference, PackedLocalDate::isSunday, PackedLocalDateTime::isSunday);
        Selection selection = isSunday.apply(table);
        assertTrue(selection.contains(0));
        assertFalse(selection.contains(1));
        assertFalse(selection.contains(2));
    }

    @Test
    public void testIsMonday() {
        ColumnReference reference = new ColumnReference("testing");
        DateFilter isSunday = new DateFilter(reference, PackedLocalDate::isMonday, PackedLocalDateTime::isMonday);
        Selection selection = isSunday.apply(table);
        assertFalse(selection.contains(0));
        assertTrue(selection.contains(1));
        assertFalse(selection.contains(2));
    }

    @Test
    public void testIsFebruary() {
        ColumnReference reference = new ColumnReference("testing");
        DateFilter isFebruary = new DateFilter(reference, PackedLocalDate::isInFebruary, PackedLocalDateTime::isInFebruary);
        Selection selection = isFebruary.apply(table);
        assertTrue(selection.contains(0));
        assertTrue(selection.contains(1));
        assertFalse(selection.contains(2));
    }

    @Test
    public void testIsMarch() {
        ColumnReference reference = new ColumnReference("testing");
        DateFilter result = new DateFilter(reference, PackedLocalDate::isInMarch, PackedLocalDateTime::isInMarch);
        Selection selection = result.apply(table);
        assertFalse(selection.contains(0));
        assertFalse(selection.contains(1));
        assertTrue(selection.contains(2));
    }

    @Test
    public void testIsFirstDayOfTheMonth() {
        ColumnReference reference = new ColumnReference("testing");
        DateFilter result = new DateFilter(reference, PackedLocalDate::isFirstDayOfMonth, PackedLocalDateTime::isFirstDayOfMonth);
        Selection selection = result.apply(table);
        assertFalse(selection.contains(0));
        assertFalse(selection.contains(1));
        assertTrue(selection.contains(2));
    }

    @Test
    public void testIsLastDayOfTheMonth() {
        ColumnReference reference = new ColumnReference("testing");
        DateFilter result = new DateFilter(reference, PackedLocalDate::isLastDayOfMonth, PackedLocalDateTime::isLastDayOfMonth);
        Selection selection = result.apply(table);
        assertFalse(selection.contains(0));
        assertTrue(selection.contains(1));
        assertFalse(selection.contains(2));
    }

    @Test
    public void testIsInYear() {
        ColumnReference reference = new ColumnReference("testing");
        DateBiFilter result = new DateBiFilter(reference, PackedLocalDate::isInYear, LongLongPredicate.isInYear, 2016);

        Selection selection = result.apply(table);
        assertTrue(selection.contains(0));
        assertTrue(selection.contains(1));
        assertTrue(selection.contains(2));
        result = new DateBiFilter(reference, PackedLocalDate::isInYear, LongLongPredicate.isInYear, 2015);
        selection = result.apply(table);
        assertFalse(selection.contains(0));
        assertFalse(selection.contains(1));
        assertFalse(selection.contains(2));
    }

    @Test
    public void testColumnFilters() {

        LocalDatePredicate after_2_28 = new LocalDatePredicate() {
            LocalDate date = LocalDate.of(2016, 2, 28);

            @Override
            public boolean test(LocalDate i) {
                return i.isAfter(date);
            }
        };

        DateColumn filtered = localDateColumn.selectIf(after_2_28);

    }

    private void print(Object o) {
        System.out.println(o);
    }
}
