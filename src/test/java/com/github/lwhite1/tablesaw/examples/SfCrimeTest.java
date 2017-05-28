package com.github.lwhite1.tablesaw.examples;

import com.github.lwhite1.tablesaw.api.CategoryColumn;
import com.github.lwhite1.tablesaw.api.ColumnType;
import com.github.lwhite1.tablesaw.api.DoubleColumn;
import com.github.lwhite1.tablesaw.api.Table;
import com.github.lwhite1.tablesaw.io.csv.CsvReader;

import static com.github.lwhite1.tablesaw.api.ColumnType.*;

/**
 *
 */
public class SfCrimeTest {

    public static void main(String[] args) throws Exception {

        Table crime = Table.createFromCsv("/Users/larrywhite/IdeaProjects/testdata/bigdata/train.csv");

        out(crime.shape());
        out(crime.structure().print());

        crime.removeColumns("DayOfWeek");

        DoubleColumn precinct = crime.categoryColumn("PdDistrict").toIntColumn();
        precinct.setName("Precinct");
        crime.addColumn(precinct);

        DoubleColumn year = crime.dateTimeColumn("Dates").year();
        year.setName("Year");
        crime.addColumn(year);

        CategoryColumn category = crime.categoryColumn("Category");
        Table categorySummary = category.summary().sortDescendingOn("Count");
        out(categorySummary.print());

        DoubleColumn minuteOfDay = crime.dateTimeColumn("Dates").minuteOfDay();
        minuteOfDay.setName("MinuteOfDay");
        crime.addColumn(minuteOfDay);

        DoubleColumn dayOfYear = crime.dateTimeColumn("Dates").dayOfYear();
        dayOfYear.setName("DayOfYear");
        crime.addColumn(dayOfYear);

        DoubleColumn dayOfWeekValue = crime.dateTimeColumn("Dates").dayOfWeekValue();
        dayOfWeekValue.setName("DayOfWeek");
        crime.addColumn(dayOfWeekValue);

        Table[] subTables = crime.sampleSplit(.1);
        Table train = subTables[0];
        Table test = subTables[1];


        out(CsvReader.printColumnTypes("/Users/larrywhite/IdeaProjects/testdata/bigdata/sampleSubmission.csv", true,
                ','));

/*
        LogisticRegression model = LogisticRegression.learn(
                train.categoryColumn("Category"),
                0.1,
                1.0E-3,
                700,
                train.nCol("X"),
                train.nCol("Y"),
                train.nCol("MinuteOfDay"),
                train.nCol("DayOfYear"),
                train.nCol("DayOfWeek"),
                train.nCol("Year"),
                train.nCol("Precinct"));

        out("Model trained");

        ConfusionMatrix matrix = model.predictMatrix(test.categoryColumn("Category"),
                test.nCol("X"),
                test.nCol("Y"),
                test.nCol("MinuteOfDay"),
                test.nCol("DayOfYear"),
                test.nCol("DayOfWeek"),
                test.nCol("Year"),
                test.nCol("Precinct"));

        out(matrix.accuracy());
        out(matrix.toTable().print());
*/

        // Table trueCrime = Table.createFromCsv("/Users/larrywhite/IdeaProjects/testdata/bigdata/test.csv");
        // out(CsvReader.printColumnTypes("/Users/larrywhite/IdeaProjects/testdata/bigdata/sampleSubmission.csv",
        // true, ','));

        ColumnType[] columnTypes = {
                DOUBLE, // 0     Id
                DOUBLE,  // 1     ARSON
                DOUBLE,  // 2     ASSAULT
                DOUBLE,  // 3     BAD CHECKS
                DOUBLE,  // 4     BRIBERY
                DOUBLE,  // 5     BURGLARY
                DOUBLE,  // 6     DISORDERLY CONDUCT
                DOUBLE,  // 7     DRIVING UNDER THE INFLUENCE
                DOUBLE,  // 8     DRUG/NARCOTIC
                DOUBLE,  // 9     DRUNKENNESS
                DOUBLE,  // 10    EMBEZZLEMENT
                DOUBLE,  // 11    EXTORTION
                DOUBLE,  // 12    FAMILY OFFENSES
                DOUBLE,  // 13    FORGERY/COUNTERFEITING
                DOUBLE,  // 14    FRAUD
                DOUBLE,  // 15    GAMBLING
                DOUBLE,  // 16    KIDNAPPING
                DOUBLE,  // 17    LARCENY/THEFT
                DOUBLE,  // 18    LIQUOR LAWS
                DOUBLE,  // 19    LOITERING
                DOUBLE,  // 20    MISSING PERSON
                DOUBLE,  // 21    NON-CRIMINAL
                DOUBLE,  // 22    OTHER OFFENSES
                DOUBLE,  // 23    PORNOGRAPHY/OBSCENE MAT
                DOUBLE,  // 24    PROSTITUTION
                DOUBLE,  // 25    RECOVERED VEHICLE
                DOUBLE,  // 26    ROBBERY
                DOUBLE,  // 27    RUNAWAY
                DOUBLE,  // 28    SECONDARY CODES
                DOUBLE,  // 29    SEX OFFENSES FORCIBLE
                DOUBLE,  // 30    SEX OFFENSES NON FORCIBLE
                DOUBLE,  // 31    STOLEN PROPERTY
                DOUBLE,  // 32    SUICIDE
                DOUBLE,  // 33    SUSPICIOUS OCC
                DOUBLE,  // 34    TREA
                DOUBLE,  // 35    TRESPASS
                DOUBLE,  // 36    VANDALISM
                DOUBLE,  // 37    VEHICLE THEFT
                DOUBLE,  // 38    WARRANTS
                DOUBLE,  // 39    WEAPON LAWS
        };

        Table results = CsvReader.read(columnTypes,
                "/Users/larrywhite/IdeaProjects/testdata/bigdata/sampleSubmission.csv");

        DoubleColumn larceny = results.numericColumn("LARCENY/THEFT");
        DoubleColumn warrants = results.numericColumn("WARRANTS");

/*
        Table trueCrime = testData();
        for (int row : trueCrime) {
            double[] posteriori = new double[39];
            model.predictFromModel(row, posteriori,
                    test.nCol("X"),
                    test.nCol("Y"),
                    test.nCol("MinuteOfDay"),
                    test.nCol("DayOfYear"),
                    test.nCol("DayOfWeek"),
                    test.nCol("Year"),
                    test.nCol("Precinct")
            );

            larceny.set(row, 1.0f);
            warrants.set(row, 0f);
        }
        results.exportToCsv("newSubmission.csv");
*/
    }

    private static Table testData() throws Exception {
        // Setup actual test data
        Table trueCrime = Table.createFromCsv("/Users/larrywhite/IdeaProjects/testdata/bigdata/test.csv");

        trueCrime.removeColumns("DayOfWeek");

        DoubleColumn precinctT = trueCrime.categoryColumn("PdDistrict").toIntColumn();
        precinctT.setName("Precinct");
        trueCrime.addColumn(precinctT);

        DoubleColumn yearT = trueCrime.dateTimeColumn("Dates").year();
        yearT.setName("Year");
        trueCrime.addColumn(yearT);

        DoubleColumn minuteOfDayT = trueCrime.dateTimeColumn("Dates").minuteOfDay();
        minuteOfDayT.setName("MinuteOfDay");
        trueCrime.addColumn(minuteOfDayT);

        DoubleColumn dayOfYearT = trueCrime.dateTimeColumn("Dates").dayOfYear();
        dayOfYearT.setName("DayOfYear");
        trueCrime.addColumn(dayOfYearT);

        DoubleColumn dayOfWeekValueT = trueCrime.dateTimeColumn("Dates").dayOfWeekValue();
        dayOfWeekValueT.setName("DayOfWeek");
        trueCrime.addColumn(dayOfWeekValueT);
        return trueCrime;
    }

    private static void out(Object str) {
        System.out.println(String.valueOf(str));
    }
}
