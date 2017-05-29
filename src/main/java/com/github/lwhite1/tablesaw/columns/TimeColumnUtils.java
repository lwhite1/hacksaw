package com.github.lwhite1.tablesaw.columns;

import com.github.lwhite1.tablesaw.api.TimeColumn;
import com.github.lwhite1.tablesaw.filtering.predicates.IntIntPredicate;
import com.github.lwhite1.tablesaw.filtering.predicates.IntPredicate;
import it.unimi.dsi.fastutil.ints.IntArrayList;

import java.time.LocalTime;

/**
 *
 */
public interface TimeColumnUtils extends Column, Iterable<LocalTime> {

    IntPredicate isMissing = i -> i == TimeColumn.MISSING_VALUE;
    IntPredicate isNotMissing = i -> i != TimeColumn.MISSING_VALUE;

    IntIntPredicate isGreaterThan = (valueToTest, valueToCompareAgainst) -> valueToTest > valueToCompareAgainst;
    IntIntPredicate isGreaterThanOrEqualTo = (valueToTest, valueToCompareAgainst) -> valueToTest >=
            valueToCompareAgainst;
    IntIntPredicate isLessThan = (valueToTest, valueToCompareAgainst) -> valueToTest < valueToCompareAgainst;
    IntIntPredicate isLessThanOrEqualTo = (valueToTest, valueToCompareAgainst) -> valueToTest <= valueToCompareAgainst;
    IntIntPredicate isEqualTo = (valueToTest, valueToCompareAgainst) -> valueToTest == valueToCompareAgainst;

    IntArrayList data();
}
