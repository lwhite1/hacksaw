package com.github.lwhite1.tablesaw.columns;

import com.github.lwhite1.tablesaw.api.DateTimeColumn;
import com.github.lwhite1.tablesaw.filtering.predicates.LongPredicate;
import it.unimi.dsi.fastutil.longs.LongArrayList;

/**
 *
 */
public interface DateTimeColumnUtils extends Column {

    LongPredicate isMissing = i -> i == DateTimeColumn.MISSING_VALUE;
    LongPredicate isNotMissing = i -> i != DateTimeColumn.MISSING_VALUE;

    LongArrayList data();
}
