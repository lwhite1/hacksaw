package com.github.lwhite1.tablesaw.kapi

import com.github.lwhite1.tablesaw.api.ColumnType
import com.github.lwhite1.tablesaw.api.DoubleColumn
import com.github.lwhite1.tablesaw.store.ColumnMetadata
import com.github.lwhite1.tablesaw.util.Selection

/**
 *
 */
class NumberCol(val target: DoubleColumn) : Col {

    fun target(): DoubleColumn = target

    override fun toDoubleArray(): DoubleArray = target.toDoubleArray()

    fun getFloat(index: Int): Float = target.getFloat(index)

    override fun size(): Int = target.size()

    override fun summary(): Dataframe = Dataframe(target.summary())

    override fun countMissing(): Int = target.countMissing()

    override fun countUnique(): Int = target.countUnique()

    override fun unique(): Col = NumberCol(target.unique())

    override fun type(): ColumnType = target.type()

    override fun name(): String = target.name()

    override fun getString(row: Int): String = target.getString(row)

    override fun copy(): Col = NumberCol(target.copy())

    override fun emptyCopy(rowSize: Int): Col = NumberCol(target.emptyCopy())

    override fun clear() = target.clear()

    override fun sortAscending() = target.sortAscending()

    override fun sortDescending() = target.sortDescending()

    override fun isEmpty(): Boolean = target.isEmpty

    override fun appendCell(stringValue: String) = target.appendCell(stringValue)

    override fun id(): String = target.id()

    override fun metadataString(): String = target.metadata()

    override fun columnMetadata(): ColumnMetadata = target.columnMetadata()

    override fun print(): String = target.print()


/*
    operator fun plus(c : DoubleCol): DoubleColumn = target.add(c.target);

    operator fun plus(value : Double): DoubleColumn = target.addToEach(value);
*/



/*
    operator fun minus(c : DoubleCol): DoubleColumn = target.subtract(c.target);

    operator fun minus(value : Int): IntColumn = target.addToEach(-value);
*/

    operator fun div(c: NumberCol): DoubleColumn = target.divide(c.target)

    operator fun times(c: NumberCol): DoubleColumn = target.multiply(c.target)

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
    fun isGreaterThanOrEqualTo(value: Double): Selection = target.isGreaterThanOrEqualTo(value)
    fun isLessThanOrEqualTo(value: Double): Selection = target.isLessThanOrEqualTo(value)
    fun isLessThan(value: Double): Selection = target.isLessThan(value)
    fun isEqualTo(value: Double): Selection = target.isEqualTo(value)

    // other boolean expressions
    fun isZero(): Selection = target.isZero
    fun isMissing(): Selection = target.isMissing
    fun isNotMissing(): Selection = target.isNotMissing
    fun isNegative(): Selection = target.isNegative
    fun isPositive(): Selection = target.isPositive
    fun isNonNegative(): Selection = target.isNonNegative

}