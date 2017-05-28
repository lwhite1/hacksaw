package com.github.lwhite1.tablesaw.mapping;

import com.github.lwhite1.tablesaw.api.DoubleColumn;
import com.github.lwhite1.tablesaw.api.TimeColumn;
import com.github.lwhite1.tablesaw.columns.TimeColumnUtils;
import com.github.lwhite1.tablesaw.columns.packeddata.PackedLocalTime;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public interface TimeMapUtils extends TimeColumnUtils {

    default DoubleColumn differenceInMilliseconds(TimeColumn column2) {
        return difference(column2, ChronoUnit.MILLIS);
    }

    default DoubleColumn differenceInSeconds(TimeColumn column2) {
        return difference(column2, ChronoUnit.SECONDS);
    }

    default DoubleColumn differenceInMinutes(TimeColumn column2) {
        return difference(column2, ChronoUnit.MINUTES);
    }

    default DoubleColumn differenceInHours(TimeColumn column2) {
        return difference(column2, ChronoUnit.HOURS);
    }

    default DoubleColumn difference(TimeColumn column2, ChronoUnit unit) {
        DoubleColumn newColumn = DoubleColumn.create(name() + " - " + column2.name());

        for (int r = 0; r < size(); r++) {
            int c1 = this.getInt(r);
            int c2 = column2.getInt(r);
            if (c1 == TimeColumn.MISSING_VALUE || c2 == TimeColumn.MISSING_VALUE) {
                newColumn.append(DoubleColumn.MISSING_VALUE);
            } else {
                newColumn.append(difference(c1, c2, unit));
            }
        }
        return newColumn;
    }

    default long difference(int packedLocalTime1, int packedLocalTime2, ChronoUnit unit) {
        LocalTime value1 = PackedLocalTime.asLocalTime(packedLocalTime1);
        LocalTime value2 = PackedLocalTime.asLocalTime(packedLocalTime2);
        return unit.between(value1, value2);
    }

    default DoubleColumn hour() {
        DoubleColumn newColumn = DoubleColumn.create(name() + "[" + "hour" + "]");
        for (int r = 0; r < size(); r++) {
            int c1 = getInt(r);
            if (c1 != TimeColumn.MISSING_VALUE) {
                newColumn.append(PackedLocalTime.getHour(c1));
            } else {
                newColumn.append(DoubleColumn.MISSING_VALUE);
            }
        }
        return newColumn;
    }

    default DoubleColumn minuteOfDay() {
        DoubleColumn newColumn = DoubleColumn.create(name() + "[" + "minute-of-day" + "]");
        for (int r = 0; r < size(); r++) {
            int c1 = getInt(r);
            if (c1 != TimeColumn.MISSING_VALUE) {
                newColumn.append(PackedLocalTime.getMinuteOfDay(c1));
            } else {
                newColumn.append(DoubleColumn.MISSING_VALUE);
            }
        }
        return newColumn;
    }

    default DoubleColumn secondOfDay() {
        DoubleColumn newColumn = DoubleColumn.create(name() + "[" + "second-of-day" + "]");
        for (int r = 0; r < size(); r++) {
            int c1 = getInt(r);
            if (c1 != TimeColumn.MISSING_VALUE) {
                newColumn.append(PackedLocalTime.getSecondOfDay(c1));
            } else {
                newColumn.append(DoubleColumn.MISSING_VALUE);
            }
        }
        return newColumn;
    }

    LocalTime get(int r);

    int getInt(int r);
}
