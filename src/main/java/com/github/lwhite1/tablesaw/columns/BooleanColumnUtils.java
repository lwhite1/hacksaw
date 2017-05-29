package com.github.lwhite1.tablesaw.columns;

import com.github.lwhite1.tablesaw.api.BooleanColumn;
import com.github.lwhite1.tablesaw.filtering.predicates.BooleanPredicate;
import it.unimi.dsi.fastutil.ints.IntIterable;

/**
 *
 */
public interface BooleanColumnUtils extends Column, IntIterable {

    BooleanPredicate isMissing = i -> i == Byte.MIN_VALUE;

    BooleanPredicate isNotMissing = i -> i != BooleanColumn.MISSING_VALUE;

    BooleanPredicate isTrue = i -> i == 1;

    BooleanPredicate isFalse = i -> i == 0;
}
