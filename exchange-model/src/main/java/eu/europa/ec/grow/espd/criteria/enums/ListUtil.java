package eu.europa.ec.grow.espd.criteria.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by ratoico on 1/15/16 at 4:10 PM.
 */
final class ListUtil {

    private ListUtil() {

    }

    @SafeVarargs
    static <T> List<T> list(T... values) {
        return Collections.unmodifiableList(Arrays.asList(values));
    }
}
