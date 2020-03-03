package graeme.hosford.eventmanager.presentation.utils

import java.text.SimpleDateFormat
import java.util.*

object DatePresentationUtils {
    fun formatDateRange(startDate: Calendar, endDate: Calendar): String {
        val dateFormatter = SimpleDateFormat("MMM dd", Locale.getDefault())
        val dateFormatterWithYear = SimpleDateFormat("MMM dd yyyy", Locale.getDefault())

        val start = dateFormatter.format(startDate.time)

        if (startDate.isSameDay(endDate)) {
            return if (startDate.isSameYear(Calendar.getInstance())) {
                start
            } else {
                dateFormatterWithYear.format(startDate.time)
            }
        }

        val end = dateFormatter.format(endDate.time)

        return if (startDate.isSameYear(endDate)) {
            if (startDate.isSameYear(Calendar.getInstance())) {
                "$start - $end"
            } else {
                "$start - ${dateFormatterWithYear.format(endDate.time)}"
            }
        } else {
            "${dateFormatterWithYear.format(startDate.time)} - ${dateFormatterWithYear.format(
                endDate.time
            )}"
        }
    }

    fun formatTimeRange(start: Calendar, end: Calendar): String {
        val formatter = SimpleDateFormat("HH:mm", Locale.getDefault())

        val startTime = formatter.format(start.time)
        val endTime = formatter.format(end.time)

        return "$startTime - $endTime"
    }

    private fun Calendar.isSameDay(another: Calendar): Boolean {
        return (get(Calendar.DAY_OF_MONTH) == another.get(Calendar.DAY_OF_MONTH) &&
                (get(Calendar.MONTH) == another.get(Calendar.MONTH)) &&
                (get(Calendar.YEAR) == another.get(Calendar.YEAR)))
    }

    private fun Calendar.isSameYear(another: Calendar): Boolean {
        return get(Calendar.YEAR) == another.get(Calendar.YEAR)
    }
}