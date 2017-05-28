package com.github.lwhite1.tablesaw.reducing;

import com.github.lwhite1.tablesaw.api.DoubleColumn;
import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.descriptive.moment.Kurtosis;
import org.apache.commons.math3.stat.descriptive.moment.Skewness;

/**
 * Contains common utilities for double and long types
 */
public class NumericReduceUtils {

    // TODO(lwhite): Re-implement these methods to work natively with float[], instead of converting to double[]
    /**
     * A function that calculates the mean of the values in the column param
     */
    public static NumericReduceFunction mean = new NumericReduceFunction() {

        @Override
        public String functionName() {
            return "Mean";
        }

        @Override
        public double reduce(double[] data) {
            return StatUtils.mean(data);
        }
    };

    /**
     * A function that calculates the sum of the values in the column param
     */
    public static NumericReduceFunction sum = new NumericReduceFunction() {

        @Override
        public String functionName() {
            return "Sum";
        }

        @Override
        public double reduce(double[] data) {
            return StatUtils.sum(data);
        }

        @Override
        public double reduce(DoubleColumn floatColumn) {
            float sum;
            sum = 0.0f;
            for (double value : floatColumn) {
                if (value != Float.NaN) {
                    sum += value;
                }
            }
            return sum;
        }
    };

    public static NumericReduceFunction median = new NumericReduceFunction() {

        @Override
        public String functionName() {
            return "Median";
        }

        @Override
        public double reduce(double[] data) {
            return percentile(data, 50.0);
        }
    };

    public static NumericReduceFunction n = new NumericReduceFunction() {

        @Override
        public String functionName() {
            return "N";
        }

        //TODO: Consider whether we should provide a count without missing values
        @Override
        public double reduce(double[] data) {
            return data.length;
        }
    };

    public static NumericReduceFunction quartile1 = new NumericReduceFunction() {

        @Override
        public String functionName() {
            return "First Quartile";
        }

        @Override
        public double reduce(double[] data) {
            return percentile(data, 25.0);
        }
    };

    public static NumericReduceFunction quartile3 = new NumericReduceFunction() {

        @Override
        public String functionName() {
            return "Third Quartile";
        }

        @Override
        public double reduce(double[] data) {
            return percentile(data, 75.0);
        }
    };

    public static NumericReduceFunction percentile90 = new NumericReduceFunction() {

        @Override
        public String functionName() {
            return "90th Percentile";
        }

        @Override
        public double reduce(double[] data) {
            return percentile(data, 90.0);
        }
    };

    public static NumericReduceFunction percentile95 = new NumericReduceFunction() {

        @Override
        public String functionName() {
            return "95th Percentile";
        }

        @Override
        public double reduce(double[] data) {
            return percentile(data, 95.0);
        }
    };

    public static NumericReduceFunction percentile99 = new NumericReduceFunction() {

        @Override
        public String functionName() {
            return "99th Percentile";
        }

        @Override
        public double reduce(double[] data) {
            return percentile(data, 99.0);
        }
    };

    public static NumericReduceFunction range = new NumericReduceFunction() {

        @Override
        public String functionName() {
            return "Range";
        }

        @Override
        public double reduce(double[] data) {
            return StatUtils.max(data) - StatUtils.min(data);
        }
    };

    public static NumericReduceFunction min = new NumericReduceFunction() {

        @Override
        public String functionName() {
            return "Min";
        }

        @Override
        public double reduce(double[] data) {
            return StatUtils.min(data);
        }
    };

    public static NumericReduceFunction max = new NumericReduceFunction() {

        @Override
        public String functionName() {
            return "Max";
        }

        @Override
        public double reduce(double[] data) {
            return StatUtils.max(data);
        }
    };

    public static NumericReduceFunction product = new NumericReduceFunction() {

        @Override
        public String functionName() {
            return "Product";
        }

        @Override
        public double reduce(double[] data) {
            return StatUtils.product(data);
        }
    };

    public static NumericReduceFunction geometricMean = new NumericReduceFunction() {

        @Override
        public String functionName() {
            return "Geometric Mean";
        }

        @Override
        public double reduce(double[] data) {
            return StatUtils.geometricMean(data);
        }
    };

    public static NumericReduceFunction populationVariance = new NumericReduceFunction() {

        @Override
        public String functionName() {
            return "Population Variance";
        }

        @Override
        public double reduce(double[] data) {
            return StatUtils.populationVariance(data);
        }
    };

    /**
     * Returns the quadratic mean, aka, the root-mean-square
     */
    public static NumericReduceFunction quadraticMean = new NumericReduceFunction() {

        @Override
        public String functionName() {
            return "Quadratic Mean";
        }

        @Override
        public double reduce(double[] data) {
            return new DescriptiveStatistics(data).getQuadraticMean();
        }
    };

    public static NumericReduceFunction kurtosis = new NumericReduceFunction() {

        @Override
        public String functionName() {
            return "Kurtosis";
        }

        @Override
        public double reduce(double[] data) {
            return new Kurtosis().evaluate(data, 0, data.length);
        }
    };

    public static NumericReduceFunction skewness = new NumericReduceFunction() {

        @Override
        public String functionName() {
            return "Skewness";
        }

        @Override
        public double reduce(double[] data) {
            return new Skewness().evaluate(data, 0, data.length);
        }
    };

    public static NumericReduceFunction sumOfSquares = new NumericReduceFunction() {

        @Override
        public String functionName() {
            return "Sum of Squares";
        }

        @Override
        public double reduce(double[] data) {
            return StatUtils.sumSq(data);
        }
    };

    public static NumericReduceFunction sumOfLogs = new NumericReduceFunction() {

        @Override
        public String functionName() {
            return "Sum of Logs";
        }

        @Override
        public double reduce(double[] data) {
            return StatUtils.sumLog(data);
        }
    };

    public static NumericReduceFunction variance = new NumericReduceFunction() {

        @Override
        public String functionName() {
            return "Variance";
        }

        @Override
        public double reduce(double[] data) {
            return StatUtils.variance(data);
        }
    };

    public static NumericReduceFunction stdDev = new NumericReduceFunction() {

        @Override
        public String functionName() {
            return "Std. Deviation";
        }

        @Override
        public double reduce(double[] data) {
            return Math.sqrt(StatUtils.variance(data));
        }
    };

    public static double percentile(double[] data, double percentile) {
        return StatUtils.percentile(data, percentile);
    }
}
