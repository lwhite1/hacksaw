package com.github.lwhite1.tablesaw.filtering.predicates;

import com.github.lwhite1.tablesaw.columns.packeddata.PackedLocalDateTime;

/**
 *
 */
public interface LongLongPredicate {

    LongLongPredicate isInYear = (packedDateTime, year) -> PackedLocalDateTime.isInYear(packedDateTime, (int) year);
    LongLongPredicate isBefore = PackedLocalDateTime::isBefore;

    /**
     * Returns true if valueToTest meets the criteria of this predicate when valueToCompareAgainst is considered
     * <p>
     * Example (to compare all the values v in a column such that v is greater than 4, v is the value to test and 4 is
     * the value to compare against
     *
     * @param valueToTest           the value you're checking. Often this is the value of a cell in an int column
     * @param valueToCompareAgainst the value to compare against. Often this is a single value for all comparisons
     */
    boolean test(long valueToTest, long valueToCompareAgainst);
}
