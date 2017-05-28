package com.github.lwhite1.tablesaw.api.plot;

import com.github.lwhite1.tablesaw.api.DoubleColumn;
import com.github.lwhite1.tablesaw.plotting.xchart.XchartLine;

/**
 *
 */
public class Line {

    public static void show(String chart, DoubleColumn x, DoubleColumn y) {
        XchartLine.show(chart, x, y);
    }
}
