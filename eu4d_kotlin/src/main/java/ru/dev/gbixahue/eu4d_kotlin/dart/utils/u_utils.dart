import 'dart:async';
import 'dart:math';

import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:love_game/src/commons/utils/exp_utils.dart';
import 'package:recase/recase.dart';

final int UNDEFINED = -1;

Future<T> emptyFuture<T>() => Future.delayed(Duration(seconds: 0));

int randomRange(int min, int max) => min + Random().nextInt(max - min);

Locale getLocale(BuildContext context) => Localizations.localeOf(context);

String getLocaleCountryCode(BuildContext context) => getLocale(context).countryCode;

String getLocaleLanguageCode(BuildContext context) => getLocale(context).languageCode;

class HexColor extends Color {
  HexColor(final String hexColor) : super(_getColorFromHex(hexColor));

  static int _getColorFromHex(String hexColor) {
    hexColor = hexColor.toUpperCase().replaceAll("#", "");
    if (hexColor.length == 6) {
      hexColor = "FF" + hexColor;
    }
    return int.parse(hexColor, radix: 16);
  }
}

//	< 0 	if [a] is smaller than [b],
//	= 0 	if [a] is equal to 		 [b],
//  > 0 	if [a] is greater than [b].
class Range<T> {
  final T start;
  final T stop;
  final Comparator<T> comparator;

  Range(this.start, this.stop, this.comparator);

  bool inRange(T value) {
    if (comparator(value, start) < 0 || comparator(value, stop) > 0) return false;

    return true;
  }

  static Comparator<int> get intComparator => (int a, int b) => a.compareTo(b);

  @override
  String toString() => "${stringOf(start)}-${stringOf(stop)}";
}

String enumToString(Object entry) => ReCase(describeEnum(entry)).camelCase;

String toEnumString(String entry) => ReCase(entry).constantCase;
