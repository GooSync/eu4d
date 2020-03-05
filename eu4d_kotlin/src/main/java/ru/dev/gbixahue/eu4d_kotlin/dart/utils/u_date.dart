import 'package:intl/intl.dart';
import 'package:love_game/src/commons/utils/exp_utils.dart';

class UDate {
  static String hoursToString(DateTime dateTime, [String languageCode]) => DateFormat.jm(languageCode).format(dateTime);

  static bool isTheSameDayOfYear(int a, int b) {
    return dayOfYear(a).inDays == dayOfYear(b).inDays;
  }

  static Duration dayOfYear(int fromMilliseconds) {
    final date = DateTime.fromMillisecondsSinceEpoch(fromMilliseconds);
    final diff = date.difference(DateTime(date.year, 1, 0, 0, 0));
    return diff;
  }

  static Duration getDiff(int from, int to) => DateTime.fromMillisecondsSinceEpoch(from).difference(DateTime.fromMillisecondsSinceEpoch(to));

  static Duration getDiffUntilNextDay(int timeStampMs) {
    int tsBeginOfNextDay = add(timeStampMs, Duration(days: 1));
    tsBeginOfNextDay = UDate.resetToXhXsXms(tsBeginOfNextDay);
    return Duration(milliseconds: tsBeginOfNextDay - timeStampMs);
  }

  static int add(int timeStampMilliseconds, Duration duration) {
    return timeStampMilliseconds + duration.inMilliseconds;
  }

  static int tsNowMilliseconds() => DateTime.now().toUtc().millisecondsSinceEpoch;

  static int tsNowSeconds() => tsNowMilliseconds() ~/ 1000;

  static int tsSecondsToMilliseconds(int timeSeconds) {
    String sTime = stringOf(timeSeconds);
    int timeMilliseconds = timeSeconds;
    if (sTime.length == 10) timeMilliseconds *= 1000;
    if (stringOf(timeMilliseconds).length != 13) logE("timeStampSecondsToMilliseconds", "Can't convert timeStamp from seconds to milliseconds");

    return timeMilliseconds;
  }

  static int resetToXhXsXms(int timeStampMilliseconds, [int x = 0]) {
    final date = DateTime.fromMillisecondsSinceEpoch(timeStampMilliseconds);
    return DateTime(date.year, date.month, date.day, x, x, x, x, x).millisecondsSinceEpoch;
  }

  static DateRange range(int from, int to) => DateRange._(fromTimeStamp: from, toTimeStamp: to);
}

class UDateNow {
  static String weekDayToString(int weekDay, [String languageCode]) {
    final fd = DateFormat("EEE", languageCode);
    DateTime dt = DateTime.now();
    if (weekDay == dt.weekday) return fd.format(dt);

    for (int i = 1; i < 8; i++) {
      dt = dt.add(Duration(days: 1));
      if (weekDay == dt.weekday) break;
    }
    return fd.format(dt);
  }

  static String dayAndMonthToString(int day, [String languageCode]) {
    final fd = DateFormat("d.M", languageCode);
    DateTime dtNow = DateTime.now();
    DateTime dtReady = DateTime(dtNow.year, dtNow.month, day);
    return fd.format(dtReady);
  }
}

class DateRange {
  final int fromTimeStamp; //ms
  final int toTimeStamp; //ms

  DateRange._({this.fromTimeStamp, this.toTimeStamp});

  bool inRange(int sourceTime) => fromTimeStamp < sourceTime && toTimeStamp > sourceTime;

  bool isFirstDay(int sourceTime) => _getDiff(sourceTime, fromTimeStamp).inDays == 0;

  bool isLastDay(int sourceTime) => _getDiff(sourceTime, toTimeStamp).inDays == 0;

  Duration diff() => _getDiff(fromTimeStamp, toTimeStamp);

  Duration _getDiff(int from, int to) => DateTime.fromMillisecondsSinceEpoch(from).difference(DateTime.fromMillisecondsSinceEpoch(to));
}
