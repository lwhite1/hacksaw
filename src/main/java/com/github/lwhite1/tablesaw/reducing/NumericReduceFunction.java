package com.github.lwhite1.tablesaw.reducing;

import com.github.lwhite1.tablesaw.api.DoubleColumn;

/**
 * Functions that calculate values over the data of an entire column, such as sum, mean, std. dev, etc.
 */
public interface NumericReduceFunction {

    String functionName();

    double reduce(double[] data);

    default double reduce(DoubleColumn doubles) {
        return this.reduce(doubles.toDoubleArray());
    }
}
