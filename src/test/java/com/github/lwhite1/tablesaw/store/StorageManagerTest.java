package com.github.lwhite1.tablesaw.store;

import com.github.lwhite1.tablesaw.api.CategoryColumn;
import com.github.lwhite1.tablesaw.api.ColumnType;
import com.github.lwhite1.tablesaw.api.DateColumn;
import com.github.lwhite1.tablesaw.api.DoubleColumn;
import com.github.lwhite1.tablesaw.api.Table;
import com.github.lwhite1.tablesaw.io.csv.CsvReader;
import com.github.lwhite1.tablesaw.table.Relation;
import com.google.common.base.Stopwatch;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

import static com.github.lwhite1.tablesaw.api.ColumnType.*;
import static org.junit.Assert.*;

/**
 * Tests for StorageManager
 */
public class StorageManagerTest {

    private static final int COUNT = 5;
    // column types for the tornado table
    private static final ColumnType[] COLUMN_TYPES = {
            DOUBLE,   // number by year
            DOUBLE,   // year
            DOUBLE,   // month
            DOUBLE,   // day
            LOCAL_DATE,  // date
            LOCAL_TIME,  // time
            CATEGORY, // tz
            CATEGORY, // st
            CATEGORY, // state fips
            DOUBLE,    // state torn number
            DOUBLE,    // scale
            DOUBLE,    // injuries
            DOUBLE,    // fatalities
            CATEGORY, // loss
            DOUBLE,   // crop loss
            DOUBLE,   // St. Lat
            DOUBLE,   // St. Lon
            DOUBLE,   // End Lat
            DOUBLE,   // End Lon
            DOUBLE,   // length
            DOUBLE,   // width
            DOUBLE,   // NS
            DOUBLE,   // SN
            DOUBLE,   // SG
            CATEGORY,  // Count FIPS 1-4
            CATEGORY,
            CATEGORY,
            CATEGORY};
    private Relation table = Table.create("t");
    private DoubleColumn floatColumn = DoubleColumn.create("float");
    private CategoryColumn categoryColumn = CategoryColumn.create("cat");
    private DateColumn localDateColumn = DateColumn.create("date");

    public static void main(String[] args) throws Exception {

        Stopwatch stopwatch = Stopwatch.createStarted();
        System.out.println("loading");
        com.github.lwhite1.tablesaw.api.Table tornados = CsvReader.read(COLUMN_TYPES, "data/1950-2014_torn.csv");
        tornados.setName("tornados");
        System.out.println(String.format("loaded %d records in %d seconds",
                tornados.rowCount(),
                stopwatch.elapsed(TimeUnit.SECONDS)));
        System.out.println(tornados.shape());
        System.out.println(tornados.columnNames().toString());
        System.out.println(tornados.first(10).print());
        stopwatch.reset().start();
        StorageManager.saveTable("/tmp/tablesaw/testdata", tornados);
        stopwatch.reset().start();
        tornados = StorageManager.readTable("/tmp/tablesaw/testdata/tornados.saw");
        System.out.println(tornados.first(5).print());
    }

    @Before
    public void setUp() throws Exception {

        for (int i = 0; i < COUNT; i++) {
            floatColumn.append((float) i);
            localDateColumn.append(LocalDate.now());
            categoryColumn.add("Category " + i);
        }
        table.addColumn(floatColumn);
        table.addColumn(localDateColumn);
        table.addColumn(categoryColumn);
    }

    @Test
    public void testCatStorage() throws Exception {
        StorageManager.writeColumn("/tmp/cat_dogs", categoryColumn);
        CategoryColumn readCat = StorageManager.readCategoryColumn("/tmp/cat_dogs", categoryColumn.columnMetadata());
        for (int i = 0; i < categoryColumn.size(); i++) {
            assertEquals(categoryColumn.get(i), readCat.get(i));
        }
    }

    @Test
    public void testWriteTable() throws IOException {
        StorageManager.saveTable("/tmp/zeta", table);
        Table t = StorageManager.readTable("/tmp/zeta/t.saw");
        assertEquals(table.columnCount(), t.columnCount());
        assertEquals(table.rowCount(), t.rowCount());
        for (int i = 0; i < table.rowCount(); i++) {
            assertEquals(categoryColumn.get(i), t.categoryColumn("cat").get(i));
        }
        t.sortOn("cat"); // exercise the column a bit
    }

    @Test
    public void testWriteTableTwice() throws IOException {

        StorageManager.saveTable("/tmp/mytables2", table);
        com.github.lwhite1.tablesaw.api.Table t = StorageManager.readTable("/tmp/mytables2/t.saw");
        t.numericColumn("float").setName("a float column");

        StorageManager.saveTable("/tmp/mytables2", table);
        t = StorageManager.readTable("/tmp/mytables2/t.saw");

        assertEquals(table.name(), t.name());
        assertEquals(table.rowCount(), t.rowCount());
        assertEquals(table.columnCount(), t.columnCount());
    }

    @Test
    public void testSeparator() {
        assertNotNull(StorageManager.separator());
    }
}