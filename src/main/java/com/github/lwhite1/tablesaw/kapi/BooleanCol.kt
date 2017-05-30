package com.github.lwhite1.tablesaw.kapi

import com.github.lwhite1.tablesaw.api.BooleanColumn
import com.github.lwhite1.tablesaw.util.Selection

/**
 *
 */
class BooleanCol(val target: BooleanColumn): Col {

    override fun target(): BooleanColumn = target

    override fun unique(): Col = BooleanCol(target.unique())

    override fun copy(): Col = BooleanCol(target.copy())

    override fun emptyCopy(rowSize: Int): Col = BooleanCol(target.emptyCopy(rowSize))

    override fun sortAscending() = target.sortAscending()

    override fun sortDescending() = target.sortDescending()

    fun countTrue(): Int = target.countTrue()

    fun countFalse(): Int = target.countFalse()

    fun isFalse(): Selection = target.isFalse

    fun isTrue(): Selection = target.isTrue

}