import 'dart:async';
import 'dart:io';

import 'package:connectivity/connectivity.dart';

class NetworkState {
  ConnectivityResult _result = ConnectivityResult.none;
  StreamSubscription<ConnectivityResult> _subscription;
  bool _hasNetworkResponse = false;

  startListen() {
    _subscription = Connectivity().onConnectivityChanged.listen((result) async {
      _result = result;
      await _checkInternetAddresses();
    });
  }

  stopListening() {
    _subscription.cancel();
  }

  bool hasConnection() => _hasNetworkResponse || _result != ConnectivityResult.none;

  checkConnection({Function onSuccess, Function onFail}) async {
    ConnectivityResult result = await Connectivity().checkConnectivity();
    await _checkInternetAddresses();
    if (_hasNetworkResponse || result != ConnectivityResult.none)
      onSuccess?.call();
    else
      onFail?.call();
  }

  _checkInternetAddresses() async {
    try {
      final result = await InternetAddress.lookup('google.com');
      if (result.isNotEmpty && result[0].rawAddress.isNotEmpty) {
        _hasNetworkResponse = true;
      }
    } catch (_) {
      _hasNetworkResponse = false;
    }
  }
}
