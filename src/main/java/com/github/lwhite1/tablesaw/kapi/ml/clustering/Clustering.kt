package com.github.lwhite1.tablesaw.kapi.ml.clustering

import com.github.lwhite1.tablesaw.api.ml.clustering.Gmeans
import com.github.lwhite1.tablesaw.kapi.Dataframe
import com.github.lwhite1.tablesaw.kapi.NumberCol

/**
 * Provides models for K-means, G-Means and X-Means clustering
 */

class GMeansModel(val target: Gmeans) {

    companion object Factory {

        fun train(
                maxK: Int,
                vararg columns: NumberCol): GMeansModel {

            val cols = columns.map { x -> x.target() }.toTypedArray()
            return GMeansModel(Gmeans(maxK, *cols))
        }
    }

    fun predict(x: DoubleArray): Int = target.predict(x)

    fun centroids(): Array<DoubleArray> = target.centroids()

    fun distortion(): Double = target.distortion()

    fun getClusterCount(): Int = target.getClusterCount()

    fun getClusterLabels(): IntArray = target.getClusterLabels()

    fun getClusterSizes(): IntArray = target.getClusterSizes()

    fun labeledCentroids(): Dataframe = Dataframe(target.labeledCentroids())
}