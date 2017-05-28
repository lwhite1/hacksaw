package com.github.lwhite1.tablesaw.io.csv;

import com.github.lwhite1.tablesaw.api.ColumnType;
import com.github.lwhite1.tablesaw.api.DoubleColumn;
import com.github.lwhite1.tablesaw.api.Table;
import com.github.lwhite1.tablesaw.columns.Column;
import org.junit.Ignore;
import org.junit.Test;

import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;

import static com.github.lwhite1.tablesaw.api.ColumnType.*;
import static com.github.lwhite1.tablesaw.api.QueryHelper.column;
import static org.junit.Assert.*;

/**
 * Tests for CSV Reading
 */
public class CsvReaderTest {

    private final ColumnType[] bus_types = {DOUBLE, CATEGORY, CATEGORY, DOUBLE, DOUBLE};

    @Test
    public void testWithBusData() throws Exception {
        // Read the CSV file
        Table table = CsvReader.read(bus_types, true, ',', "data/bus_stop_test.csv");

        // Look at the column names
        assertEquals("[stop_id, stop_name, stop_desc, stop_lat, stop_lon]", table.columnNames().toString());

        table = table.sortDescendingOn("stop_id");
        table.removeColumns("stop_desc");

        Column c = table.numericColumn("stop_lat");
        Table v = table.selectWhere(column("stop_lon").isGreaterThan(-0.1f));
    }

    @Test
    public void testWithBushData() throws Exception {

        // Read the CSV file
        ColumnType[] types = {LOCAL_DATE, DOUBLE, CATEGORY};
        Table table = CsvReader.read(types, "data/BushApproval.csv");

        assertEquals(323, table.rowCount());

        // Look at the column names
        assertEquals("[date, approval, who]", table.columnNames().toString());
    }

    @Test
    public void testBushDataWithoutSamplingForTypeDetection() throws Exception {

        // Read the CSV file
        Table table = CsvReader.read("data/BushApproval.csv", true, ',', true);

        assertEquals(323, table.rowCount());

        // Look at the column names
        assertEquals("[date, approval, who]", table.columnNames().toString());
    }


    @Test
    public void testDataTypeDetection() throws Exception {
        ColumnType[] columnTypes = CsvReader.detectColumnTypes("data/bus_stop_test.csv", true, ',', false);
        assertTrue(Arrays.equals(bus_types, columnTypes));
    }

    @Test
    public void testPrintStructure() throws Exception {
        String output =
                "ColumnType[] columnTypes = {\n" +
                        "LOCAL_DATE, // 0.0   date        \n" +
                        "DOUBLE,     // 1.0   approval    \n" +
                        "CATEGORY,   // 2.0   who         \n" +
                        "}\n";
        assertEquals(output, CsvReader.printColumnTypes("data/BushApproval.csv", true, ','));
    }

    @Test
    public void testDataTypeDetection2() throws Exception {
        ColumnType[] columnTypes = CsvReader.detectColumnTypes("data/BushApproval.csv", true, ',', false);
        assertEquals(ColumnType.LOCAL_DATE, columnTypes[0]);
        assertEquals(ColumnType.DOUBLE, columnTypes[1]);
        assertEquals(ColumnType.CATEGORY, columnTypes[2]);
    }

    @Ignore
    @Test
    public void testLoadFromUrl() throws Exception {
        ColumnType[] types = {LOCAL_DATE, DOUBLE, CATEGORY};
        String location = "https://raw.githubusercontent.com/lwhite1/tablesaw/master/data/BushApproval.csv";
        Table table;
        try (InputStream input = new URL(location).openStream()) {
            table = CsvReader.read("Bush approval ratings", types, true, ',', input);
        }
        assertNotNull(table);
    }

    @Test
    public void testBoundary1() throws Exception {
        Table table1 = Table.createFromCsv("data/boundaryTest1.csv");
        table1.structure();  // just make sure the import completed
    }

    @Test
    public void testBoundary2() throws Exception {
        Table table1 = Table.createFromCsv("data/boundaryTest2.csv");
        table1.structure(); // just make sure the import completed
    }

    @Test
    public void testReadFailure() throws Exception {
        Table table1 = Table.createFromCsv("data/read_failure_test.csv");
        table1.structure(); // just make sure the import completed
        DoubleColumn test = table1.numericColumn("Test");
        System.out.println(test.summary().print());
    }

    @Test
    public void testReadFailure2() throws Exception {
        Table table1 = Table.createFromCsv("data/read_failure_test2.csv");
        table1.structure(); // just make sure the import completed
        DoubleColumn test = table1.numericColumn("Test");
        System.out.println(test.summary().print());
    }
}
