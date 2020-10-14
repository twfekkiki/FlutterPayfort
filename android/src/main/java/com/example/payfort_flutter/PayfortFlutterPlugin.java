package com.example.payfort_flutter;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;

import io.flutter.Log;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;
import io.flutter.embedding.engine.FlutterEngine;


/**
 * PayfortFlutterPlugin
 */
public class PayfortFlutterPlugin implements FlutterPlugin, MethodCallHandler, ActivityAware {

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    private MethodChannel channel;

    private PayFortDelegate delegate;

    private ActivityPluginBinding activityPluginBinding;


    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
        channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "payfort_flutter");
        channel.setMethodCallHandler(this);
//    PayFortDelegate delegate = plugin.setupActivity(registrar.activity());
//    registrar.addActivityResultListener(delegate);
    }

    public static void registerWith(Registrar registrar) {


        Log.w("Call", "register");
        PayfortFlutterPlugin plugin = new PayfortFlutterPlugin();
        final MethodChannel channel = new MethodChannel(registrar.messenger(), "payfort_flutter");
        channel.setMethodCallHandler(plugin);
        PayFortDelegate delegate = plugin.setupActivity(registrar.activity());
        registrar.addActivityResultListener(delegate);
    }
//
//  public static void registerWith(Registrar registrar) {
//    PayfortFlutterPlugin plugin = new PayfortFlutterPlugin();
//
//    plugin.setupEngine(registrar.messenger());
//    PayFortDelegate delegate = plugin.setupActivity(registrar.activity());
//    registrar.addActivityResultListener(delegate);
//  }


    private void setupEngine(BinaryMessenger messenger) {
        MethodChannel channel = new MethodChannel(messenger, "payfort_flutter");
        channel.setMethodCallHandler(this);
    }

    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
        if (call.method.equals("getPlatformVersion")) {
            System.out.print("tawfik register");
            result.success("Android " + android.os.Build.VERSION.RELEASE);
        } else if (call.method.equals("initialize")) {
            Log.w("Call", "initialize");
          delegate.initFortSdk();
          System.out.println(call.arguments.toString());
          Config.setAccessCode((String) call.argument("accessCode"));
          Config.setMerchantIdentifier((String) call.argument("merchantIdentifier"));
          Config.setShaRequestPhrase((String) call.argument("shaRequestPhrase"));
          Config.setLanguageType((String) call.argument("languageType"));
          Config.setEnvironment((String) call.argument("environment"));
            result.success("");
        } else if (call.method.equals("request")) {
            delegate.requestForPayfortPayment(call, result);
        } else {
            result.notImplemented();
        }
    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
        channel.setMethodCallHandler(null);
    }


    public PayFortDelegate setupActivity(Activity activity) {
        delegate = new PayFortDelegate(activity);
        return delegate;
    }

    @Override
    public void onAttachedToActivity(@NonNull ActivityPluginBinding activityPluginBinding) {
        setupActivity(activityPluginBinding.getActivity());
        this.activityPluginBinding = activityPluginBinding;
        activityPluginBinding.addActivityResultListener(delegate);
    }

    @Override
    public void onDetachedFromActivityForConfigChanges() {
        onDetachedFromActivity();
    }

    @Override
    public void onReattachedToActivityForConfigChanges(@NonNull ActivityPluginBinding binding) {
        onAttachedToActivity(activityPluginBinding);
    }

    @Override
    public void onDetachedFromActivity() {
        activityPluginBinding.removeActivityResultListener(delegate);
        activityPluginBinding = null;
        delegate = null;

    }
}
