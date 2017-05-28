package com.github.lwhite1.tablesaw.index;

import com.github.lwhite1.tablesaw.api.Table;
import com.github.lwhite1.tablesaw.columns.DateColumnUtils;
import com.github.lwhite1.tablesaw.columns.DoubleColumnUtils;
import com.github.lwhite1.tablesaw.columns.packeddata.PackedLocalDate;
import com.github.lwhite1.tablesaw.util.Selection;
import com.google.common.base.Stopwatch;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class IntIndexTest {
    
    private DoubleIndex index;
    private DateIndex dateIndex;
    private Table table;

    @Before
    public void setUp() throws Exception {
        Stopwatch stopwatch = Stopwatch.createStarted();
        table = Table.createFromCsv("data/BushApproval.csv");
        index = new DoubleIndex(table.doubleColumn("approval"));
        dateIndex = new DateIndex(table.dateColumn("date"));
    }

    @Test
    public void testGet() {
        Selection fromCol = table.doubleColumn("approval").select(DoubleColumnUtils.isEqualTo, 71);
        Selection fromIdx = index.get(71);
        assertEquals(fromCol, fromIdx);
    }

    @Test
    public void testGet2() {
        LocalDate date = LocalDate.of(2001, 12, 12);
        int packedDate = PackedLocalDate.pack(date);
        Selection fromCol = table.dateColumn("date").select(DateColumnUtils.isEqualTo, packedDate);
        Selection fromIdx = dateIndex.get(date);
        assertEquals(fromCol, fromIdx);
    }

    @Test
    public void testGTE() {
        Selection fromCol = table.doubleColumn("approval").select(DoubleColumnUtils.isGreaterThanOrEqualTo, 71);
        Selection fromIdx = index.atLeast(71);
        assertEquals(fromCol, fromIdx);
    }

    @Test
    public void testGTE2() {
        LocalDate date = LocalDate.of(2001, 12, 12);
        int packedDate = PackedLocalDate.pack(date);
        Selection fromCol = table.dateColumn("date").select(DateColumnUtils.isGreaterThanOrEqualTo, packedDate);
        Selection fromIdx = dateIndex.atLeast(date);
        assertEquals(fromCol, fromIdx);
    }
}