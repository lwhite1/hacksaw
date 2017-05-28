package com.github.lwhite1.tablesaw.filtering;

import com.github.lwhite1.tablesaw.columns.packeddata.PackedLocalDate;

/**
 *
 */
public interface IntBiPredicate {

    IntBiPredicate isInYear = PackedLocalDate::isInYear;

    /**
     * Returns true if valueToTest meets the criteria of this predicate when valueToCompareAgainst is considered
     * <p>
     * Example (to compare all the values v in a column such that v is greater than 4, v is the value to test and 4 is
     * the value to compare against
     *
     * @param valueToTest           the value you're checking. Often this is the value of a cell in an int column
     * @param valueToCompareAgainst the value to compare against. Often this is a single value for all comparisions
     */
    boolean test(int valueToTest, int valueToCompareAgainst);
}
