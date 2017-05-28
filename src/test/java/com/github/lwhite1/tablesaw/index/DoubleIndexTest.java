package com.github.lwhite1.tablesaw.index;

import com.github.lwhite1.tablesaw.api.Table;
import com.github.lwhite1.tablesaw.columns.DoubleColumnUtils;
import com.github.lwhite1.tablesaw.util.Selection;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class DoubleIndexTest {

    private DoubleIndex index;
    private Table table;

    @Before
    public void setUp() throws Exception {
        table = Table.createFromCsv("data/bus_stop_test.csv");
        index = new DoubleIndex(table.numericColumn("stop_lat"));
    }

    @Test
    public void testGet() {
        Selection fromCol = table.numericColumn("stop_lat").select(DoubleColumnUtils.isEqualTo, 30.330425f);
        Selection fromIdx = index.get(30.330425f);
        assertEquals(fromCol, fromIdx);
    }

    @Test
    public void testGTE() {
        Selection fromCol = table.numericColumn("stop_lat").select(DoubleColumnUtils.isGreaterThanOrEqualTo, 30.330425f);
        Selection fromIdx = index.atLeast(30.330425f);
        assertEquals(fromCol, fromIdx);
    }

    @Test
    public void testLTE() {
        Selection fromCol = table.numericColumn("stop_lat").select(DoubleColumnUtils.isLessThanOrEqualTo, 30.330425f);
        Selection fromIdx = index.atMost(30.330425f);
        assertEquals(fromCol, fromIdx);
    }

    @Test
    public void testLT() {
        Selection fromCol = table.numericColumn("stop_lat").select(DoubleColumnUtils.isLessThan, 30.330425f);
        Selection fromIdx = index.lessThan(30.330425f);
        assertEquals(fromCol, fromIdx);
    }

    @Test
    public void testGT() {
        Selection fromCol = table.numericColumn("stop_lat").select(DoubleColumnUtils.isGreaterThan, 30.330425f);
        Selection fromIdx = index.greaterThan(30.330425f);
        assertEquals(fromCol, fromIdx);
    }
}