package com.github.lwhite1.tablesaw.columns;

import com.github.lwhite1.tablesaw.filtering.predicates.LongLongPredicate;
import it.unimi.dsi.fastutil.longs.LongIterable;

/**
 * Pre-made predicates for common integer use cases, and other helpful things
 */
public interface LongColumnUtils extends Column, LongIterable {

    LongLongPredicate isGreaterThan = (valueToTest, valueToCompareAgainst) -> valueToTest > valueToCompareAgainst;

    LongLongPredicate isGreaterThanOrEqualTo = (valueToTest, valueToCompareAgainst) -> valueToTest >=
            valueToCompareAgainst;

    LongLongPredicate isLessThan = (valueToTest, valueToCompareAgainst) -> valueToTest < valueToCompareAgainst;

    LongLongPredicate isLessThanOrEqualTo = (valueToTest, valueToCompareAgainst) -> valueToTest <= valueToCompareAgainst;

    LongLongPredicate isEqualTo = (long valueToTest, long valueToCompareAgainst) -> valueToTest == valueToCompareAgainst;
}
