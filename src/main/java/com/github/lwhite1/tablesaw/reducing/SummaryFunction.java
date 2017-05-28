package com.github.lwhite1.tablesaw.reducing;

import com.github.lwhite1.tablesaw.api.Table;
import com.github.lwhite1.tablesaw.reducing.NumericReduceFunction;
import com.github.lwhite1.tablesaw.reducing.NumericSummaryTable;
import com.github.lwhite1.tablesaw.table.ViewGroup;

/**
 *
 */
public class SummaryFunction {

    private final Table original;
    private final String summarizedColumnName;
    private final NumericReduceFunction function;

    public SummaryFunction(Table original, String summarizedColumnName, NumericReduceFunction function) {
        this.original = original;
        this.summarizedColumnName = summarizedColumnName;
        this.function = function;
    }

    public String summarizedColumnName() {
        return summarizedColumnName;
    }

    Table original() {
        return original;
    }

    public NumericSummaryTable by(String... columnNames) {
        ViewGroup group = ViewGroup.create(original(), columnNames);
        return group.reduce(summarizedColumnName(), function());
    }

    /**
     * Returns the result of applying to the function to all the values in the appropriate column
     */
    public double get() {
        return original.reduce(summarizedColumnName, function());
    }

    public NumericReduceFunction function() {
        return function;
    }
}
