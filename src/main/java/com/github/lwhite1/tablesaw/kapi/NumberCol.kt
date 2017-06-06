package com.github.lwhite1.tablesaw.kapi

import com.github.lwhite1.tablesaw.api.DoubleColumn
import com.github.lwhite1.tablesaw.util.Selection

/**
 *
 */
class NumberCol(val target: DoubleColumn) : Col {

    override fun target(): DoubleColumn = target


    override fun toDoubleArray(): DoubleArray = target.toDoubleArray()

    fun getFloat(index: Int): Float = target.getFloat(index)

    override fun unique(): Col = NumberCol(target.unique())

    override fun copy(): Col = NumberCol(target.copy())

    override fun emptyCopy(rowSize: Int): Col = NumberCol(target.emptyCopy())

    override fun sortAscending() = target.sortAscending()

    override fun sortDescending() = target.sortDescending()

    operator fun plus(c : NumberCol): NumberCol = NumberCol(target.add(c.target));

//    operator fun plus(value : Double): DoubleColumn = target.addToEach(value);

    operator fun minus(c : NumberCol): NumberCol = NumberCol(target.subtract(c.target));

   // operator fun minus(value : Int): NumberCol = target.addToEach(-value);

    operator fun div(c: NumberCol): NumberCol = NumberCol(target.divide(c.target))

    operator fun times(c: NumberCol): NumberCol = NumberCol(target.multiply(c.target))

    // math functions
    fun cube(): NumberCol = NumberCol(target.cube())
    fun cubeRoot(): NumberCol = NumberCol(target.cubeRoot())

    fun square(): NumberCol = NumberCol(target.square())
    fun sqrt(): NumberCol = NumberCol(target.sqrt())

    fun abs(): NumberCol = NumberCol(target.abs())
    fun round(): NumberCol = NumberCol(target.round())

    fun log1p(): NumberCol = NumberCol(target.log1p())
    fun log10(): NumberCol = NumberCol(target.log10())
    fun logN(): NumberCol = NumberCol(target.logN())

    // summary statistics
    fun sum(): Double = target.sum()
    fun product(): Double = target.product()
    fun sumOfLogs(): Double = target.sumOfLogs()
    fun sumOfSquares(): Double = target.sumOfSquares()

    fun mean(): Double = target.mean()
    fun geometricMean(): Double = target.geometricMean()
    fun quadraticMean(): Double = target.quadraticMean()
    fun median(): Double = target.median()
    fun quartile1(): Double = target.quartile1()
    fun quartile3(): Double = target.quartile3()
    fun percentile(percentile : Double): Double = target.percentile(percentile)

    fun max(): Double = target.max()
    fun min(): Double = target.min()

    fun range(): Double = target.range()
    fun variance(): Double = target.variance()
    fun populationVariance(): Double = target.populationVariance()
    fun standardDeviation(): Double = target.standardDeviation()

    fun skewness(): Double = target.skewness()
    fun kurtosis(): Double = target.kurtosis()

    // comparisons

    infix fun isGreaterThan(value: Double): Selection = target.isGreaterThan(value)
    infix fun isGreaterThan(value: Int): Selection = target.isGreaterThan(value)

    infix fun isGreaterThanOrEqualTo(value: Double): Selection = target.isGreaterThanOrEqualTo(value)
    infix fun isGreaterThanOrEqualTo(value: Int): Selection = target.isGreaterThanOrEqualTo(value)

    infix fun isLessThanOrEqualTo(value: Double): Selection = target.isLessThanOrEqualTo(value)
    infix fun isLessThanOrEqualTo(value: Int): Selection = target.isLessThanOrEqualTo(value)

    infix fun isLessThan(value: Double): Selection = target.isLessThan(value)
    infix fun isLessThan(value: Int): Selection = target.isLessThan(value)

    infix fun isEqualTo(value: Double): Selection = target.isEqualTo(value)
    infix fun isEqualTo(value: Int): Selection = target.isEqualTo(value)

    // other boolean expressions
    fun isZero(): Selection = target.isZero
    fun isMissing(): Selection = target.isMissing
    fun isNotMissing(): Selection = target.isNotMissing
    fun isNegative(): Selection = target.isNegative
    fun isPositive(): Selection = target.isPositive
    fun isNonNegative(): Selection = target.isNonNegative

}