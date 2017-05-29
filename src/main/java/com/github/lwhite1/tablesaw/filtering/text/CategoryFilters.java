package com.github.lwhite1.tablesaw.filtering.text;

import com.github.lwhite1.tablesaw.columns.CategoryColumnUtils;
import com.github.lwhite1.tablesaw.util.BitmapBackedSelection;
import com.github.lwhite1.tablesaw.util.DictionaryMap;
import com.github.lwhite1.tablesaw.util.Selection;
import it.unimi.dsi.fastutil.ints.IntRBTreeSet;
import it.unimi.dsi.fastutil.objects.Object2IntMap;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public interface CategoryFilters extends CategoryColumnUtils {

    default Selection startsWith(String string) {
        Selection results = new BitmapBackedSelection();

        IntRBTreeSet sorted = new IntRBTreeSet();
        DictionaryMap dictionaryMap = this.dictionaryMap();

        for (Object2IntMap.Entry<String> entry : dictionaryMap.valueToKeyMap().object2IntEntrySet()) {
            String key = entry.getKey();
            if (key.startsWith(string)) {
                sorted.add(entry.getIntValue());
            }
        }

        int i = 0;
        for (int next : values()) {
            if (sorted.contains(next)) {
                results.add(i);
            }
            i++;
        }
        return results;
    }

    default Selection matchesRegex(String string) {
        Pattern p = Pattern.compile(string);
        Selection results = new BitmapBackedSelection();
        int i = 0;
        for (String next : this) {
            Matcher m = p.matcher(next);
            if (m.matches()) {
                results.add(i);
            }
            i++;
        }
        return results;
    }

    default Selection hasLengthEqualTo(int lengthChars) {
        Selection results = new BitmapBackedSelection();
        int i = 0;
        for (String next : this) {
            if (next.length() == lengthChars) {
                results.add(i);
            }
            i++;
        }
        return results;
    }

    default Selection isShorterThan(int lengthChars) {
        Selection results = new BitmapBackedSelection();
        int i = 0;
        for (String next : this) {
            if (next.length() < lengthChars) {
                results.add(i);
            }
            i++;
        }
        return results;
    }

    default Selection isLongerThan(int lengthChars) {
        Selection results = new BitmapBackedSelection();
        int i = 0;
        for (String next : this) {
            if (next.length() > lengthChars) {
                results.add(i);
            }
            i++;
        }
        return results;
    }
}
