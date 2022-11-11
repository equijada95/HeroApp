package com.example.prueba001.utils

import java.util.ArrayList
import java.util.Arrays

fun String.getList(): List<String> {
    val firstReplace = replace("[", "")
    val secondReplace = firstReplace.replace("]", "")
    return ArrayList(Arrays.asList(*secondReplace.split(",").toTypedArray()))
}
