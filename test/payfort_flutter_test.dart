import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:payfort_flutter/payfort_flutter.dart';

void main() {
  const MethodChannel channel = MethodChannel('payfort_flutter');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await PayfortFlutter.platformVersion, '42');
  });
}
