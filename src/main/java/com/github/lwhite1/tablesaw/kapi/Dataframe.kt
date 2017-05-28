package com.github.lwhite1.tablesaw.kapi

import com.github.lwhite1.tablesaw.api.BooleanColumn
import com.github.lwhite1.tablesaw.api.CategoryColumn
import com.github.lwhite1.tablesaw.api.DateColumn
import com.github.lwhite1.tablesaw.api.DateTimeColumn
import com.github.lwhite1.tablesaw.api.DoubleColumn
import com.github.lwhite1.tablesaw.api.QueryHelper.column
import com.github.lwhite1.tablesaw.api.Table
import com.github.lwhite1.tablesaw.api.TimeColumn
import com.github.lwhite1.tablesaw.columns.Column
import com.github.lwhite1.tablesaw.reducing.functions.Count
import com.github.lwhite1.tablesaw.reducing.functions.Maximum
import com.github.lwhite1.tablesaw.reducing.functions.Mean
import com.github.lwhite1.tablesaw.reducing.functions.Median
import com.github.lwhite1.tablesaw.reducing.functions.Minimum
import com.github.lwhite1.tablesaw.reducing.functions.StandardDeviation
import com.github.lwhite1.tablesaw.reducing.functions.Sum
import com.github.lwhite1.tablesaw.reducing.functions.Variance


/**
 *  Kotlin wrapper for Table.java
 */

class Dataframe (val target : Table) {

    fun name(): String = target.name()

    override fun toString(): String = target.toString()
    fun print(): String = target.print()
    fun printHtml(): String = target.printHtml()

    fun summary(): String = target.summary()
    fun shape(): String = target.shape()
    fun structure(): Dataframe = Dataframe(target.structure())

    fun emptyCopy(): Dataframe = Dataframe(target.emptyCopy())
    fun emptyCopy(rowCount: Int): Dataframe = Dataframe(target.emptyCopy(rowCount))

    fun first(nRows: Int): Dataframe = Dataframe(target.first(nRows))
    fun last(nRows: Int): Dataframe = Dataframe(target.last(nRows))

    fun rowCount(): Int = target.rowCount()
    fun columnCount(): Int = target.columnCount()

    fun columnIndex(columnName: String): Int = target.columnIndex(columnName)
    fun append(dataframe: Dataframe) = target.append(dataframe.target)

    operator fun get(c: Int, r: Int): String = target.get(c, r)
    operator fun get(c: Int): Col = col(target.column(c))
    operator fun get(columnName: String): Col = col(target.column(columnName))

    fun columnNames(): List<String> = target.columnNames()

    // fun select(vararg columnName : String) : Projection = target.select(columnName)  // todo review
    fun selectWhere(): Table = target.selectWhere(column("name").isEqualTo("t"))  // todo review

    companion object Factory {
        fun createFromCsv(
                csvString : String,
                header: Boolean = false,
                delimiter: Char = ',')

                : Dataframe
                = Dataframe(Table.createFromCsv(csvString, header, delimiter))
    }

    fun  setName(tableName: String) = target.setName(tableName)
    fun  removeColumns(vararg names : String) = target.removeColumns(*names)
    fun exportToCsv(fileName: String) = target.exportToCsv(fileName)
    fun  save(fileName: String): String = target.save(fileName)


    fun sum(numericColumnName: String): Sum = target.sum(numericColumnName)

    fun mean(numericColumnName: String): Mean = target.mean(numericColumnName)

    fun median(numericColumnName: String): Median = target.median(numericColumnName)

    fun variance(numericColumnName: String): Variance = target.variance(numericColumnName)

    fun stdDev(numericColumnName: String): StandardDeviation = target.stdDev(numericColumnName)

    fun count(numericColumnName: String): Count = target.count(numericColumnName)

    fun max(numericColumnName: String): Maximum = target.max(numericColumnName)

    fun minimum(numericColumnName: String): Minimum = target.minimum(numericColumnName)

    fun dateCol(columnName: String): DateCol = DateCol(target.dateColumn(columnName))

    fun nCol(columnName: String): DoubleCol {
        val col = target.column(columnName)
        return when (col) {
            is DoubleColumn -> DoubleCol(col)
            else -> {
                throw ClassCastException("Non-numeric column type")
            }
        }
    }

    fun col(columnName: String): Col {
        val col = target.column(columnName)
        return col(col)
    }

    fun col(col: Column<*>): Col {
        return when (col) {
            is DoubleColumn -> DoubleCol(col)
            is DateColumn -> DateCol(col)
            is TimeColumn -> TimeCol(col)
            is DateTimeColumn -> DateTimeCol(col)
            is BooleanColumn -> BooleanCol(col)
            is CategoryColumn -> CategoryCol(col)
            else -> {
                throw ClassCastException("Unhandled column type")
            }
        }
    }
}