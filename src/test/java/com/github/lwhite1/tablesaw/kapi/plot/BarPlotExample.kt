package com.github.lwhite1.tablesaw.kapi.plot

import com.github.lwhite1.tablesaw.api.plot.Bar
import com.github.lwhite1.tablesaw.kapi.Dataframe

/**
 */

@Throws(Exception::class)
fun main(args: Array<String>) {
    val baseball = Dataframe.createFromCsv("data/baseball.csv")
    val team = baseball.categoryCol("team")
    val x = baseball.nCol("W")
    Bar.show("Title", team.target, x.target)
}
