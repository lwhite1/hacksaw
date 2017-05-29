package com.github.lwhite1.tablesaw.kapi

import com.github.lwhite1.tablesaw.api.Table
import com.github.lwhite1.tablesaw.api.plot.Bar
import com.github.lwhite1.tablesaw.api.plot.Pareto
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
        val table = Table.createFromCsv("data/tornadoes_1950-2014.csv")
        //Table t2 = table.countBy(table.categoryColumn("State"));
        //show("tornadoes by state", t2.categoryColumn("Category"), t2.numericColumn("Count"));

        //show("T", table.summarize("fatalities", sum).by("State"));
        Bar.show("T", table.summarize("fatalities", NumericReduceUtils.sum).by("Scale"))
    }

    @Test
    fun setName() {
        val frame = Dataframe.createFromCsv("data/BushApproval.csv")
        frame.setName("Duh")
        assertEquals("Duh", frame.name())
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
        val result1 = frame.summarize("approval", NumericReduceUtils.sum).by("who")
        val result2 = frame.summarize("approval", NumericReduceUtils.max).by("who")
        val result3 = frame.summarize("approval", NumericReduceUtils.median).by("who")
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