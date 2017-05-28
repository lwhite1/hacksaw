package com.github.lwhite1.tablesaw.io.html;

import com.github.lwhite1.tablesaw.api.ColumnType;
import com.github.lwhite1.tablesaw.api.Table;
import com.github.lwhite1.tablesaw.columns.Column;
import com.github.lwhite1.tablesaw.io.csv.CsvReader;
import com.github.lwhite1.tablesaw.reducing.NumericReduceUtils;
import com.github.lwhite1.tablesaw.table.ViewGroup;
import org.junit.Before;
import org.junit.Test;

/**
 *
 */
public class HtmlTableWriterTest {

    private Table table;

    @Before
    public void setUp() throws Exception {
        table = Table.createFromCsv("data/BushApproval.csv");
    }

    @Test
    public void testWrite() {
        Column byColumn = table.column("who");
        ViewGroup group = new ViewGroup(table, byColumn);
        Table result = group.reduce("approval", NumericReduceUtils.mean);
        String str = HtmlTableWriter.write(result, "NA");
    }

}