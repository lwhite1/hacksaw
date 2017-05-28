package com.github.lwhite1.tablesaw.filtering;

import com.github.lwhite1.tablesaw.api.DoubleColumn;
import com.github.lwhite1.tablesaw.api.Table;
import com.github.lwhite1.tablesaw.columns.ColumnReference;
import com.github.lwhite1.tablesaw.util.Selection;

import static com.github.lwhite1.tablesaw.columns.DoubleColumnUtils.isGreaterThan;

/**
 */
public class DoubleGreaterThan extends ColumnFilter {

    private float value;

    public DoubleGreaterThan(ColumnReference reference, float value) {
        super(reference);
        this.value = value;
    }

    public Selection apply(Table relation) {
        DoubleColumn floatColumn = (DoubleColumn) relation.column(columnReference.getColumnName());
        return floatColumn.select(isGreaterThan, value);
    }
}
