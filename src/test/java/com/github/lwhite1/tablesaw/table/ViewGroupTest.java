package com.github.lwhite1.tablesaw.table;

import com.github.lwhite1.tablesaw.api.CategoryColumn;
import com.github.lwhite1.tablesaw.api.Table;
import com.github.lwhite1.tablesaw.reducing.NumericReduceFunction;
import com.github.lwhite1.tablesaw.reducing.NumericReduceUtils;
import org.apache.commons.math3.stat.StatUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.github.lwhite1.tablesaw.reducing.NumericReduceUtils.*;
import static org.junit.Assert.*;

/**
 *
 */
public class ViewGroupTest {

    static NumericReduceFunction exaggerate = new NumericReduceFunction() {
        @Override
        public String functionName() {
            return "exaggeration";
        }

        @Override
        public double reduce(double[] data) {
            return StatUtils.max(data) + 1000;
        }
    };

    private Table table;

    @Before
    public void setUp() throws Exception {
        table = Table.createFromCsv("data/BushApproval.csv");
    }

    @Test
    public void testViewGroupCreation() {

        ViewGroup group = new ViewGroup(table, table.column("who"));
        assertEquals(6, group.size());
        List<TemporaryView> viewList = group.getSubTables();

        int count = 0;
        for (TemporaryView view : viewList) {
            count += view.rowCount();
        }
        assertEquals(table.rowCount(), count);
    }

    @Test
    public void testViewTwoColumn() {

        ViewGroup group = new ViewGroup(table, table.column("who"), table.column("approval"));
        List<TemporaryView> viewList = group.getSubTables();

        int count = 0;
        for (TemporaryView view : viewList) {
            count += view.rowCount();
        }
        assertEquals(table.rowCount(), count);
    }

    @Test
    public void testWith2GroupingCols() {
        CategoryColumn month = table.dateColumn(0).month();
        month.setName("month");
        table.addColumn(month);
        String[] splitColumnNames = {table.column(2).name(), "month"};
        ViewGroup tableGroup = ViewGroup.create(table, splitColumnNames);
        List<TemporaryView> tables = tableGroup.getSubTables();
        Table t = table.summarize("approval", sum).by(splitColumnNames);

        // compare the sum of the original column with the sum of the sums of the group table
        assertEquals(table.numericColumn(1).sum(), Math.round(t.numericColumn(2).sum()), 0.001);
        assertEquals(65, tables.size());
    }

    @Test
    public void testCountByGroup() {
        Table groups = table.summarize("approval", n).by("who");
        assertEquals(2, groups.columnCount());
        assertEquals(6, groups.rowCount());
        CategoryColumn group = groups.categoryColumn(0);
        assertTrue(group.contains("fox"));
    }

    @Test
    public void testCustomFunction() {
        Table exaggeration = table.summarize("approval", exaggerate).by("who");
        CategoryColumn group = exaggeration.categoryColumn(0);
        assertTrue(group.contains("fox"));
    }

    @Test
    public void testSumGroup() {
        Table groups = table.summarize("approval", sum).by("who");
        // compare the sum of the original column with the sum of the sums of the group table
        assertEquals(table.numericColumn(1).sum(), Math.round(groups.numericColumn(1).sum()), 0.001);
    }
}