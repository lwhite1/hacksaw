package com.github.lwhite1.tablesaw;

import com.github.lwhite1.tablesaw.api.CategoryColumn;
import com.github.lwhite1.tablesaw.api.Table;
import com.github.lwhite1.tablesaw.table.SubTable;
import com.github.lwhite1.tablesaw.table.TableGroup;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests grouping and aggregation operations on tables
 */
public class TableGroupTest {

    private Table table;

    @Before
    public void setUp() throws Exception {
        table = Table.createFromCsv( "data/BushApproval.csv");
    }

    @Test
    public void testGetSubTables() {
        TableGroup tableGroup = new TableGroup(table, table.column("who"));
        List<SubTable> tables = tableGroup.getSubTables();
        assertEquals(6, tables.size());
    }

    @Test
    public void testWith2GroupingCols() {
        CategoryColumn month = table.dateColumn(0).month();
        month.setName("month");
        table.addColumn(month);
        String[] splitColumnNames = {table.column(2).name(), "month"};
        TableGroup tableGroup = new TableGroup(table, splitColumnNames);
        List<SubTable> tables = tableGroup.getSubTables();
      //  Table t = table.sum("approval").by(splitColumnNames);

        // compare the sum of the original column with the sum of the sums of the group table
     //   assertEquals(table.numericColumn(1).sum(), Math.round(t.numericColumn(2).sum()), 0.001);
        assertEquals(65, tables.size());
    }

/*
    @Test
    public void testCountByGroup() {
        Table groups = table.count("approval").by("who");
        assertEquals(2, groups.columnCount());
        assertEquals(6, groups.rowCount());
        CategoryColumn group = groups.categoryColumn(0);
        assertTrue(group.contains("fox"));
    }

    @Test
    public void testSumGroup() {
        Table groups = table.sum("approval").by("who");
        // compare the sum of the original column with the sum of the sums of the group table
        assertEquals(table.numericColumn(1).sum(), Math.round(groups.numericColumn(1).sum()), 0.001);
    }
*/
}