// To parse this JSON data, do
//
//     final payfortResponse = payfortResponseFromJson(jsonString);

import 'dart:convert';

PayfortResponse payfortResponseFromJson(String str) => PayfortResponse.fromJson(json.decode(str));

String payfortResponseToJson(PayfortResponse data) => json.encode(data.toJson());

class PayfortResponse {
  PayfortResponse({
    this.amount,
    this.responseCode,
    this.cardHolderName,
    this.paymentOption,
    this.customerIp,
    this.eci,
    this.language,
    this.command,
    this.responseMessage,
    this.sdkToken,
    this.merchantReference,
    this.customerEmail,
    this.currency,
    this.status,
    this.deviceId,
    this.serviceCommand,
    this.signature,
    this.merchantIdentifier,
    this.cardNumber,
    this.fortId,
    this.expiryDate,
    this.authorizationCode,
  });

  String amount;
  String responseCode;
  String cardHolderName;
  String paymentOption;
  String customerIp;
  String eci;
  String language;
  String command;
  String responseMessage;
  String sdkToken;
  String merchantReference;
  String customerEmail;
  String currency;
  String status;
  String deviceId;
  String serviceCommand;
  String signature;
  String merchantIdentifier;
  String cardNumber;
  String fortId;
  String expiryDate;
  String authorizationCode;

  factory PayfortResponse.fromJson(Map<String, dynamic> json) => PayfortResponse(
    amount: json["amount"] == null ? null : json["amount"],
    responseCode: json["response_code"] == null ? null : json["response_code"],
    cardHolderName: json["card_holder_name"] == null ? null : json["card_holder_name"],
    paymentOption: json["payment_option"] == null ? null : json["payment_option"],
    customerIp: json["customer_ip"] == null ? null : json["customer_ip"],
    eci: json["eci"] == null ? null : json["eci"],
    language: json["language"] == null ? null : json["language"],
    command: json["command"] == null ? null : json["command"],
    responseMessage: json["response_message"] == null ? null : json["response_message"],
    sdkToken: json["sdk_token"] == null ? null : json["sdk_token"],
    merchantReference: json["merchant_reference"] == null ? null : json["merchant_reference"],
    customerEmail: json["customer_email"] == null ? null : json["customer_email"],
    currency: json["currency"] == null ? null : json["currency"],
    status: json["status"] == null ? null : json["status"],
    deviceId: json["device_id"] == null ? null : json["device_id"],
    serviceCommand: json["service_command"] == null ? null : json["service_command"],
    signature: json["signature"] == null ? null : json["signature"],
    merchantIdentifier: json["merchant_identifier"] == null ? null : json["merchant_identifier"],
    cardNumber: json["card_number"] == null ? null : json["card_number"],
    fortId: json["fort_id"] == null ? null : json["fort_id"],
    expiryDate: json["expiry_date"] == null ? null : json["expiry_date"],
    authorizationCode: json["authorization_code"] == null ? null : json["authorization_code"],
  );

  Map<String, dynamic> toJson() => {
    "amount": amount == null ? null : amount,
    "response_code": responseCode == null ? null : responseCode,
    "card_holder_name": cardHolderName == null ? null : cardHolderName,
    "payment_option": paymentOption == null ? null : paymentOption,
    "customer_ip": customerIp == null ? null : customerIp,
    "eci": eci == null ? null : eci,
    "language": language == null ? null : language,
    "command": command == null ? null : command,
    "response_message": responseMessage == null ? null : responseMessage,
    "sdk_token": sdkToken == null ? null : sdkToken,
    "merchant_reference": merchantReference == null ? null : merchantReference,
    "customer_email": customerEmail == null ? null : customerEmail,
    "currency": currency == null ? null : currency,
    "status": status == null ? null : status,
    "device_id": deviceId == null ? null : deviceId,
    "service_command": serviceCommand == null ? null : serviceCommand,
    "signature": signature == null ? null : signature,
    "merchant_identifier": merchantIdentifier == null ? null : merchantIdentifier,
    "card_number": cardNumber == null ? null : cardNumber,
    "fort_id": fortId == null ? null : fortId,
    "expiry_date": expiryDate == null ? null : expiryDate,
    "authorization_code": authorizationCode == null ? null : authorizationCode,
  };
}
