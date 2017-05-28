package com.github.lwhite1.tablesaw.kapi

import com.github.lwhite1.tablesaw.reducing.NumericReduceFunction
import com.github.lwhite1.tablesaw.reducing.NumericReduceUtils
import org.junit.Test

import org.junit.Assert.*

/**
 * Created by larrywhite on 5/28/17.
 */
class DataframeTest {
    @Test
    fun name() {
    }

    @Test
    fun toStringTest() {
    }

    @Test
    fun print() {
    }

    @Test
    fun printHtml() {
    }

    @Test
    fun summary() {
    }

    @Test
    fun shape() {
    }

    @Test
    fun structure() {
    }

    @Test
    fun emptyCopy() {
    }

    @Test
    fun emptyCopy1() {
    }

    @Test
    fun first() {
    }

    @Test
    fun last() {
    }

    @Test
    fun rowCount() {
    }

    @Test
    fun columnCount() {
    }

    @Test
    fun columnIndex() {
    }

    @Test
    fun append() {
    }

    @Test
    fun get() {
    }

    @Test
    fun columnNames() {
    }

    @Test
    fun selectWhere() {
    }

    @Test
    fun setName() {
    }

    @Test
    fun removeColumns() {
    }

    @Test
    fun exportToCsv() {
    }

    @Test
    fun save() {
    }

    @Test
    fun summarize() {
        val frame = Dataframe.createFromCsv("data/BushApproval.csv")
        val result = frame.summarize("approval", NumericReduceUtils.mean).by("who")
        val result1 = frame.sum("approval").by("who")
        val result2 = frame.max("approval").by("who")
        val result3 = frame.median("approval").by("who")
        println(result.print())
        println(result1.print())
        println(result2.print())
        println(result3.print())
    }

    @Test
    fun sum() {
    }

    @Test
    fun mean() {
    }

    @Test
    fun median() {
    }

    @Test
    fun variance() {
    }

    @Test
    fun stdDev() {
    }

    @Test
    fun count() {
    }

    @Test
    fun max() {
    }

    @Test
    fun minimum() {
    }

    @Test
    fun nCol() {
    }

    @Test
    fun getTarget() {
    }

}