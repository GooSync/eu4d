import 'package:love_game/src/commons/utils/u_of.dart';

class UMap {
  static Map<String, dynamic> keyStringOf(Map<dynamic, dynamic> source) {
    if (source == null || source.isEmpty) return {};

    return source.map((key, value) => MapEntry(stringOf(key), value));
  }

  static Map<dynamic, String> valueStringOf(Map<dynamic, dynamic> source) {
    if (source == null || source.isEmpty) return {};

    return source.map((key, value) => MapEntry(key, stringOf(value)));
  }

  static Map<String, String> mapStringOf(Map<dynamic, dynamic> source) {
    if (source == null || source.isEmpty) return {};

    return source.map((key, value) => MapEntry(stringOf(key), stringOf(value)));
  }

  static Map<String, int> keyStringOfValueIntOf(Map<dynamic, dynamic> source) {
    Map<String, int> map = {};
    if (source == null) return map;

    source.forEach((key, value) {
      String sKey = stringOf(key);
      int iValue = intOf(value);
      if (sKey != null && iValue != null) map[sKey] = iValue;
    });
    return map;
  }

  static List<T> toList<T, K, V>(Map<K, V> map, T Function(K key, V value) converter) {
    final list = List<T>();
    map.forEach((key, value) => list.add(converter(key, value)));
    return list;
  }
}
