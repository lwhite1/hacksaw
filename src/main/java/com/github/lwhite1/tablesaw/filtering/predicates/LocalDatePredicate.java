package com.github.lwhite1.tablesaw.filtering.predicates;

import com.github.lwhite1.tablesaw.columns.packeddata.PackedLocalDate;

import java.time.LocalDate;

/**
 *
 */
public interface LocalDatePredicate {

    boolean test(LocalDate d);

}