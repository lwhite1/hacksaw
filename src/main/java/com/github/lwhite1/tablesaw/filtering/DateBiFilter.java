package com.github.lwhite1.tablesaw.filtering;

import com.github.lwhite1.tablesaw.api.ColumnType;
import com.github.lwhite1.tablesaw.api.DateColumn;
import com.github.lwhite1.tablesaw.api.DateTimeColumn;
import com.github.lwhite1.tablesaw.api.Table;
import com.github.lwhite1.tablesaw.columns.Column;
import com.github.lwhite1.tablesaw.columns.ColumnReference;
import com.github.lwhite1.tablesaw.util.Selection;

/**
 */
public class DateBiFilter extends ColumnFilter {

    private final IntBiPredicate intPredicate;
    private final LongBiPredicate longPredicate;
    private final int value;

    public DateBiFilter(ColumnReference columnReference, IntBiPredicate intPredicate, LongBiPredicate longPredicate, int value) {
        super(columnReference);
        this.intPredicate = intPredicate;
        this.longPredicate = longPredicate;
        this.value = value;
    }

    @Override
    public Selection apply(Table relation) {
        Column column = relation.column(columnReference.getColumnName());
        ColumnType type = column.type();
        switch (type) {
            case LOCAL_DATE:
                DateColumn dateColumn = relation.dateColumn(columnReference.getColumnName());
                return dateColumn.select(intPredicate, value);
            case LOCAL_DATE_TIME:
                DateTimeColumn dateTimeColumn = relation.dateTimeColumn(columnReference.getColumnName());
                return dateTimeColumn.select(longPredicate, value);
            default:
                throw new UnsupportedOperationException("Columns of type " + type.name() + " do not support date operations ");

        }
    }
}
