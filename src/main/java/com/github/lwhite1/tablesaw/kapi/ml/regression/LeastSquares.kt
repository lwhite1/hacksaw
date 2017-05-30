package com.github.lwhite1.tablesaw.kapi.ml.regression

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
    }

    override fun toString(): String = target.toString()

    fun residuals(): DoubleArray = target.residuals()

    fun fitted(): DoubleArray = target.fitted()

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

    fun coefficients(): DoubleArray = target.coefficients()

    fun actuals(): DoubleArray = target.actuals()
}