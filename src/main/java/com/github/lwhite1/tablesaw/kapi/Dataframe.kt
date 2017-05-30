package com.github.lwhite1.tablesaw.kapi

import com.github.lwhite1.tablesaw.api.*
import com.github.lwhite1.tablesaw.api.QueryHelper.column
import com.github.lwhite1.tablesaw.columns.Column
import com.github.lwhite1.tablesaw.filtering.Filter
import com.github.lwhite1.tablesaw.io.csv.CsvReader
import com.github.lwhite1.tablesaw.reducing.NumericReduceFunction
import com.github.lwhite1.tablesaw.reducing.SummaryFunction
import com.github.lwhite1.tablesaw.table.ViewGroup
import com.github.lwhite1.tablesaw.util.Selection
import it.unimi.dsi.fastutil.ints.IntComparator

/**
 *  Kotlin wrapper for Table.java
 */
class Dataframe (val target : Table) {

    fun name(): String = target.name()

    override fun toString(): String = target.toString()
    fun show(): String = target.print()
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

    fun addCol(column: Col) = target.addColumn(column.target())


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

    fun dateCol(columnName: String): DateCol = DateCol(target.dateColumn(columnName))
    fun timeCol(columnName: String): TimeCol = TimeCol(target.timeColumn(columnName))
    fun booleanCol(columnName: String): BooleanCol = BooleanCol(target.booleanColumn(columnName))
    fun categoryCol(columnName: String): CategoryCol = CategoryCol(target.categoryColumn(columnName))
    fun dateTimeCol(columnName: String): DateTimeCol = DateTimeCol(target.dateTimeColumn(columnName))

    fun nCol(columnName: String): NumberCol {
        val col = target.column(columnName)
        return when (col) {
            is DoubleColumn -> NumberCol(col)
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
            is DoubleColumn -> NumberCol(col)
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

    fun sampleSplit(table1Proportion: Double) : Array<Dataframe> {
        val tables = target.sampleSplit(table1Proportion)
        return tables.map {i -> Dataframe(i) }.toTypedArray()
    }

    fun sample(proportion: Double) : Dataframe {
        val table = target.sample(proportion)
        return Dataframe(table)
    }

    fun sortOn(vararg columnNames: String): Dataframe {
        return Dataframe(target.sortOn(*columnNames))
    }

    fun sortOn(rowComparator: IntComparator): Dataframe {
        return Dataframe(target.sortOn(rowComparator))
    }

    fun selectWhere(selection : Selection): Dataframe {
        return Dataframe(target.selectWhere(selection))
    }

    fun selectWhere(filter : Filter): Dataframe {
        return Dataframe(target.selectWhere(filter))
    }

    fun uniqueRecords(): Dataframe {
        return Dataframe(target.uniqueRecords())
    }
}