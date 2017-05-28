package com.github.lwhite1.tablesaw.kapi

import com.github.lwhite1.tablesaw.api.DoubleColumn
import com.github.lwhite1.tablesaw.api.QueryHelper.column
import com.github.lwhite1.tablesaw.api.Table
import com.github.lwhite1.tablesaw.io.csv.CsvReader
import com.github.lwhite1.tablesaw.reducing.NumericReduceFunction
import com.github.lwhite1.tablesaw.reducing.NumericReduceUtils.*
import com.github.lwhite1.tablesaw.reducing.SummaryFunction

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
  //  operator fun get(c: Int): Col = col(target.column(c))
  //  operator fun get(columnName: String): Col = col(target.column(columnName))

    fun columnNames(): List<String> = target.columnNames()

    // fun select(vararg columnName : String) : Projection = target.select(columnName)  // todo review
    fun selectWhere(): Table = target.selectWhere(column("name").isEqualTo("t"))  // todo review

    companion object Factory {
        fun createFromCsv(
                csvString : String,
                header: Boolean = true,
                delimiter: Char = ',',
                skipSampling: Boolean = false)

                : Dataframe
                = Dataframe(CsvReader.read(csvString, header, delimiter, skipSampling))
    }

    fun  setName(tableName: String) = target.setName(tableName)
    fun  removeColumns(vararg names : String) = target.removeColumns(*names)
    fun  exportToCsv(fileName: String) = target.exportToCsv(fileName)
    fun  save(fileName: String): String = target.save(fileName)


    fun summarize(numericColumnName: String, function: NumericReduceFunction): SummaryFunction = target.summarize(numericColumnName, function)

    fun sum(numericColumnName: String): SummaryFunction = target.summarize(numericColumnName, sum)


    fun mean(numericColumnName: String): SummaryFunction = target.summarize(numericColumnName, mean)

    fun median(numericColumnName: String): SummaryFunction = target.summarize(numericColumnName, median)

    fun variance(numericColumnName: String): SummaryFunction = target.summarize(numericColumnName, variance)

    fun stdDev(numericColumnName: String): SummaryFunction = target.summarize(numericColumnName, stdDev)

    fun count(numericColumnName: String): SummaryFunction = target.summarize(numericColumnName, n)

    fun max(numericColumnName: String): SummaryFunction = target.summarize(numericColumnName, max)

    fun minimum(numericColumnName: String): SummaryFunction = target.summarize(numericColumnName, min)


    //fun dateCol(columnName: String): DateCol = DateCol(target.dateColumn(columnName))

    fun nCol(columnName: String): NumberCol {
        val col = target.column(columnName)
        return when (col) {
            is DoubleColumn -> NumberCol(col)
            else -> {
                throw ClassCastException("Non-numeric column type")
            }
        }
    }

    /*
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
    */
}