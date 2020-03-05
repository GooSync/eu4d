package ru.dev.gbixahue.eu4d_android.date

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Anton Zhilenkov on 06.03.2018.
 */
val simpleDateFormatMap = HashMap<String, ThreadLocal<SimpleDateFormat>>()

private fun getFormatter(pattern: String, locale: Locale? = null): ThreadLocal<SimpleDateFormat> {
  var threadLocal: ThreadLocal<SimpleDateFormat>? = simpleDateFormatMap[pattern]
  if (threadLocal == null) {
    threadLocal = object : ThreadLocal<SimpleDateFormat>() {
      override fun initialValue(): SimpleDateFormat {
        return if (locale == null) SimpleDateFormat(pattern) else SimpleDateFormat(pattern, locale)
      }
    }
    simpleDateFormatMap.put(pattern, threadLocal)
  }
  return threadLocal
}

fun releaseDateFormatters() {
  simpleDateFormatMap.clear()
}

fun convertDateToTimeWithGMToffset(stringDate: String, patternOfDate: String, offset: Long = getGMToffset(), locale: Locale? = null): Long {
  val dateFormat = getFormatter(patternOfDate, locale).get() ?: return -1

  val calendar = Calendar.getInstance()
  return try {
    calendar.timeInMillis = dateFormat.parse(stringDate).time + offset
    calendar.timeInMillis
  } catch (e: Exception) {
    calendar.timeInMillis = System.currentTimeMillis()
    calendar.timeInMillis
  }

}

fun convertDateBetweenPatterns(
  date: String,
  patternOfDate: String,
  toPattern: String,
  default: String = date,
  locale: Locale? = null
): String {
  return try {
    getFormatter(
      toPattern,
      locale
    ).get()?.format(getFormatter(patternOfDate, locale).get()?.parse(date)) ?: default
  } catch (e: Exception) {
    return default
  }
}

fun convertTimeToDate(time: Long, pattern: String, locale: Locale? = null): String {
  return getFormatter(pattern, locale).get()?.format(time) ?: ""
}

fun convertDateToTime(stringDate: String, patternOfDate: String, default: Long = -1, locale: Locale? = null): Long {
  return try {
    getFormatter(patternOfDate, locale).get()?.parse(stringDate)?.time ?: default
  } catch (e: Exception) {
    default
  }
}

fun resetToUTC(time: Long): Long {
  val tz = TimeZone.getTimeZone("UTC")
  val format = SimpleDateFormat.getInstance()
  format.timeZone = tz
  return try {
    format.parse(format.format(Date(time))).time
  } catch (e: Exception) {
    time
  }
}

fun getGMToffset(): Long {
  return Calendar.getInstance().timeZone.getOffset(System.currentTimeMillis()).toLong()
}

fun incrementTime(type: Int, fromTime: Long, incrementValue: Int): Long {
  val calendar = Calendar.getInstance()
  calendar.timeInMillis = fromTime
  calendar.add(type, incrementValue)
  return calendar.timeInMillis
}

fun getTimeDiff(type: Int, fromTime: Long, toTime: Long = System.currentTimeMillis()): Int {
  val calendarFrom = Calendar.getInstance()
  calendarFrom.timeInMillis = fromTime
  val calendarTo = Calendar.getInstance()
  calendarTo.timeInMillis = toTime
  return calendarTo.get(type) - calendarFrom.get(type)
}