import 'package:logging/logging.dart';
import 'package:love_game/src/commons/utils/exp_utils.dart';

final Logger _logger = new Logger('MyClassName');

logD(dynamic from, dynamic message) {
  String fromStr = stringOf(from);
  _logger.log(Level.FINE, "[${_clearFrom(fromStr)}]: $message");
}

logE(dynamic from, dynamic message) {
  String fromStr = stringOf(from);
  _logger.log(Level.SEVERE, "[${_clearFrom(fromStr)}]: $message");
}

String _clearFrom(String from) => from.replaceAll(" ", "").replaceAll("Instanceof", "").replaceAll("'", "");
