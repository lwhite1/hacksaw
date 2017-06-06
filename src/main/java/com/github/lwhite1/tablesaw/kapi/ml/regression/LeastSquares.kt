package com.github.lwhite1.tablesaw.kapi.ml.regression

import com.github.lwhite1.tablesaw.api.DoubleColumn
import com.github.lwhite1.tablesaw.api.ml.regression.LeastSquares
import com.github.lwhite1.tablesaw.kapi.NumberCol

/**
 * Performs ordinary least squares regression
 */
class LeastSqModel(val target: LeastSquares) {

    companion object Factory {

        fun train(
                responseVariable: NumberCol,
                vararg explanatoryVariables: NumberCol): LeastSqModel {

            val expVars = explanatoryVariables.map { x -> x.target() }.toTypedArray()
            return LeastSqModel(LeastSquares(responseVariable.target, *expVars))
        }

        // Todo: constructor with table so we can pass columns using name only
/*
        fun train(dataframe:  Dataframe,
                responseVariable: String,
                vararg explanatoryVariables: String): LeastSqModel {

            val xColumn = dataframe.target.column(responseVariable)

            val expVars = explanatoryVariables.map { x -> x.target() }.toTypedArray()
            return LeastSqModel(LeastSquares(xColumn, *expVars))
        }
*/
    }

    override fun toString(): String = target.toString()

    fun residuals(): NumberCol = NumberCol(DoubleColumn.create("residuals", target.residuals()))

    fun fitted(): NumberCol = NumberCol(DoubleColumn.create("fitted", target.fitted()))

    fun adjustedRSquared(): Double = target.adjustedRSquared()

    fun df(): Double = target.df()

    fun error(): Double = target.error()

    fun ftest(): Double = target.ftest()

    fun pValue(): Double = target.pValue()

    fun intercept(): Double = target.intercept()

    fun RSquared(): Double = target.RSquared()

    fun RSS(): Double = target.RSS()

    fun ttest(): Array<DoubleArray> = target.ttest()

    fun predict(x: DoubleArray): Double = target.predict(x)

    fun coefficients(): NumberCol =  NumberCol(DoubleColumn.create("coefficients", target.coefficients()))

    fun actuals(): NumberCol =  NumberCol(DoubleColumn.create("actuals", target.actuals()))
}