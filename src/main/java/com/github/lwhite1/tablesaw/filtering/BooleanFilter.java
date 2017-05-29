package com.github.lwhite1.tablesaw.filtering;

import com.github.lwhite1.tablesaw.api.BooleanColumn;
import com.github.lwhite1.tablesaw.api.Table;
import com.github.lwhite1.tablesaw.columns.ColumnReference;
import com.github.lwhite1.tablesaw.filtering.predicates.BooleanPredicate;
import com.github.lwhite1.tablesaw.filtering.predicates.StringPredicate;
import com.github.lwhite1.tablesaw.util.Selection;

/**
 */
public class BooleanFilter extends ColumnFilter {

    private final BooleanPredicate predicate;

    public BooleanFilter(ColumnReference columnReference, BooleanPredicate predicate) {
        super(columnReference);
        this.predicate = predicate;
    }

    @Override
    public Selection apply(Table relation) {
        BooleanColumn booleanColumn = relation.booleanColumn(columnReference.getColumnName());
        return booleanColumn.select(predicate);
    }
}
