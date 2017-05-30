package com.github.lwhite1.tablesaw.kapi

import com.github.lwhite1.tablesaw.api.DateColumn
import com.github.lwhite1.tablesaw.api.TimeColumn
import com.github.lwhite1.tablesaw.columns.Column
import com.github.lwhite1.tablesaw.util.Selection
import java.time.LocalDate
import java.time.LocalTime
import java.time.temporal.ChronoUnit
import java.time.temporal.TemporalUnit

/**
 *
 */
class DateCol(val target: DateColumn) : Col {

    override fun target(): DateColumn = target

    override fun unique(): Col = DateCol(target.unique())

    override fun copy(): Col = DateCol(target.copy())

    override fun emptyCopy(rowSize: Int): Col = DateCol(target.emptyCopy(rowSize))

    override fun sortAscending() = target.sortAscending()

    override fun sortDescending() = target.sortDescending()

    fun append(value: Int) = target.append(value)
    override fun appendCell(stringValue: String) = target.appendCell(stringValue)
    //fun append(c: Column) = target.append(c)

    fun isAfter(value: Int): Selection = target.isAfter(value)
    fun isAfter(value: LocalDate): Selection = target.isAfter(value)
    fun isOnOrAfter(value: LocalDate): Selection = target.isOnOrAfter(value)
    fun isOnOrAfter(value: Int): Selection = target.isOnOrAfter(value)

    fun isBefore(value: Int): Selection = target.isBefore(value)
    fun isBefore(value: LocalDate): Selection = target.isBefore(value)
    fun isOnOrBefore(value: LocalDate): Selection = target.isOnOrBefore(value)
    fun isOnOrBefore(value: Int): Selection = target.isOnOrBefore(value)


    fun isMonday(): Selection = target.isMonday
    fun isTuesday(): Selection = target.isTuesday
    fun isWednesday(): Selection = target.isWednesday
    fun isThursday(): Selection = target.isThursday
    fun isFriday(): Selection = target.isFriday
    fun isSaturday(): Selection = target.isSaturday
    fun isSunday(): Selection = target.isSunday

    fun isInJanuary(): Selection = target.isInJanuary
    fun isInFebruary(): Selection = target.isInFebruary
    fun isInMarch(): Selection = target.isInMarch
    fun isInApril(): Selection = target.isInApril
    fun isInMay(): Selection = target.isInMay
    fun isInJune(): Selection = target.isInJune
    fun isInJuly(): Selection = target.isInJuly
    fun isInAugust(): Selection = target.isInAugust
    fun isInSeptember(): Selection = target.isInSeptember
    fun isInOctober(): Selection = target.isInOctober
    fun isInNovember(): Selection = target.isInNovember
    fun isInDecember(): Selection = target.isInDecember

    fun isFirstDayOfMonth(): Selection = target.isFirstDayOfMonth
    fun isLastDayOfMonth(): Selection = target.isLastDayOfMonth

    fun isInQ1(): Selection = target.isInQ1
    fun isInQ2(): Selection = target.isInQ2
    fun isInQ3(): Selection = target.isInQ3
    fun isInQ4(): Selection = target.isInQ4

    fun isInYear(year: Int): Selection = target.isInYear(year)

    fun max(): LocalDate? = target.max()
    fun min(): LocalDate? = target.min()

    //  operator fun contains(localDate: LocalDate): Boolean = target.contains(localDate)


    // Mapping utilities

    fun dateColumnName(column1: Column<*>, value: Int, unit: TemporalUnit): String {
        return column1.name() + ": " + value + " " + unit.toString() + "(s)"
    }

    fun differenceInDays(column2: DateCol): NumberCol = NumberCol(target.differenceInDays(column2.target))

    fun differenceInWeeks(column2: DateCol): NumberCol = NumberCol(target.differenceInWeeks(column2.target))

    fun differenceInMonths(column2: DateCol): NumberCol = NumberCol(target.differenceInMonths(column2.target))

    fun differenceInYears(column2: DateCol): NumberCol = NumberCol(target.differenceInYears(column2.target))

    /**
     * Calculates the temporal difference between each element of the receiver and the respective element of the
     * argument
     *
     *
     * Missing values in either result in a Missing Value for the new column
     */
    fun difference(column1: DateCol, column2: DateCol, unit: ChronoUnit): NumberCol
            = NumberCol(target.difference(column1.target, column2.target, unit))

    // These functions fill some amount of time to a date, producing a new date column
    fun plusDays(days: Int): DateCol = plus(days, ChronoUnit.DAYS)

    fun plusWeeks(weeks: Int): DateCol = plus(weeks, ChronoUnit.WEEKS)

    fun plusYears(years: Int): DateCol = plus(years, ChronoUnit.YEARS)

    fun plusMonths(months: Int): DateCol = plus(months, ChronoUnit.MONTHS)

    // These functions subtract some amount of time from a date, producing a new date column

    fun minusDays(days: Int): DateCol = plusDays(-days)

    fun minusWeeks(weeks: Int): DateCol = plusWeeks(-weeks)

    fun minusYears(years: Int): DateCol = plusYears(-years)

    fun minusMonths(months: Int): DateCol = plusMonths(-months)

    fun plus(value: Int, unit: TemporalUnit): DateCol = DateCol(target.plus(value, unit))

    fun minus(value: Int, unit: TemporalUnit): DateCol = DateCol(target.minus(value, unit))

    fun atStartOfDay(): DateTimeCol = DateTimeCol(target.atStartOfDay())

    /**
     * Returns a DateTime column where each value consists of the dates from this column combined with the corresponding
     * times from the other column
     */
    fun atTime(time: LocalTime): DateTimeCol = DateTimeCol(target.atTime(time))

    /**
     * Returns a DateTime column where each value consists of the dates from this column combined with the corresponding
     * times from the other column
     */
    fun atTime(timeColumn: TimeColumn): DateTimeCol = DateTimeCol(target.atTime(timeColumn))

    fun getInt(row: Int): Int = target.getInt(row)

    operator fun get(index: Int): LocalDate? = target.get(index)

    fun dayOfWeek(): CategoryCol = CategoryCol(target.dayOfWeek())

    fun dayOfWeekValue(): NumberCol = NumberCol(target.dayOfWeekValue())

    fun dayOfMonth(): NumberCol = NumberCol(target.dayOfMonth())

    fun dayOfYear(): NumberCol = NumberCol(target.dayOfYear())

    fun monthValue(): NumberCol = NumberCol(target.monthValue())

    fun month(): CategoryCol = CategoryCol(target.month())

    fun year(): NumberCol = NumberCol(target.year())


}