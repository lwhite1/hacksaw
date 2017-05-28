package com.github.lwhite1.tablesaw.mapping;

import com.github.lwhite1.tablesaw.api.DateTimeColumn;
import com.github.lwhite1.tablesaw.api.DoubleColumn;
import com.github.lwhite1.tablesaw.columns.DateTimeColumnUtils;
import com.github.lwhite1.tablesaw.columns.packeddata.PackedLocalDateTime;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public interface DateTimeMapUtils extends DateTimeColumnUtils {

    default DoubleColumn differenceInMilliseconds(DateTimeColumn column2) {
        return difference(column2, ChronoUnit.MILLIS);
    }

    default DoubleColumn differenceInSeconds(DateTimeColumn column2) {
        return difference(column2, ChronoUnit.SECONDS);
    }

    default DoubleColumn differenceInMinutes(DateTimeColumn column2) {
        return difference(column2, ChronoUnit.MINUTES);
    }

    default DoubleColumn differenceInHours(DateTimeColumn column2) {
        return difference(column2, ChronoUnit.HOURS);
    }

    default DoubleColumn differenceInDays(DateTimeColumn column2) {
        return difference(column2, ChronoUnit.DAYS);
    }

    default DoubleColumn differenceInYears(DateTimeColumn column2) {
        return difference(column2, ChronoUnit.YEARS);
    }

    default DoubleColumn difference(DateTimeColumn column2, ChronoUnit unit) {
        DoubleColumn newColumn = DoubleColumn.create(name() + " - " + column2.name());

        for (int r = 0; r < size(); r++) {
            long c1 = this.getLong(r);
            long c2 = column2.getLong(r);
            if (c1 == DateTimeColumn.MISSING_VALUE || c2 == DateTimeColumn.MISSING_VALUE) {
                newColumn.append(DoubleColumn.MISSING_VALUE);
            } else {
                newColumn.append(difference(c1, c2, unit));
            }
        }
        return newColumn;
    }

    default long difference(long packedLocalDateTime1, long packedLocalDateTime2, ChronoUnit unit) {
        LocalDateTime value1 = PackedLocalDateTime.asLocalDateTime(packedLocalDateTime1);
        LocalDateTime value2 = PackedLocalDateTime.asLocalDateTime(packedLocalDateTime2);
        return unit.between(value1, value2);
    }

    default DoubleColumn hour() {
        DoubleColumn newColumn = DoubleColumn.create(name() + "[" + "hour" + "]");
        for (int r = 0; r < size(); r++) {
            long c1 = getLong(r);
            if (c1 != DateTimeColumn.MISSING_VALUE) {
                newColumn.append(PackedLocalDateTime.getHour(c1));
            } else {
                newColumn.append(DoubleColumn.MISSING_VALUE);
            }
        }
        return newColumn;
    }

    default DoubleColumn minuteOfDay() {
        DoubleColumn newColumn = DoubleColumn.create(name() + "[" + "minute-of-day" + "]");
        for (int r = 0; r < size(); r++) {
            long c1 = getLong(r);
            if (c1 != DateTimeColumn.MISSING_VALUE) {
                newColumn.append((short) PackedLocalDateTime.getMinuteOfDay(c1));
            } else {
                newColumn.append(DoubleColumn.MISSING_VALUE);
            }
        }
        return newColumn;
    }

    default DoubleColumn secondOfDay() {
        DoubleColumn newColumn = DoubleColumn.create(name() + "[" + "second-of-day" + "]");
        for (int r = 0; r < size(); r++) {
            long c1 = getLong(r);
            if (c1 != DateTimeColumn.MISSING_VALUE) {
                newColumn.append(PackedLocalDateTime.getSecondOfDay(c1));
            } else {
                newColumn.append(DoubleColumn.MISSING_VALUE);
            }
        }
        return newColumn;
    }

    LocalDateTime get(int r);

    long getLong(int r);
}
