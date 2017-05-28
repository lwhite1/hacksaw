package com.github.lwhite1.tablesaw.api.plot;

import com.github.lwhite1.tablesaw.api.DoubleColumn;
import com.github.lwhite1.tablesaw.plotting.xchart.XchartQuantile;

/**
 *
 */
public class Quantile {

    public static void show(String chartTitle, DoubleColumn yColumn) {
        double[] x = new double[yColumn.size()];

        for (int i = 0; i < x.length; i++) {
            x[i] = i / (float) x.length;
        }

        DoubleColumn copy = (DoubleColumn) yColumn.copy();
        copy.sortAscending();
        show(chartTitle, x, copy);
    }

    public static void show(String chartTitle, double[] xData, DoubleColumn yColumn) {

        // Create Chart
        XchartQuantile.show(chartTitle, xData, yColumn, 600, 400);
/*
    THIS CODE IS FOR A GLIMPSE-BACKED CHART
    double[] yData = yColumn.toDoubleArray();
    XchartScatter.SimpleScatter scatter = new XchartScatter.SimpleScatter(chartTitle, xData, yData);
    try {
      Display.showWithSwing(scatter);
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
*/
    }
}
