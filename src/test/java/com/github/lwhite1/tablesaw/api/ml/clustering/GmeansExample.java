package com.github.lwhite1.tablesaw.api.ml.clustering;

import com.github.lwhite1.tablesaw.api.Table;

import java.util.Arrays;

/**
 * An example program illustrating the use of X-means clustering
 */
public class GmeansExample {

    public static void main(String[] args) throws Exception {

        Table t = Table.createFromCsv("data/whiskey.csv");

        Gmeans model = new Gmeans(
                10,
                t.numericColumn(2),
                t.numericColumn(3),
                t.numericColumn(4),
                t.numericColumn(5),
                t.numericColumn(6),
                t.numericColumn(7),
                t.numericColumn(8),
                t.numericColumn(9),
                t.numericColumn(10),
                t.numericColumn(11),
                t.numericColumn(12),
                t.numericColumn(13)
        );

        out("Distortion: " + model.distortion());
        out("Cluster count: " + model.getClusterCount());
        out(Arrays.toString(model.getClusterLabels()));
        out(Arrays.toString(model.getClusterSizes()));
        out(model.labeledCentroids().print());
    }

    private static void out(Object object) {
        System.out.println(String.valueOf(object));
    }

}