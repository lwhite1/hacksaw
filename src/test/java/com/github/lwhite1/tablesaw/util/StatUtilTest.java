package com.github.lwhite1.tablesaw.util;

import com.github.lwhite1.tablesaw.api.DoubleColumn;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class StatUtilTest {

    @Test
    public void testSum() {
        Random random = new Random();
        float sum = 0.0f;
        DoubleColumn column = DoubleColumn.create("c1");
        for (int i = 0; i < 100; i++) {
            float f = random.nextFloat();
            column.append(f);
            sum += f;
        }
        assertEquals(sum, column.sum(), 0.01f);
    }

    @Test
    public void testMin() {
        Random random = new Random();
        float min = Float.MAX_VALUE;
        DoubleColumn column = DoubleColumn.create("c1");
        for (int i = 0; i < 100; i++) {
            float f = random.nextFloat();
            column.append(f);
            if (min > f) {
                min = f;
            }
        }
        assertEquals(min, column.min(), 0.01f);
    }

    @Test
    public void testMax() {
        Random random = new Random();
        float max = Float.MIN_VALUE;
        DoubleColumn column = DoubleColumn.create("c1");
        for (int i = 0; i < 100; i++) {
            float f = random.nextFloat();
            column.append(f);
            if (max < f) {
                max = f;
            }
        }
        assertEquals(max, column.max(), 0.01f);
    }

    @Test
    public void testStats() {
        Random random = new Random();
        // assertEquals(sum, column.sum(), 0.01f);

    }
}