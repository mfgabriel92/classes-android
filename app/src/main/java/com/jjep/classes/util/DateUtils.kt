package com.jjep.classes.util

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    /**
     * Formats a string representation of date, by adding missing zeroes to the months and days
     *
     * @param y integer the year
     * @param m integer the month
     * @param d integer the day
     * @return the formatted date yyyy-MM-dd
     */
    fun convertToDate(y: String, m: String, d: String) : String {
        val month = if (m.length != 2) "0$m" else m
        val day = if (d.length != 2) "0$d" else d

        return "$y-$month-$day"
    }
}