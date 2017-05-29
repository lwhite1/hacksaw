package com.github.lwhite1.tablesaw.filtering;

import com.github.lwhite1.tablesaw.api.CategoryColumn;
import com.github.lwhite1.tablesaw.api.Table;
import com.github.lwhite1.tablesaw.columns.ColumnReference;
import com.github.lwhite1.tablesaw.util.Selection;

/**
 */
public class StringBiFilter extends ColumnFilter {

    private final StringBiPredicate predicate;
    private final String value;

    public StringBiFilter(ColumnReference columnReference, StringBiPredicate predicate, String value) {
        super(columnReference);
        this.predicate = predicate;
        this.value = value;
    }

    @Override
    public Selection apply(Table relation) {
        CategoryColumn categoryColumn = relation.categoryColumn(columnReference.getColumnName());
        return categoryColumn.select(predicate, value);
    }
}
