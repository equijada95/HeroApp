package com.example.prueba001.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringUtils {

    public static List<String> getListFromString(String string) {
        String replace = string.replace("[","");
        String replace1 = replace.replace("]","");
        return new ArrayList<String>(Arrays.asList(replace1.split(",")));
    }

}
