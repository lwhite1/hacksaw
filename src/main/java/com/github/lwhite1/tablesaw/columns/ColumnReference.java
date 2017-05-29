package com.github.lwhite1.tablesaw.columns;

import com.github.lwhite1.tablesaw.api.DoubleColumn;
import com.github.lwhite1.tablesaw.columns.packeddata.PackedLocalDate;
import com.github.lwhite1.tablesaw.columns.packeddata.PackedLocalDateTime;
import com.github.lwhite1.tablesaw.columns.packeddata.PackedLocalTime;
import com.github.lwhite1.tablesaw.filtering.BooleanIsFalse;
import com.github.lwhite1.tablesaw.filtering.BooleanIsTrue;
import com.github.lwhite1.tablesaw.filtering.DateBiFilter;
import com.github.lwhite1.tablesaw.filtering.DateEqualTo;
import com.github.lwhite1.tablesaw.filtering.DateFilter;
import com.github.lwhite1.tablesaw.filtering.DoubleBiFilter;
import com.github.lwhite1.tablesaw.filtering.Filter;
import com.github.lwhite1.tablesaw.filtering.DoubleBetween;
import com.github.lwhite1.tablesaw.filtering.DoubleIsIn;
import com.github.lwhite1.tablesaw.filtering.IsMissing;
import com.github.lwhite1.tablesaw.filtering.IsNotMissing;
import com.github.lwhite1.tablesaw.filtering.LocalDateBetween;
import com.github.lwhite1.tablesaw.filtering.LongBiPredicate;
import com.github.lwhite1.tablesaw.filtering.StringBiFilter;
import com.github.lwhite1.tablesaw.filtering.StringEqualTo;
import com.github.lwhite1.tablesaw.filtering.StringFilter;
import com.github.lwhite1.tablesaw.filtering.StringNotEqualTo;
import com.github.lwhite1.tablesaw.filtering.TimeEqualTo;
import com.github.lwhite1.tablesaw.filtering.TimeFilter;
import com.github.lwhite1.tablesaw.filtering.dates.LocalDateIsAfter;
import com.github.lwhite1.tablesaw.filtering.text.TextHasLengthEqualTo;
import com.github.lwhite1.tablesaw.filtering.text.TextIsIn;
import com.github.lwhite1.tablesaw.filtering.text.TextIsLongerThan;
import com.github.lwhite1.tablesaw.filtering.text.TextIsShorterThan;
import com.github.lwhite1.tablesaw.filtering.text.TextMatchesRegex;
import com.github.lwhite1.tablesaw.filtering.times.IsAfter;
import com.github.lwhite1.tablesaw.filtering.times.IsBefore;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * A reference to a column that can be used in evaluating query predicates. It is a key part of having a fluent API
 * for querying tables.
 * <p>
 * Basically, it lets you write a query like this:
 * <p>
 * table.selectWhere(column("foo").isEqualTo("Bar"));
 * <p>
 * In that example, column() is a static method that returns a ColumnReference for a column named "foo".
 * The method isEqualTo(), is implemented on ColumnReference in a way that it can be applied to potentially, multiple
 * column types, although in this case, it only makes sense for CategoryColumns since the argument is a string.
 * <p>
 * When selectWhere() isExecuted, it supplies the table to the ColumnReference. The ColumnReference uses the table
 * and columnName to get access to the right column, and then fulfils its role by ensuring that the filtering
 * "isEqualTo("Bar") is applied to all the cells in the column.
 */
public class ColumnReference {

    private String columnName;

    public ColumnReference(String column) {
        this.columnName = column;
    }

    public Filter isNotMissing() {
        return new IsNotMissing(this);
    }

    public Filter isMissing() {
        return new IsMissing(this);
    }

    public Filter isBetween(int low, int high) {
        return new DoubleBetween(this, low, high);
    }

    public Filter isBetween(LocalDate low, LocalDate high) {
        return new LocalDateBetween(this, low, high);
    }

    public Filter isEqualTo(LocalTime value) {
        return new TimeEqualTo(this, value);
    }

    public Filter isEqualTo(LocalDate value) {
        return new DateEqualTo(this, value);
    }

    public Filter isEqualTo(String value) {
        return new StringEqualTo(this, value);
    }

    public Filter isNotEqualTo(String value) {
        return new StringNotEqualTo(this, value);
    }

    public Filter isIn(DoubleColumn intColumn) {
        return new DoubleIsIn(this, intColumn);
    }

    public Filter isIn(String... strings) {
        return new TextIsIn(this, strings);
    }

    public Filter isIn(double... doubles) {
        return new DoubleIsIn(this, doubles);
    }

    public Filter isGreaterThan(float value) {
        return new DoubleBiFilter(this, DoubleColumnUtils.isGreaterThan, value);
    }

    public Filter isLessThan(float value) {
        return new DoubleBiFilter(this, DoubleColumnUtils.isLessThan, value);
    }

    public Filter isLessThanOrEqualTo(float value) {
        return new DoubleBiFilter(this, DoubleColumnUtils.isLessThanOrEqualTo, value);
    }

    public Filter isGreaterThanOrEqualTo(float value) {
        return new DoubleBiFilter(this, DoubleColumnUtils.isGreaterThanOrEqualTo, value);
    }

    public String getColumnName() {
        return columnName;
    }

    public Filter isMidnight() {
        return new TimeFilter(this, PackedLocalTime::isMidnight, PackedLocalDateTime::isMidnight);
    }

    public Filter isNoon() {
        return new TimeFilter(this, PackedLocalTime::isNoon, PackedLocalDateTime::isNoon);
    }

    public Filter isBeforeNoon() {
        return new TimeFilter(this, PackedLocalTime::AM, PackedLocalDateTime::AM);
    }

    public Filter isAfterNoon() {
        return new TimeFilter(this, PackedLocalTime::PM, PackedLocalDateTime::PM);
    }

    public Filter isBefore(LocalTime value) {
        return new IsBefore(this, value);
    }

    public Filter isBefore(LocalDate value) {
        return new DateBiFilter(this,PackedLocalDate::isBefore, PackedLocalDateTime::isBefore, PackedLocalDate.pack(value));
    }

    public Filter isAfter(LocalTime value) {
        return new IsAfter(this, value);
    }

    public Filter isSunday() {
        return new DateFilter(this, PackedLocalDate::isSunday, PackedLocalDateTime::isSunday);
    }

    public Filter isMonday() {
        return new DateFilter(this, PackedLocalDate::isMonday, PackedLocalDateTime::isMonday);
    }

    public Filter isTuesday() {
        return new DateFilter(this, PackedLocalDate::isTuesday, PackedLocalDateTime::isTuesday);
    }

    public Filter isWednesday() {
        return new DateFilter(this, PackedLocalDate::isWednesday, PackedLocalDateTime::isWednesday);
    }

    public Filter isThursday() {
        return new DateFilter(this, PackedLocalDate::isThursday, PackedLocalDateTime::isThursday);
    }

    public Filter isFriday() {
        return new DateFilter(this, PackedLocalDate::isFriday, PackedLocalDateTime::isFriday);
    }

    public Filter isSaturday() {
        return new DateFilter(this, PackedLocalDate::isSaturday, PackedLocalDateTime::isSaturday);
    }

    public Filter isInJanuary() {
        return new DateFilter(this, PackedLocalDate::isInJanuary, PackedLocalDateTime::isInJanuary);
    }

    public Filter isInFebruary() {
        return new DateFilter(this, PackedLocalDate::isInFebruary, PackedLocalDateTime::isInFebruary);
    }

    public Filter isInMarch() {
        return new DateFilter(this, PackedLocalDate::isInMarch, PackedLocalDateTime::isInMarch);
    }

    public Filter isInApril() {
        return new DateFilter(this, PackedLocalDate::isInApril, PackedLocalDateTime::isInApril);
    }

    public Filter isInMay() {
        return new DateFilter(this, PackedLocalDate::isInMay, PackedLocalDateTime::isInMay);
    }

    public Filter isInJune() {
        return new DateFilter(this, PackedLocalDate::isInJune, PackedLocalDateTime::isInJune);
    }

    public Filter isInJuly() {
        return new DateFilter(this, PackedLocalDate::isInJuly, PackedLocalDateTime::isInJuly);
    }

    public Filter isInAugust() {
        return new DateFilter(this, PackedLocalDate::isInAugust, PackedLocalDateTime::isInAugust);
    }

    public Filter isInSeptember() {
        return new DateFilter(this, PackedLocalDate::isInSeptember, PackedLocalDateTime::isInSeptember);
    }

    public Filter isInOctober() {
        return new DateFilter(this, PackedLocalDate::isInOctober, PackedLocalDateTime::isInOctober);
    }

    public Filter isInNovember() {
        return new DateFilter(this, PackedLocalDate::isInNovember, PackedLocalDateTime::isInNovember);
    }

    public Filter isInDecember() {
        return new DateFilter(this, PackedLocalDate::isInDecember, PackedLocalDateTime::isInDecember);
    }

    public Filter isInQ1() {
        return new DateFilter(this, PackedLocalDate::isInQ1, PackedLocalDateTime::isInQ1);
    }

    public Filter isInQ2() {
        return new DateFilter(this, PackedLocalDate::isInQ2, PackedLocalDateTime::isInQ2);
    }

    public Filter isInQ3() {
        return new DateFilter(this, PackedLocalDate::isInQ3, PackedLocalDateTime::isInQ3);
    }

    public Filter isInQ4() {
        return new DateFilter(this, PackedLocalDate::isInQ4, PackedLocalDateTime::isInQ4);
    }

    public Filter isFirstDayOfMonth() {
        return new DateFilter(this, PackedLocalDate::isFirstDayOfMonth, PackedLocalDateTime::isFirstDayOfMonth);
    }

    public Filter isLastDayOfMonth() {
        return new DateFilter(this, PackedLocalDate::isLastDayOfMonth, PackedLocalDateTime::isLastDayOfMonth);
    }

    public Filter isInYear(int year) {
        return new DateBiFilter(this, PackedLocalDate::isInYear, LongBiPredicate.isInYear, year);
    }

    public Filter isAfter(LocalDate date) {
        return new LocalDateIsAfter(this, PackedLocalDate.pack(date));
    }

    public Filter isUpperCase() {
        return new StringFilter(this, StringUtils::isAllUpperCase);
    }

    public Filter isLowerCase() {
        return new StringFilter(this, StringUtils::isAllLowerCase);
    }

    public Filter isAlpha() {
        return new StringFilter(this, StringUtils::isAlpha);
    }

    public Filter isAlphaNumeric() {
        return new StringFilter(this, StringUtils::isAlphanumeric);
    }

    public Filter isNumeric() {
        return new StringFilter(this, StringUtils::isNumeric);
    }

    public Filter isEmpty() {
        return new StringFilter(this, String::isEmpty);
    }

    public Filter isLongerThan(int length) {
        return new TextIsLongerThan(this, length);
    }

    public Filter isShorterThan(int length) {
        return new TextIsShorterThan(this, length);
    }

    public Filter hasLengthEqualTo(int length) {
        return new TextHasLengthEqualTo(this, length);
    }

    public Filter equalToIgnoringCase(String string) {
        return new StringBiFilter(this, String::equalsIgnoreCase, string);
    }

    public Filter startsWith(String string) {
        return new StringBiFilter(this, String::startsWith, string);
    }

    public Filter endsWith(String string) {
        return new StringBiFilter(this, String::endsWith, string);
    }

    public Filter contains(String string) {
        return new StringBiFilter(this, String::contains, string);
    }

    public Filter matchesRegex(String string) {
        return new TextMatchesRegex(this, string);
    }

    public Filter isTrue() {
        return new BooleanIsTrue(this);
    }

    public Filter isFalse() {
        return new BooleanIsFalse(this);
    }
}
