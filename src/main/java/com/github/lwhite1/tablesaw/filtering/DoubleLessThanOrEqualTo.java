package com.github.lwhite1.tablesaw.filtering;

import com.github.lwhite1.tablesaw.api.DoubleColumn;
import com.github.lwhite1.tablesaw.api.Table;
import com.github.lwhite1.tablesaw.columns.ColumnReference;
import com.github.lwhite1.tablesaw.util.Selection;

/**
 */
public class DoubleLessThanOrEqualTo extends ColumnFilter {

    private float value;

    public DoubleLessThanOrEqualTo(ColumnReference reference, float value) {
        super(reference);
        this.value = value;
    }

    public Selection apply(Table relation) {
        DoubleColumn floatColumn = (DoubleColumn) relation.column(columnReference.getColumnName());
        return floatColumn.isLessThanOrEqualTo(value);
    }
}
