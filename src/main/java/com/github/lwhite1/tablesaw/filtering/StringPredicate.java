package com.github.lwhite1.tablesaw.filtering;

/**
 *
 */
public interface StringPredicate {

    StringPredicate isEmpty = String::isEmpty;

    boolean test(String i);

}
