package com.github.lwhite1.tablesaw.kapi.ml.features

import com.github.lwhite1.tablesaw.api.DoubleColumn
import com.github.lwhite1.tablesaw.api.ml.features.PrincipalComponents
import com.github.lwhite1.tablesaw.kapi.NumberCol
import smile.projection.PCA

/**
 *
 */
class PrincipalComponentsModel(val target: PrincipalComponents) {

    companion object Factory {

        fun train(
                useCorrelationMatrix: Boolean,
                vararg explanatoryVariables: NumberCol): PrincipalComponentsModel {

            val expVars: Array<DoubleColumn> = explanatoryVariables.map { x -> x.target() }.toTypedArray()

            return PrincipalComponentsModel(PrincipalComponents.create(useCorrelationMatrix, expVars))
        }
    }

    fun getCenter(): DoubleArray = target.center

    fun getCumulativeVarianceProportion(): DoubleArray = target.cumulativeVarianceProportion

    fun getVarianceProportion(): DoubleArray = target.varianceProportion

    fun getVariance(): DoubleArray = target.variance

    fun project(x: DoubleArray): DoubleArray = target.project(x)

    fun project(x: Array<DoubleArray>): Array<DoubleArray> = target.project(x)

    fun getLoadings(): Array<DoubleArray> = target.loadings

    fun getProjection(): Array<DoubleArray> = target.projection

    fun setProjection(p: Int): PCA = target.setProjection(p)

    fun setProjection(p: Double): PCA = target.setProjection(p)


}
