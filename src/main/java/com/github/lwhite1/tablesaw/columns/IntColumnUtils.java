package com.github.lwhite1.tablesaw.columns;

import com.github.lwhite1.tablesaw.filtering.predicates.IntIntPredicate;
import it.unimi.dsi.fastutil.ints.IntIterable;

/**
 *
 */
public interface IntColumnUtils extends Column, IntIterable {

    IntIntPredicate isGreaterThan = (valueToTest, valueToCompareAgainst) -> valueToTest > valueToCompareAgainst;
    IntIntPredicate isGreaterThanOrEqualTo = (valueToTest, valueToCompareAgainst) -> valueToTest >=
            valueToCompareAgainst;

    IntIntPredicate isLessThan = (valueToTest, valueToCompareAgainst) -> valueToTest < valueToCompareAgainst;
    IntIntPredicate isLessThanOrEqualTo = (valueToTest, valueToCompareAgainst) -> valueToTest <= valueToCompareAgainst;

    IntIntPredicate isEqualTo = (valueToTest, valueToCompareAgainst) -> valueToTest == valueToCompareAgainst;
}
