package com.example.payfort_flutter;

import android.app.Activity;
import android.content.Intent;

import com.payfort.fort.android.sdk.base.callbacks.FortCallBackManager;
import com.payfort.fort.android.sdk.base.callbacks.FortCallback;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;

public class PayFortDelegate implements PluginRegistry.ActivityResultListener, IPaymentRequestCallBack {
    private final Activity activity;
    private MethodChannel.Result pendingResult;
    public static final int RESPONSE_PURCHASE_CANCEL = 333;
    public static final int RESPONSE_PURCHASE_SUCCESS = 444;
    public static final int RESPONSE_PURCHASE_FAILURE = 555;


    /*private  final  PayFortData payFortData;*/


    public PayFortDelegate(Activity activity/*, PayFortData payFortData*/) {
        this.activity = activity;
        /*this.payFortData = payFortData;*/
    }

    private FortCallBackManager fortCallback = null;

    public void initFortSdk() {
        fortCallback = FortCallback.Factory.create();

    }

    @Override
    public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
        fortCallback.onActivityResult(requestCode, resultCode, data);
        return true;
    }

    public void requestForPayfortPayment(MethodCall call, MethodChannel.Result result) {


        PayFortData payFortData = new PayFortData();
        payFortData.accessCode = call.argument("accessCode");
        //payFortData.merchantIdentifier = call.argument("merchantIdentifier");
        payFortData.amount = call.argument("amount"); //String.valueOf((int) (Float.parseFloat(etAmount.getText().toString()) * 100));// Multiplying with 100, bcz amount should not be in decimal format
        payFortData.command = call.argument("command");
        payFortData.currency = call.argument("currency");
        payFortData.customerEmail = call.argument("customerEmail");
        payFortData.language = call.argument("language");
        payFortData.merchantReference = call.argument("merchantReference");
        //payFortData.fortId = call.argument("fortId");
        pendingResult = result;

        PayFortPayment payFortPayment = new PayFortPayment(activity, this.fortCallback, this);
        payFortPayment.requestForPayment(payFortData);
    }

    private void clearMethodCallAndResult() {
        pendingResult = null;
    }
    private void finishWithSuccess(Map<String, Object> data) {
        if (pendingResult != null) {
            pendingResult.success(data);
            clearMethodCallAndResult();
        }
    }

    private void finishWithError(String errorCode, String errorMessage, Throwable throwable) {
        if (pendingResult != null) {
            pendingResult.error(errorCode, errorMessage, throwable);
            clearMethodCallAndResult();
        }
    }

    private  HashMap<String , Object> convertToMap(PayFortData data)
    {
        HashMap<String, Object> map = new HashMap<>();
        for (Field field : data.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try { map.put(field.getName(), field.get(data)); } catch (Exception e) { }
        }
        return map;
    }
    @Override
    public void onPaymentRequestResponse(int responseType, Map<String, Object> responseData) {

        if(responseType == RESPONSE_PURCHASE_CANCEL || responseType == RESPONSE_PURCHASE_FAILURE)
        {
            finishWithSuccess(responseData);
            //finishWithError("payfortError" , "fail" , responseData);
        }
        else {
            finishWithSuccess(responseData);
        }
    }
}
