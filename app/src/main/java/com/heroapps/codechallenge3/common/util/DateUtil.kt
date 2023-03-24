package com.heroapps.codechallenge3.common.util

import android.os.Build
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.util.Calendar
import java.util.Date

fun formatDate(c: Calendar): String {
    val sdf = SimpleDateFormat("MMMM dd, yyyy")
    return sdf.format(c.time)
}

fun getAge(c: Calendar): Int {
    val today = Calendar.getInstance()
    var age = today.get(Calendar.YEAR) - c.get(Calendar.YEAR)

    when {
        c.get(Calendar.MONTH) > today.get(Calendar.MONTH) -> age -= 1
        c.get(Calendar.MONTH) == today.get(Calendar.MONTH) -> {
            if (c.get(Calendar.DAY_OF_MONTH) > today.get(Calendar.DAY_OF_MONTH)) {
                age -= 1
            }
        }
    }

    return age
}

fun getCalendar18FromNow(): Calendar {

    val calendar = Calendar.getInstance()

    val year = calendar.get(Calendar.YEAR) - 18
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    calendar.set(year, month, day)

    return calendar
}