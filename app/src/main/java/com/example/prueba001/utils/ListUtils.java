package com.example.prueba001.utils;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {

    public interface Predicate<K> {
        boolean evaluate(K element);
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        if (list == null && predicate == null) return null;
        List<T> filteredList = new ArrayList<>();
        for (T item: list) {
            if (!predicate.evaluate(item)) continue;
            filteredList.add(item);
        }
        return filteredList.isEmpty() ? null : filteredList;
    }

}
