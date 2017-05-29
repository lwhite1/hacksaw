package com.github.lwhite1.tablesaw.filtering;

import com.github.lwhite1.tablesaw.api.ColumnType;
import com.github.lwhite1.tablesaw.api.DateTimeColumn;
import com.github.lwhite1.tablesaw.api.Table;
import com.github.lwhite1.tablesaw.api.TimeColumn;
import com.github.lwhite1.tablesaw.columns.Column;
import com.github.lwhite1.tablesaw.columns.ColumnReference;
import com.github.lwhite1.tablesaw.filtering.predicates.IntPredicate;
import com.github.lwhite1.tablesaw.filtering.predicates.LongPredicate;
import com.github.lwhite1.tablesaw.util.Selection;

/**
 */
public class TimeFilter extends ColumnFilter {

    private final IntPredicate intPredicate;
    private final LongPredicate longPredicate;

    public TimeFilter(ColumnReference columnReference, IntPredicate intPredicate, LongPredicate longPredicate) {
        super(columnReference);
        this.intPredicate = intPredicate;
        this.longPredicate = longPredicate;
    }

    @Override
    public Selection apply(Table relation) {
        Column column = relation.column(columnReference.getColumnName());
        ColumnType type = column.type();
        switch (type) {
            case LOCAL_TIME:
                TimeColumn timeColumn = relation.timeColumn(columnReference.getColumnName());
                return timeColumn.select(intPredicate);
            case LOCAL_DATE_TIME:
                DateTimeColumn dateTimeColumn = relation.dateTimeColumn(columnReference.getColumnName());
                return dateTimeColumn.select(longPredicate);
            default:
                throw new UnsupportedOperationException("Columns of type " + type.name() + " do not support time operations ");

        }
    }
}
