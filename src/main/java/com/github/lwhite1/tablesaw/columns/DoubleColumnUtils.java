package com.github.lwhite1.tablesaw.columns;

import com.github.lwhite1.tablesaw.filtering.predicates.DoubleDoublePredicate;
import com.github.lwhite1.tablesaw.filtering.predicates.DoublePredicate;
import it.unimi.dsi.fastutil.ints.IntIterable;

/**
 *
 */
public interface DoubleColumnUtils extends Column, IntIterable {

    DoublePredicate isZero = i -> i == 0.0f;

    DoublePredicate isNegative = i -> i < 0f;

    DoublePredicate isPositive = i -> i > 0f;

    DoublePredicate isNonNegative = i -> i >= 0f;

    DoubleDoublePredicate isGreaterThan = (valueToTest, valueToCompareAgainst) -> valueToTest > valueToCompareAgainst;

    DoubleDoublePredicate isGreaterThanOrEqualTo = (valueToTest, valueToCompareAgainst) -> valueToTest >=
            valueToCompareAgainst;

    DoubleDoublePredicate isLessThan = (valueToTest, valueToCompareAgainst) -> valueToTest < valueToCompareAgainst;

    DoubleDoublePredicate isLessThanOrEqualTo = (valueToTest, valueToCompareAgainst) -> valueToTest <=
            valueToCompareAgainst;

    DoubleDoublePredicate isEqualTo = (valueToTest, valueToCompareAgainst) -> valueToTest == valueToCompareAgainst;

    DoublePredicate isMissing = i -> i != i;

    DoublePredicate isNotMissing = i -> i == i;
}
