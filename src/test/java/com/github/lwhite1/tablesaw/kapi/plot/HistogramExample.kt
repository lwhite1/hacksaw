package com.github.lwhite1.tablesaw.kapi.plot

import com.github.lwhite1.tablesaw.api.plot.Histogram
import com.github.lwhite1.tablesaw.kapi.Dataframe

/**
 */

@Throws(Exception::class)
fun main(args: Array<String>) {
    val baseball = Dataframe.createFromCsv("data/baseball.csv")
    val x = baseball.nCol("BA")
    //Histogram.show("Distribution of team batting averages", x)

    Histogram.show(x)
}
