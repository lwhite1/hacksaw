package com.github.lwhite1.tablesaw.util;

import com.github.lwhite1.tablesaw.api.CategoryColumn;
import com.github.lwhite1.tablesaw.api.DoubleColumn;
import com.github.lwhite1.tablesaw.api.Table;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;

/**
 *
 */
public class Stats {

    private long n;
    private double sum;
    private double mean;
    private double min;
    private double max;
    private double variance;
    private double standardDeviation;
    private double geometricMean;
    private double quadraticMean;
    private double secondMoment;
    private double populationVariance;
    private double sumOfLogs;
    private double sumOfSquares;
    private String name;

    public Stats(String name) {
        this.name = name;
    }

    public static Stats create(final DoubleColumn values) {
        SummaryStatistics summaryStatistics = new SummaryStatistics();
        for (double f : values) {
            summaryStatistics.addValue(f);
        }
        return getStats(values, summaryStatistics);
    }

    private static Stats getStats(DoubleColumn values, SummaryStatistics summaryStatistics) {
        Stats stats = new Stats("Column: " + values.name());
        stats.min = summaryStatistics.getMin();
        stats.max = summaryStatistics.getMax();
        stats.n = summaryStatistics.getN();
        stats.sum = summaryStatistics.getSum();
        stats.variance = summaryStatistics.getVariance();
        stats.populationVariance = summaryStatistics.getPopulationVariance();
        stats.quadraticMean = summaryStatistics.getQuadraticMean();
        stats.geometricMean = summaryStatistics.getGeometricMean();
        stats.mean = summaryStatistics.getMean();
        stats.standardDeviation = summaryStatistics.getStandardDeviation();
        stats.sumOfLogs = summaryStatistics.getSumOfLogs();
        stats.sumOfSquares = summaryStatistics.getSumsq();
        stats.secondMoment = summaryStatistics.getSecondMoment();
        return stats;
    }

    public float range() {
        return (float) (max - min);
    }

    public float standardDeviation() {
        return (float) standardDeviation;
    }

    public long n() {
        return n;
    }

    public float mean() {
        return (float) (sum / (double) n);
    }

    public float min() {
        return (float) min;
    }

    public float max() {
        return (float) max;
    }

    public float sum() {
        return (float) sum;
    }

    public float variance() {
        return (float) variance;
    }

    public float sumOfSquares() {
        return (float) sumOfSquares;
    }

    public float populationVariance() {
        return (float) populationVariance;
    }

    public float sumOfLogs() {
        return (float) sumOfLogs;
    }

    public float geometricMean() {
        return (float) geometricMean;
    }

    public float quadraticMean() {
        return (float) quadraticMean;
    }

    public float secondMoment() {
        return (float) secondMoment;
    }

    public Table asTable() {
        Table t = Table.create(name);
        CategoryColumn measure = CategoryColumn.create("Measure");
        DoubleColumn value = DoubleColumn.create("Value");
        t.addColumn(measure);
        t.addColumn(value);

        measure.add("n");
        value.append(n);

        measure.add("sum");
        value.append(sum());

        measure.add("Mean");
        value.append(mean());

        measure.add("Min");
        value.append(min());

        measure.add("Max");
        value.append(max());

        measure.add("Range");
        value.append(range());

        measure.add("Variance");
        value.append(variance());

        measure.add("Std. Dev");
        value.append(standardDeviation());

        return t;
    }

    public Table asTableComplete() {
        Table t = asTable();

        CategoryColumn measure = t.categoryColumn("Measure");
        DoubleColumn value = t.doubleColumn("Value");

        measure.add("Sum of Squares");
        value.append(sumOfSquares());

        measure.add("Sum of Logs");
        value.append(sumOfLogs());

        measure.add("Population Variance");
        value.append(populationVariance());

        measure.add("Geometric Mean");
        value.append(geometricMean());

        measure.add("Quadratic Mean");
        value.append(quadraticMean());

        measure.add("Second Moment");
        value.append(secondMoment());

        return t;
    }
}
