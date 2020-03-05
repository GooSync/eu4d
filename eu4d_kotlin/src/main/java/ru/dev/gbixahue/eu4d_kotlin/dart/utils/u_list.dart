import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:love_game/src/commons/utils/u_of.dart';

class UList {
  static List<String> toStringOfList(List list) => list == null ? [] : list.map((item) => stringOf(item)).toList();

  static List<Timestamp> asTimestampList(List list) => list == null ? [] : list.map((item) => item as Timestamp).toList();

  static List<T> removeNull<T>(List list) => list == null ? [] : list.where((item) => item != null).toList();

  static T findTheBest<T>(List<T> list, T Function(T, T) whoTheBest, {T defBest}) {
    if (list == null || list.isEmpty) return defBest;

    T bestItem = list[0];
    list.forEach((item) => bestItem = whoTheBest(bestItem, item));
    return bestItem;
  }

  static foreachIndexed<E>(Iterable<E> iterator, Function(int, E) func) {
  	if (iterator == null) return;

    for (int i = 0; i < iterator.length; i++) func(i, iterator.elementAt(i));
  }

  static moveToEnd<T>(List<T> collection, bool predicate(T)) {
  	if (collection == null) return;

    List<T> predicateList = collection.where((item) => predicate(item)).toList();
    predicateList.forEach((item) => collection.remove(item));
    collection.addAll(predicateList);
  }
}
