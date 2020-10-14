import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:payfort_flutter/PayFortSdk.dart';
void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String _platformVersion = 'Unknown';

  @override
  void initState() {
    super.initState();
    initPlatformState();
  }

  // Platform messages are asynchronous, so we initialize in an async method.
  Future<void> initPlatformState() async {
    String platformVersion;
    // Platform messages may fail, so we use a try/catch PlatformException.
    try {
      platformVersion = await PayfortFlutter.platformVersion;
    } on PlatformException {
      platformVersion = 'Failed to get platform version.';
    }

    // If the widget was removed from the tree while the asynchronous platform
    // message was in flight, we want to discard the reply rather than calling
    // setState to update our non-existent appearance.
    if (!mounted) return;

    setState(() {
      _platformVersion = platformVersion;
    });
  }

  String _accessCode = 'CITvfqU60Yq1mDpBGtHd';
  String _merchantIdentifier='10235cb6';
  String language = 'ar';
  String deviceId = '';
  String phraseKey = '\$2y\$10\$orbzTowtU';

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: InkWell(
          onTap: ()async {
            await PayfortFlutter.initialize(accessCode: _accessCode, merchantIdentifier: _merchantIdentifier, shaRequestPhrase: phraseKey, languageType: language,environment: PayfortEnvironments.TEST);
            PayfortResponse val = await PayfortFlutter.request(accessCode: _accessCode, merchantReference: DateTime.now().millisecondsSinceEpoch.toString(), customerEmail: "test@joinly.com",
                command: "AUTHORIZATION", currency: "AED", amount: "1000", languageType: language);

            print("the end");
            print(val.toJson());
          },
          child: Center(
            child: Text('Running on: $_platformVersion\n'),
          ),
        ),
      ),
    );
  }
}
