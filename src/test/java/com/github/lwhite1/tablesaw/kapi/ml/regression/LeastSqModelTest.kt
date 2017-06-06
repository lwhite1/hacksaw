package com.github.lwhite1.tablesaw.kapi.ml.regression

import com.github.lwhite1.tablesaw.api.plot.Histogram
import com.github.lwhite1.tablesaw.api.plot.Scatter
import com.github.lwhite1.tablesaw.kapi.Dataframe
import org.junit.Test

import org.junit.Assert.*
import java.util.*

/**
 *
 */
class LeastSqModelTest {

    var bball = Dataframe.createFromCsv("data/baseball.csv")
    var model = LeastSqModel.train(bball.nCol("W"), bball.nCol("BA"))

    @Test
    fun toStringTest() {
    }

    @Test
    fun residuals() {
        assertEquals(model.target.residuals(), model.residuals())
    }

    @Test
    fun fitted() {
        System.out.println(model.fitted().summary().show())
    }

    @Test
    fun adjustedRSquared() {
        println(model.adjustedRSquared())
    }

    @Test
    fun df() {
    }

    @Test
    fun error() {
    }

    @Test
    fun ftest() {
    }

    @Test
    fun pValue() {
    }

    @Test
    fun intercept() {
    }

    @Test
    fun RSquared() {
    }

    @Test
    fun RSS() {
    }

    @Test
    fun ttest() {
    }

    @Test
    fun predict() {
    }

    @Test
    fun coefficients() {
        println(model.coefficients().show())
    }

    @Test
    fun actuals() {
        println(model.actuals().summary().show())
        Scatter.actualVsFitted(model.target)
        System.out.println("");
    }

    @Test
    fun getTarget() {
    }

}