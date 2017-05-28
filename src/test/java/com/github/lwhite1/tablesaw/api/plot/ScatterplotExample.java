package com.github.lwhite1.tablesaw.api.plot;

import com.github.lwhite1.tablesaw.api.DoubleColumn;
import com.github.lwhite1.tablesaw.api.Table;

/**
 *
 */
public class ScatterplotExample {

    public static void main(String[] args) throws Exception {
        Table baseball = Table.createFromCsv("data/baseball.csv");
        DoubleColumn x = baseball.nCol("BA");
        DoubleColumn y = baseball.nCol("W");
        Scatter.show(x, y);

        Scatter.show("Regular season wins by year",
                baseball.numericColumn("W"),
                baseball.numericColumn("Year"),
                baseball.splitOn(baseball.column("Playoffs")));
    }
}