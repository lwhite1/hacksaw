package com.github.lwhite1.tablesaw.filtering;

import com.github.lwhite1.tablesaw.api.CategoryColumn;
import com.github.lwhite1.tablesaw.api.Table;
import com.github.lwhite1.tablesaw.columns.ColumnReference;
import com.github.lwhite1.tablesaw.filtering.predicates.StringPredicate;
import com.github.lwhite1.tablesaw.util.Selection;

/**
 */
public class StringFilter extends ColumnFilter {

    private final StringPredicate predicate;

    public StringFilter(ColumnReference columnReference, StringPredicate predicate) {
        super(columnReference);
        this.predicate = predicate;
    }

    @Override
    public Selection apply(Table relation) {
        CategoryColumn categoryColumn = relation.categoryColumn(columnReference.getColumnName());
        return categoryColumn.select(predicate);
    }
}
