package com.github.lwhite1.tablesaw.filtering;

import com.github.lwhite1.tablesaw.api.DoubleColumn;
import com.github.lwhite1.tablesaw.api.Table;
import com.github.lwhite1.tablesaw.columns.ColumnReference;
import com.github.lwhite1.tablesaw.util.Selection;
import it.unimi.dsi.fastutil.doubles.DoubleArrayList;
import it.unimi.dsi.fastutil.doubles.DoubleSet;
import it.unimi.dsi.fastutil.ints.IntSet;

/**
 */
public class DoubleIsIn extends ColumnFilter {

    private DoubleColumn filterColumn;

    public DoubleIsIn(ColumnReference reference, DoubleColumn filterColumn) {
        super(reference);
        this.filterColumn = filterColumn;
    }

    public DoubleIsIn(ColumnReference reference, double... doubles) {
        super(reference);
        this.filterColumn = DoubleColumn.create("temp", new DoubleArrayList(doubles));
    }

    public Selection apply(Table relation) {
        DoubleColumn doubleColumn = (DoubleColumn) relation.column(columnReference.getColumnName());
        DoubleSet firstSet = doubleColumn.asSet();
        firstSet.retainAll(filterColumn.data());
        return doubleColumn.select(firstSet::contains);
    }
}
