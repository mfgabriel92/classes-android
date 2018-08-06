package com.jjep.classes.util

import android.annotation.SuppressLint
import android.content.Context
import com.jjep.classes.R

object TimeUtils {
    /**
     * Formats a incoming time with a 12 hour format hh:mm a
     *
     * @param context the context
     * @param hour the incoming hour
     * @param minute the incoming minute
     * @return the formatted time hh:mm a
     */
    @SuppressLint("StringFormatMatches")
    fun get12HoursTimeFormat(context: Context, hour: Int, minute: Int) : String {
        var h: Int = hour
        val m: Int = if (minute.toString().length == 2) minute else "0$minute".toInt()
        var ampm: String

        when {
            hour > 12 -> {
                h -= 12
                ampm = "PM"
            }
            hour == 0 -> {
                h += 12
                ampm = "AM";
            }
            hour == 12 -> ampm = "PM"
            else -> ampm = "AM"
        }

        return context.getString(R.string.input_time, h, m, ampm)
    }
}