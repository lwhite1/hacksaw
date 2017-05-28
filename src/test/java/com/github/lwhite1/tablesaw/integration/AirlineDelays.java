package com.github.lwhite1.tablesaw.integration;

import com.github.lwhite1.tablesaw.api.ColumnType;
import com.github.lwhite1.tablesaw.api.Table;
import com.github.lwhite1.tablesaw.io.csv.CsvReader;
import com.github.lwhite1.tablesaw.store.StorageManager;
import com.google.common.base.Stopwatch;

import java.util.concurrent.TimeUnit;

/**
 *
 */
public class AirlineDelays {

    // The full set of all available columns in tbe dataset
    static ColumnType[] heading = {
            ColumnType.DOUBLE, // year
            ColumnType.DOUBLE, // month
            ColumnType.DOUBLE, // day
            ColumnType.CATEGORY,  // dow
            ColumnType.LOCAL_TIME, // DepTime
            ColumnType.LOCAL_TIME, // CRSDepTime
            ColumnType.LOCAL_TIME, // ArrTime
            ColumnType.LOCAL_TIME, // CRSArrTime
            ColumnType.CATEGORY, // Carrier
            ColumnType.CATEGORY, // FlightNum
            ColumnType.CATEGORY, // TailNum
            ColumnType.DOUBLE, // ActualElapsedTime
            ColumnType.DOUBLE, // CRSElapsedTime
            ColumnType.DOUBLE, // AirTime
            ColumnType.DOUBLE, // ArrDelay
            ColumnType.DOUBLE, // DepDelay
            ColumnType.CATEGORY, // Origin
            ColumnType.CATEGORY, // Dest
            ColumnType.DOUBLE, // Distance
            ColumnType.DOUBLE, // TaxiIn
            ColumnType.DOUBLE, // TaxiOut
            ColumnType.BOOLEAN, // Cancelled
            ColumnType.CATEGORY, // CancellationCode
            ColumnType.BOOLEAN, // Diverted
            ColumnType.DOUBLE, // CarrierDelay
            ColumnType.DOUBLE, // WeatherDelay
            ColumnType.DOUBLE, // NASDelay
            ColumnType.DOUBLE, // SecurityDelay
            ColumnType.DOUBLE  // LateAircraftDelay
    };
    private static Table flights2008;
    // A filtered set of columns
    private static ColumnType[] reduced_set = {
            ColumnType.SKIP, // year
            ColumnType.DOUBLE, // month
            ColumnType.DOUBLE, // day
            ColumnType.CATEGORY,  // dow
            ColumnType.SKIP, // DepTime
            ColumnType.LOCAL_TIME, // CRSDepTime
            ColumnType.SKIP, // ArrTime
            ColumnType.SKIP, // CRSArrTime
            ColumnType.CATEGORY, // Carrier
            ColumnType.SKIP, // FlightNum
            ColumnType.SKIP, // TailNum
            ColumnType.SKIP, // ActualElapsedTime
            ColumnType.SKIP, // CRSElapsedTime
            ColumnType.SKIP, // AirTime
            ColumnType.SKIP, // ArrDelay
            ColumnType.DOUBLE, // DepDelay
            ColumnType.CATEGORY, // Origin
            ColumnType.CATEGORY, // Dest
            ColumnType.DOUBLE, // Distance
            ColumnType.SKIP, // TaxiIn
            ColumnType.SKIP, // TaxiOut
            ColumnType.BOOLEAN, // Cancelled
            ColumnType.SKIP, // CancellationCode
            ColumnType.SKIP, // Diverted
            ColumnType.SKIP, // CarrierDelay
            ColumnType.SKIP, // WeatherDelay
            ColumnType.SKIP, // NASDelay
            ColumnType.SKIP, // SecurityDelay
            ColumnType.SKIP  // LateAircraftDelay
    };

    private AirlineDelays() throws Exception {
        Stopwatch stopwatch = Stopwatch.createStarted();
        System.out.println("loading");
        flights2008 = CsvReader.read(reduced_set, "bigdata/2015.csv");
        System.out.println(String.format("loaded %d records in %d seconds",
                flights2008.rowCount(),
                stopwatch.elapsed(TimeUnit.SECONDS)));
        out(flights2008.shape());
        out(flights2008.columnNames().toString());
        flights2008.first(10).print();
        StorageManager.saveTable("bigdata", flights2008);
        stopwatch.reset().start();
    }

    public static void main(String[] args) throws Exception {

        new AirlineDelays();

        Stopwatch stopwatch = Stopwatch.createStarted();
        flights2008.sortAscendingOn("Origin", "UniqueCarrier");
        System.out.println("Sorting " + stopwatch.elapsed(TimeUnit.SECONDS));
    }

    private static void out(Object obj) {
        System.out.println(String.valueOf(obj));
    }
}
