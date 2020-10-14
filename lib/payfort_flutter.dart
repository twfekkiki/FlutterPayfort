
import 'dart:async';

import 'package:flutter/cupertino.dart';
import 'package:flutter/services.dart';
import 'package:payfort_flutter/PayFortResponse.dart';


enum PayfortEnvironments{
  TEST,LIVE
}
class PayfortFlutter {

  static const MethodChannel _channel =
      const MethodChannel('payfort_flutter');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }


  static Future<dynamic> initialize({@required String accessCode,@required String merchantIdentifier,@required String shaRequestPhrase,@required String languageType ,PayfortEnvironments environment }) async {
    assert(accessCode != null);
    assert(merchantIdentifier != null);
    assert(shaRequestPhrase != null);
    assert(languageType != null);
    assert(environment != null);

    final arguments = <String, dynamic>{
      "accessCode":accessCode,
      "merchantIdentifier":merchantIdentifier,
      "shaRequestPhrase":shaRequestPhrase,
      "languageType":languageType,
      "environment":environment == PayfortEnvironments.TEST?'TEST':"LIVE"
    };

    final dynamic result = await _channel.invokeMethod("initialize",arguments);
  }
  static Future<PayfortResponse> request(
      {@required String accessCode,
        @required String merchantReference,
        @required String customerEmail,
        @required String command,
        @required String currency,
        @required String amount,
        @required String languageType}) async {
    final arguments = <String, dynamic>{
      "accessCode":accessCode,
      "language":languageType,
      "merchantReference":merchantReference,
      "customerEmail":customerEmail,
      "currency":currency,
      "command":command,
      "amount":amount
    };
    final dynamic result = await _channel.invokeMethod("request",arguments);
    if(result == null) return null;
    else{
      return PayfortResponse.fromJson(Map.from(result));
    }
  }


}
