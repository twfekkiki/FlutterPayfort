package com.example.payfort_flutter;

public class Config {
    public static String ENVIRONMENT="https://sbpaymentservices.payfort.com/FortAPI/paymentApi";
    public static String MERCHANT_IDENTIFIER = "VZYfCwhg";
    public static String ACCESS_CODE = "qeoqGtJrSmvtd8xZ3UBC";
    public static String SHA_REQUEST_PHRASE = "TESTSHAIN";
    public static String LANGUAGE_TYPE = "en";//Arabic - ar //English - en
    public static void setMerchantIdentifier(String merchantIdentifier) {
        MERCHANT_IDENTIFIER = merchantIdentifier;
    }
    public static void setAccessCode(String accessCode) {
        ACCESS_CODE = accessCode;
    }
    public static void setShaRequestPhrase(String shaRequestPhrase) {
        SHA_REQUEST_PHRASE = shaRequestPhrase;
    }
    public static void setLanguageType(String languageType) {
        LANGUAGE_TYPE = languageType;
    }

    public static void setEnvironment(String Environment) {
        Config.ENVIRONMENT = Environment;
    }
}
