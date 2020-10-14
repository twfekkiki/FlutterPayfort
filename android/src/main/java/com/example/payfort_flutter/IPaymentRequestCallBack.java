package com.example.payfort_flutter;

import java.util.Map;

/**
 * Created by mostafa_anter on 7/6/17.
 */

public interface IPaymentRequestCallBack {
    void onPaymentRequestResponse(int responseType, Map<String, Object> responseData);
}
