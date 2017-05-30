package com.github.lwhite1.tablesaw.kapi

import com.github.lwhite1.tablesaw.api.ColumnType.DOUBLE
import com.github.lwhite1.tablesaw.api.DoubleColumn
import org.junit.Before
import org.junit.Test

import org.junit.Assert.assertEquals
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 */
class NumberColTest {

    val doubleColumn = DoubleColumn("Testing")
    var column1 = NumberCol(doubleColumn);

    @Before
    fun setUp() {
        doubleColumn.append(1.1)
        doubleColumn.append(2.1)
        doubleColumn.append(3.1)
        doubleColumn.append(4.1)
        doubleColumn.append(5.1)
        doubleColumn.append(6.1)
        doubleColumn.append(4.4)
        doubleColumn.append(3.3)
    }

    @Test
    fun target() {
        assertEquals(doubleColumn, column1.target)
    }

    @Test
    fun toDoubleArray() {
    }

    @Test
    fun getFloat() {
        assertEquals(4.1f, column1.getFloat(3))
    }

    @Test
    fun size() {
        assertEquals(8, column1.size())
    }

    @Test
    fun summary() {
    }

    @Test
    fun countMissing() {
    }

    @Test
    fun countUnique() {
    }

    @Test
    fun unique() {
    }

    @Test
    fun type() {
        assertEquals(DOUBLE, column1.type())
    }

    @Test
    fun name() {
        assertEquals("Testing", column1.name())
    }

    @Test
    fun getString() {
    }

    @Test
    fun copy() {
    }

    @Test
    fun emptyCopy() {
        assertTrue(column1.emptyCopy().isEmpty())
    }

    @Test
    fun clear() {
        assertFalse(column1.isEmpty())
        column1.clear()
        assertTrue(column1.isEmpty())
    }

    @Test
    fun sortAscending() {
        column1.sortAscending()  //TODO fix
        println(column1.show())
    }

    @Test
    fun sortDescending() {
        column1.sortDescending()
        println(column1.show())
    }

    @Test
    fun isEmpty() {
        assertFalse(column1.isEmpty())
    }

    @Test
    fun appendCell() {
    }

    @Test
    fun id() {
    }

    @Test
    fun metadataString() {
    }

    @Test
    fun columnMetadata() {
    }

    @Test
    fun print() {
    }

    @Test
    fun div() {
    }

    @Test
    fun times() {
    }

    @Test
    fun cube() {
        val column2 = column1.cube()
        println(column2.show())
    }

    @Test
    fun cubeRoot() {
        val column2 = column1.cubeRoot()
        println(column2.show())
    }

    @Test
    fun square() {
        val column2 = column1.square()
        println(column2.show())
    }

    @Test
    fun sqrt() {
        val column2 = column1.sqrt()
        println(column2.show())
    }

    @Test
    fun abs() {
        val column2 = column1.abs()
        println(column2.show())
    }

    @Test
    fun round() {
        val column2 = column1.round()
        println(column2.show())
    }

    @Test
    fun log1p() {
    }

    @Test
    fun log10() {
    }

    @Test
    fun logN() {
    }

    @Test
    fun sum() {
        assertEquals(29.299999237060547, column1.sum(), 0.01)
    }

    @Test
    fun product() {
        println(column1.product())
    }

    @Test
    fun sumOfLogs() {
        println(column1.sumOfLogs())
    }

    @Test
    fun sumOfSquares() {
        assertEquals(125.50999999999999, column1.sumOfSquares(), "0.0001")
    }

    @Test
    fun mean() {
        assertEquals(3.6625, column1.mean(), "0.0001")
    }

    @Test
    fun geometricMean() {
    }

    @Test
    fun quadraticMean() {
    }

    @Test
    fun median() {
        assertEquals(3.6999999999999997, column1.median(), "0.0001")
    }

    @Test
    fun quartile1() {
        assertEquals(2.35, column1.quartile1(), "0.0001")
    }

    @Test
    fun quartile3() {
        assertEquals(4.925, column1.quartile3(), "0.0001")
    }

    @Test
    fun percentile() {
    }

    @Test
    fun max() {
        assertEquals(6.1, column1.max(), 0.001)
    }

    @Test
    fun min() {
        assertEquals(1.1, column1.min(), 0.001)
    }

    @Test
    fun range() {
        assertEquals(5.0, column1.range(), 0.001)
    }

    @Test
    fun variance() {
    }

    @Test
    fun populationVariance() {
    }

    @Test
    fun standardDeviation() {
    }

    @Test
    fun skewness() {
    }

    @Test
    fun kurtosis() {
    }

    @Test
    fun isGreaterThan() {
        assertEquals(6, column1.isGreaterThan(2.43).size())
    }

    @Test
    fun isGreaterThanOrEqualTo() {
    }

    @Test
    fun isLessThanOrEqualTo() {
    }

    @Test
    fun isLessThan() {
    }

    @Test
    fun isEqualTo() {
    }

    @Test
    fun isZero() {
        assertEquals(0, column1.isZero().size())
    }

    @Test
    fun isMissing() {
        assertEquals(0, column1.isMissing().size())
    }

    @Test
    fun isNotMissing() {
        assertEquals(8, column1.isNotMissing().size())
    }

    @Test
    fun isNegative() {
        assertEquals(0, column1.isNegative().size())
    }

    @Test
    fun isPositive() {
        assertEquals(8, column1.isPositive().size())
    }

    @Test
    fun isNonNegative() {
        assertEquals(8, column1.isNonNegative().size())
    }

    @Test
    fun getTarget() {
    }

}