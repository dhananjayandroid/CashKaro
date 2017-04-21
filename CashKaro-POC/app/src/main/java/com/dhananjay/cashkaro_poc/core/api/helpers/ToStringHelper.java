package com.dhananjay.cashkaro_poc.core.api.helpers;

/**
 * Created by Dhananjay on 04-01-2017.
 */

import android.util.Pair;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This class extends acts as a helper class for string manipulation
 *
 * @author Dhananjay Kumar
 */
public class ToStringHelper {
    /**
     * The constant SEPARATOR.
     */
    public static final String SEPARATOR = "\n";
    private static final String ARROW = "=";
    /**
     * The constant COMMA_SEPARATOR.
     */
    public static final String COMMA_SEPARATOR = ",";

    /**
     * Instantiates a new To string helper.
     */
    public ToStringHelper() {
    }

    /**
     * To string conversion.
     *
     * @param l the l
     * @return the string
     */
    public static String toString(List<?> l) {
        if (l == null) {
            return "null";
        } else {
            StringBuilder sb = new StringBuilder("(");
            String sep = "";

            for (Iterator i$ = l.iterator(); i$.hasNext(); sep = "\n") {
                Object object = i$.next();
                sb.append(sep).append(object.toString());
            }

            return sb.append(")").toString();
        }
    }

    /**
     * To string conversion.
     *
     * @param l          the list
     * @param separator  the separator
     * @param leftDelim  the left delimiter
     * @param rightDelim the right delimiter
     * @return the string
     */
    public static String toString(List<?> l, String separator, String leftDelim, String rightDelim) {
        if (l == null) {
            return "null";
        } else {
            StringBuilder sb = new StringBuilder(leftDelim);
            String sep = "";

            for (Iterator i$ = l.iterator(); i$.hasNext(); sep = separator) {
                Object object = i$.next();
                sb.append(sep).append(object.toString());
            }

            return sb.append(rightDelim).toString();
        }
    }

    /**
     * To string conversion.
     *
     * @param l         the list
     * @param separator the separator
     * @return the string
     */
    public static String toString(List<?> l, String separator) {
        return toString(l, separator, "", "");
    }

    /**
     * To string conversion.
     *
     * @param m      the map
     * @param prefix the prefix
     * @return the string
     */
    public static String toString(Map<?, ?> m, String prefix) {
        return toString(m, prefix, "\n");
    }

    /**
     * To string string.
     *
     * @param m         the m
     * @param prefix    the prefix
     * @param separator the separator
     * @return the string
     */
    public static String toString(Map<?, ?> m, String prefix, String separator) {
        return toStringBuilder(m, prefix, separator).toString();
    }

    /**
     * String to string builder.
     *
     * @param m         the m
     * @param prefix    the prefix
     * @param separator the separator
     * @return the string builder
     */
    public static StringBuilder toStringBuilder(Map<?, ?> m, String prefix, String separator) {
        StringBuilder sb = new StringBuilder();
        if (m == null) {
            sb.append("null");
            return sb;
        } else {
            String sep = "";

            for (Iterator i$ = m.keySet().iterator(); i$.hasNext(); sep = separator) {
                Object object = i$.next();
                Object value = m.get(object);
                sb.append(sep).append(prefix).append(object.toString()).append("=").append(value.toString());
            }

            return sb;
        }
    }

    /**
     * string pair list to string.
     *
     * @param pairList the pair list
     * @param prefix   the prefix
     * @return the string
     */
    public static String toStringPairList(List<? extends Pair<?, ?>> pairList, String prefix) {
        return toStringPairList(pairList, prefix, "\n");
    }

    /**
     * String pair list to string.
     *
     * @param pairList  the pair list
     * @param prefix    the prefix
     * @param separator the separator
     * @return the string
     */
    public static String toStringPairList(List<? extends Pair<?, ?>> pairList, String prefix, String separator) {
        if (pairList == null) {
            return "null";
        } else {
            StringBuilder sb = new StringBuilder();
            String sep = "";

            for (Iterator i$ = pairList.iterator(); i$.hasNext(); sep = separator) {
                Pair item = (Pair) i$.next();
                sb.append(sep).append(prefix).append(String.valueOf(item.first)).append("=").append(String.valueOf(item.second));
            }

            return sb.toString();
        }
    }

    /**
     * param to string.
     *
     * @param keys      the keys
     * @param values    the values
     * @param separator the separator
     * @return the string
     */
    public static String toParamString(String[] keys, Object[] values, String separator) {
        if (keys == null) {
            return "null";
        } else {
            HashMap stringObjectMap = new HashMap();

            for (int i = 0; i < keys.length && i < values.length; ++i) {
                Object value = values[i];
                if (value != null) {
                    stringObjectMap.put(keys[i], value);
                }
            }

            return toString(stringObjectMap, "", separator);
        }
    }

    /**
     * To string string.
     *
     * @param s the s
     * @return the string
     */
    public static String toString(Set<?> s) {
        if (s == null) {
            return "null";
        } else {
            StringBuilder sb = new StringBuilder("{");
            String sep = "";

            for (Iterator i$ = s.iterator(); i$.hasNext(); sep = "\n") {
                Object object = i$.next();
                sb.append(sep).append(object.toString());
            }

            return sb.append("}").toString();
        }
    }

    /**
     * Array to string string.
     *
     * @param values the values
     * @return the string
     */
    public static String arrayToString(Object... values) {
        StringBuilder arrayString = new StringBuilder();
        String delimiter = "";
        if (values != null && values.length > 0) {
            Object[] arr$ = values;
            int len$ = values.length;

            for (int i$ = 0; i$ < len$; ++i$) {
                Object obj = arr$[i$];
                arrayString.append(delimiter);
                arrayString.append(obj);
                delimiter = ",";
            }
        }

        return arrayString.toString();
    }
}
