package graeme.hosford.eventmanager.presentation.utils

import org.junit.Assert
import org.junit.Test
import java.util.*

class DatePresentationUtilsTest {

    @Test
    fun formatTimeRange_returnsCorrect24HourTime() {
        val start = getCalendar(hourOfDay = 9, minute = 30)
        val end = getCalendar(hourOfDay = 15, minute = 52)

        val timeString = DatePresentationUtils.formatTimeRange(start, end)

        Assert.assertEquals("09:30 - 15:52", timeString)
    }

    @Test
    fun formatDateRange_returnsOnlyStartDateWhenStartAndEndOnSameDay() {
        val start = getCalendar()
        val end = getCalendar()

        val dateString = DatePresentationUtils.formatDateRange(start, end)

        Assert.assertEquals("Feb 25", dateString)
    }

    @Test
    fun formatDateRange_returnsStartDateWithYearWHenInDifferentYear() {
        val start = getCalendar(year = 2100)
        val end = getCalendar(year = 2100)

        val dateString = DatePresentationUtils.formatDateRange(start, end)

        Assert.assertEquals("Feb 25 2100", dateString)
    }

    @Test
    fun formatDateRange_returnsCorrectRangeString_whenStartAndEndInSameYearAndStartIsCurrentYear() {
        val start = getCalendar(dayOfMonth = 10)
        val end = getCalendar(dayOfMonth = 11)

        val dateString = DatePresentationUtils.formatDateRange(start, end)

        Assert.assertEquals("Feb 10 - Feb 11", dateString)
    }

    @Test
    fun formatDateRange_returnsCorrectRangeString_whenStartDateAndEndDateInSameYearAndNotCurrentYear() {
        val start = getCalendar(year = 2100, dayOfMonth = 10)
        val end = getCalendar(year = 2100, dayOfMonth = 11)

        val dateString = DatePresentationUtils.formatDateRange(start, end)

        Assert.assertEquals("Feb 10 - Feb 11 2100", dateString)
    }

    @Test
    fun formatDateRange_returnsCorrectRangeString_whenStartAndEndDatesDifferentYears() {
        val start = getCalendar(year = 2100)
        val end = getCalendar(year = 2101)

        val dateString = DatePresentationUtils.formatDateRange(start, end)

        Assert.assertEquals("Feb 25 2100 - Feb 25 2101", dateString)
    }

    private fun getCalendar(
        year: Int = 2020,
        month: Int = Calendar.FEBRUARY,
        dayOfMonth: Int = 25,
        hourOfDay: Int = 15,
        minute: Int = 40,
        second: Int = 0,
        millis: Int = 0
    ): Calendar {
        val c = Calendar.getInstance()
        c.set(Calendar.YEAR, year)
        c.set(Calendar.MONTH, month)
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        c.set(Calendar.HOUR_OF_DAY, hourOfDay)
        c.set(Calendar.MINUTE, minute)
        c.set(Calendar.SECOND, second)
        c.set(Calendar.MILLISECOND, millis)
        return c
    }

}