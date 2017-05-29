package com.github.lwhite1.tablesaw.filtering.predicates;

/**
 *
 */
public interface StringPredicate {

    StringPredicate isEmpty = String::isEmpty;

    boolean test(String i);

}
