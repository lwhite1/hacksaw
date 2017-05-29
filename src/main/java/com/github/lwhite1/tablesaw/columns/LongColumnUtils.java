package com.github.lwhite1.tablesaw.columns;

import com.github.lwhite1.tablesaw.filtering.LongBiPredicate;
import com.github.lwhite1.tablesaw.filtering.LongPredicate;
import it.unimi.dsi.fastutil.longs.LongIterable;

/**
 * Pre-made predicates for common integer use cases, and other helpful things
 */
public interface LongColumnUtils extends Column, LongIterable {

    LongBiPredicate isGreaterThan = (valueToTest, valueToCompareAgainst) -> valueToTest > valueToCompareAgainst;

    LongBiPredicate isGreaterThanOrEqualTo = (valueToTest, valueToCompareAgainst) -> valueToTest >=
            valueToCompareAgainst;

    LongBiPredicate isLessThan = (valueToTest, valueToCompareAgainst) -> valueToTest < valueToCompareAgainst;

    LongBiPredicate isLessThanOrEqualTo = (valueToTest, valueToCompareAgainst) -> valueToTest <= valueToCompareAgainst;

    LongBiPredicate isEqualTo = (long valueToTest, long valueToCompareAgainst) -> valueToTest == valueToCompareAgainst;
}
