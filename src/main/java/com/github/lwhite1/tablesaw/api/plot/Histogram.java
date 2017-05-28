package com.github.lwhite1.tablesaw.api.plot;

import com.github.lwhite1.tablesaw.api.DoubleColumn;
import com.github.lwhite1.tablesaw.plotting.smile.SmileHistogram;

/**
 *
 */
public class Histogram {


    public static void show(DoubleColumn x) {
        SmileHistogram.show(x);
    }

    public static void show(double[] x) {
        SmileHistogram.show(x);
    }

    public static void show(String title, DoubleColumn x) {
        SmileHistogram.show(title, x);
    }
}
