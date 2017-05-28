package com.github.lwhite1.tablesaw.api.plot;

import com.github.lwhite1.tablesaw.api.DoubleColumn;
import com.github.lwhite1.tablesaw.api.Table;

/**
 *
 */
public class LinePlotExample {

    public static void main(String[] args) throws Exception {
        Table baseball = Table.createFromCsv("data/boston-robberies.csv");
        DoubleColumn x = baseball.nCol("Record");
        DoubleColumn y = baseball.nCol("Robberies");
        Line.show("Monthly Boston Armed Robberies Jan. 1966 - Oct. 1975", x, y);
    }
}