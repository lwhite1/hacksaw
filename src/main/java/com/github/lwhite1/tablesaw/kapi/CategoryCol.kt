package com.github.lwhite1.tablesaw.kapi

import com.github.lwhite1.tablesaw.api.CategoryColumn
import com.github.lwhite1.tablesaw.columns.Column
import com.github.lwhite1.tablesaw.util.Selection

/**
 *
 */
class CategoryCol(val target: CategoryColumn): Col {

    override fun target(): CategoryColumn = target

    override fun unique(): Col = CategoryCol(target.unique())

    override fun copy(): Col = CategoryCol(target.copy())

    override fun emptyCopy(rowSize: Int): Col = CategoryCol(target.emptyCopy(rowSize))

    override fun sortAscending() = target.sortAscending()

    override fun sortDescending() = target.sortDescending()

    /**
     * Creates a new column, replacing each string in this column with a new string formed by
     * replacing any substring that matches the regex
     */
    fun replaceAll(regexArray: Array<String>, replacement: String): CategoryCol
            = CategoryCol(target.replaceAll(regexArray, replacement))


    /**
     * Splits on Whitespace and returns the lexicographically sorted result
     */
    fun tokenizeAndSort(): CategoryCol = CategoryCol(target.tokenizeAndSort())
    fun tokenizeAndSort(separator: String): CategoryCol = CategoryCol(target.tokenizeAndSort(separator))

    fun tokenizeAndRemoveDuplicates(): CategoryCol = CategoryCol(target.tokenizeAndRemoveDuplicates())

    fun isIn(vararg strings: String): Selection = target.isIn(*strings)

    fun upperCase(): CategoryCol = CategoryCol(target.upperCase())
    fun lowerCase(): CategoryCol = CategoryCol(target.lowerCase())

    fun trim(): CategoryCol = CategoryCol(target.trim())

    fun replaceAll(regex: String, replacement: String): CategoryCol = CategoryCol(target.replaceAll(regex, replacement))
    fun replaceFirst(regex: String, replacement: String): CategoryCol
            = CategoryCol(target.replaceFirst(regex, replacement))

    fun substring(start: Int, end: Int): CategoryCol = CategoryCol(target.substring(start, end))
    fun substring(start: Int): CategoryCol = CategoryCol(target.substring(start))

    fun abbreviate(maxWidth: Int): CategoryCol = CategoryCol(target.abbreviate(maxWidth))

    fun padEnd(minLength: Int, padChar: Char): CategoryCol = CategoryCol(target.padEnd(minLength, padChar))
    fun padStart(minLength: Int, padChar: Char): CategoryCol = CategoryCol(target.padStart(minLength, padChar))

    fun commonPrefix(column2: Column<*>): CategoryCol = CategoryCol(target.commonPrefix(column2))
    fun commonSuffix(column2: Column<*>): CategoryCol = CategoryCol(target.commonSuffix(column2))

    /**
     * Returns a column containing the levenshtein distance between the two given string columns
     */
    fun distance(column2: Column<*>): Column<*> = target.distance(column2)

    fun join(column2: Column<*>, delimiter: String): CategoryCol = CategoryCol(target.join(column2, delimiter))
}