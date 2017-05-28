package com.github.lwhite1.tablesaw.filtering;

import com.github.lwhite1.tablesaw.api.DoubleColumn;
import com.github.lwhite1.tablesaw.api.Table;
import com.github.lwhite1.tablesaw.columns.ColumnReference;
import com.github.lwhite1.tablesaw.util.Selection;

/**
 * A filter for double columns that compares the column value against a given value, using the predicate
 */
public class DoubleBiFilter extends ColumnFilter {

    private final DoubleBiPredicate predicate;
    private final float value;

    public DoubleBiFilter(ColumnReference columnReference, DoubleBiPredicate predicate, float value) {
        super(columnReference);
        this.predicate = predicate;
        this.value = value;
    }

    @Override
    public Selection apply(Table relation) {
        DoubleColumn floatColumn = (DoubleColumn) relation.column(columnReference.getColumnName());
        return floatColumn.select(predicate, value);

    }
}
