package com.github.lwhite1.tablesaw.filtering;

import com.github.lwhite1.tablesaw.api.DoubleColumn;
import com.github.lwhite1.tablesaw.api.Table;
import com.github.lwhite1.tablesaw.columns.ColumnReference;
import com.github.lwhite1.tablesaw.util.Selection;

/**
 */
public class DoubleBetween extends ColumnFilter {
    private int low;
    private int high;

    public DoubleBetween(ColumnReference reference, int lowValue, int highValue) {
        super(reference);
        this.low = lowValue;
        this.high = highValue;
    }

    public Selection apply(Table relation) {
        DoubleColumn intColumn = (DoubleColumn) relation.column(columnReference.getColumnName());
        Selection matches = intColumn.isGreaterThan(low);
        matches.toBitmap().and(intColumn.isLessThan(high).toBitmap());
        return matches;
    }
}
