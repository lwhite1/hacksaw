package com.github.lwhite1.tablesaw.columns;

import com.github.lwhite1.tablesaw.api.DateColumn;
import com.github.lwhite1.tablesaw.filtering.predicates.IntIntPredicate;
import com.github.lwhite1.tablesaw.filtering.predicates.IntPredicate;
import it.unimi.dsi.fastutil.ints.IntArrayList;

import java.time.LocalDate;

/**
 *
 */
public interface DateColumnUtils extends Column, Iterable<LocalDate> {

    IntPredicate isMissing = i -> i == DateColumn.MISSING_VALUE;
    IntPredicate isNotMissing = i -> i != DateColumn.MISSING_VALUE;

    IntIntPredicate isGreaterThan = (valueToTest, valueToCompareAgainst) -> valueToTest > valueToCompareAgainst;
    IntIntPredicate isGreaterThanOrEqualTo = (valueToTest, valueToCompareAgainst) -> valueToTest >=
            valueToCompareAgainst;
    IntIntPredicate isLessThan = (valueToTest, valueToCompareAgainst) -> valueToTest < valueToCompareAgainst;
    IntIntPredicate isLessThanOrEqualTo = (valueToTest, valueToCompareAgainst) -> valueToTest <= valueToCompareAgainst;
    IntIntPredicate isEqualTo = (valueToTest, valueToCompareAgainst) -> valueToTest == valueToCompareAgainst;

    IntArrayList data();

}
