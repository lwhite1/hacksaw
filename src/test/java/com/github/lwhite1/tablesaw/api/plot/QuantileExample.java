package com.github.lwhite1.tablesaw.api.plot;

import com.github.lwhite1.tablesaw.api.DoubleColumn;
import com.github.lwhite1.tablesaw.api.Table;

/**
 *
 */
public class QuantileExample {

    public static void main(String[] args) throws Exception {
        Table baseball = Table.createFromCsv("data/baseball.csv");
        DoubleColumn x = baseball.nCol("BA");
        Quantile.show("Distribution of team batting averages", x);
    }
}