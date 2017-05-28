package com.github.lwhite1.tablesaw;

import com.github.lwhite1.tablesaw.api.DateColumn;
import com.github.lwhite1.tablesaw.api.DoubleColumn;
import com.github.lwhite1.tablesaw.api.Table;
import com.github.lwhite1.tablesaw.columns.packeddata.PackedLocalDate;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static com.github.lwhite1.tablesaw.api.QueryHelper.*;
import static org.junit.Assert.*;

/**
 * Tests for filtering on the T class
 */
public class TableFilteringTest {

    private Table table;

    @Before
    public void setUp() throws Exception {
        table = Table.createFromCsv("data/BushApproval.csv");
    }

    @Test
    public void testFilter1() {
        Table result = table.selectWhere(column("approval").isLessThan(70));
        DoubleColumn a = result.doubleColumn("approval");
        for (double v : a) {
            assertTrue(v < 70);
        }
    }

    @Test
    public void testFilter2() {
        Table result = table.selectWhere(column("date").isInApril());
        DateColumn d = result.dateColumn("date");
        for (LocalDate v : d) {
            assertTrue(PackedLocalDate.isInApril(PackedLocalDate.pack(v)));
        }
    }

    @Test
    public void testFilter3() {
        Table result = table.selectWhere(
                both(column("date").isInApril(),
                        column("approval").isGreaterThan(70)));

        DateColumn dates = result.dateColumn("date");
        DoubleColumn approval = result.doubleColumn("approval");
        for (int row : result) {
            assertTrue(PackedLocalDate.isInApril(dates.getInt(row)));
            assertTrue(approval.get(row) > 70);
        }
    }

    @Test
    public void testFilter4() {
        Table result =
                table.select("who", "approval")
                        .where(
                                and(column("date").isInApril(),
                                        column("approval").isGreaterThan(70)));
        assertEquals(2, result.columnCount());
        assertTrue(result.columnNames().contains("who"));
        assertTrue(result.columnNames().contains("approval"));
    }
}
