package com.authorizationserver.contants.enums;

public enum ErrorCode {
    /**
     * General validation
     */
    MISSING_REQUEST("0001", "Request is missing"), //
    INVALID_REQUEST("0002", "Request is invalid"), //
    MISSING_PARAMETER("0003", "Parameter cannot be null"), //
    INVALID_PARAMETER_FORMAT("0004", "Invalid format for parameter"), //
    INVALID_API_VERSION("0005", "Invalid API version"), //
    INVALID_SIGNATURE("0006", "Invalid Signature"), //
    SIGNATURE_MISSING("0007", "Signature missing"), //

    /**
     * Accounting relate
     */
    INVALID_ACCOUNT("0101", "Invalid account"), //
    FEE_OVER_AMOUNT("0102", "Fee must paid over amount."), //
    MAXIMUM_BALANCE_LIMIT("0103", "Maximum balance limit is reach"), //
    MAXIMUM_DAILY_LIMIT("0104", "Maximum daily limit"), //
    MAXIMUM_DAILY_VOLUME("0105", "Maximum daily volume limit"), //
    NOT_ENOUGH_BALANCE("0106", "Account not enough balance"), //
    MAXIMUM_PER_TRANS_LIMIT("0107", "Maximum per transaction limit is reach"), //
    DEPOSIT_NOT_FOUND("0108", "Cannot find deposit"), //
    DEPOSIT_STATUS_INVALID("0109", "Deposit has invalid status"), //
    WRONG_CURRENCY("0110", "Invalid currency."), //

    /**
     * Payer
     */
    PAYERINFO_MISSING_OR_INVALID("0201", "Payer is missing or invalid"), //
    PAYER_COUNTRY_MISSING_OR_INVALID("0202", "Payer's country is missing or invalid"), //
    PAYER_COUNTRY_INVALID_OR_NOTSUPPORT("0203", "Payer's country is invalid or not support"), //
    PAYER_EMAIL_MISSING_OR_INVALID("0204", "Payer's email is missing or invalid"), //
    PAYER_FNAME_MISSING("0205", "Payer's first name is missing"), //
    PAYER_LASTNAME_MISSING("0206", "Payer's last name is missing"), //
    PAYER_MOBILE_MISSING("0207", "Payer's mobile is missing"), //
    PAYER_MOBILE_INVALID("0208", "Payer's mobile is invalid"), //
    PAYER_ADDRESS_INVALID("0209", "Payer's address is invalid"), //
    PAYER_LASTNAME_INVALID("0211", "Payer's last name is invalid"),//
    PAYER_FNAME_INVALID("0210", "Payer's first name is invalid"),//
    /**
     * Merchant
     */
    MERCHANT_NOT_ENROLL_ECOM("0300", "Merchant not yet enroll ecommerce"), //
    MERCHANT_SETTING_INVALID("0301", "Merchant not yet set ecommerce setting"), //
    MERCHANT_INFO_MISSING("0302", "Merchant info is missing"), //
    WRONG_MERCHANT_INFO("0303", "Merchant info is invalid."), //
    MERCHANT_STATUS_INVALID("0304", "Merchant account not verified yet."), //
    MERCHANT_SECRET_KEY_INFO_MISSING("0305", "Merchant secret key is not configured"), //
    /**
     * Order
     */
    ORDER_NOT_FOUND("0400", "Order not found"), //
    ORDER_INFO_MISSING("0401", "Order info is missing"), //
    ORDER_INFO_AMOUNT_MISSING("0402", "Order info: Amount is missing"), //
    ORDER_INFO_CURRENCY_MISSING("0403", "Order info: Currency is missing"), //
    ORDER_INFO_ORDER_ID_MISSING("0404", "Order info: Order Id is missing"), //
    ORDER_INFO_ITEM_NAME_MISSING("0405", "Order info: Item name is missing"), //
    ORDER_INFO_ITEM_PRICE_MISSING("0406", "Order info: Item price is missing"), //
    ORDER_INFO_QUANTITY_MISSING("0407", "Order info: Quantity is missing"), //
    ORDER_BEING_PROCESS_BY_OTHER("0408", "Order already processing by other"), //
    INVALID_ORDER("0409", "Order not valid"), //
    ORDER_STATUS_INVALID("0410", "Order has invalid status for payment"), //
    ORDER_INFO_ORDER_ID_NOT_UNIQUE("0414", "Order Info: Order Id is not unique"), //
    ORDER_REFUND_AMOUNT_GREATER("0411", "Total amount refund is greater original amount"), //
    ORDER_EXPIRED("0412", "The order was expired"), //
    ORDER_AVAILABLE_TIME("0413", "Available time at least 10s"), //

    /**
     * Tariff and payment option
     */
    PAYMENT_OPTION_MISSING("0500", "Payment Option is missing"), //
    NO_TARIFF_FOUND("0501", "No tariff found for ecom"), //
    NO_PAYMNET_PARTNER_FOUND("0502", "No Payment Partner found."), //
    PAYMENT_OPTION_INVALID("0503", "Payment Option is invalid"), //

    /**
     * CAPTURE,REFUND,VOID
     */
    AMOUNT_MISSING_OR_INVALID("0600", "Amount is missing or invalid."), //
    PARTNER_STATUS_MISSING_OR_INVALID("0608", "Partner status is missing or invalid."), //
    CURRENCY_MISSING_OR_INVALID("0601", "Currency is missing or invalid."), //
    STATEMENT_MISSING_OR_INVALID("0602", "Statement is missing or invalid."), //
    PAYMODE_MISSING_OR_INVALID("0603", "Order process with partner not support"), //
    AMOUNT_REMAIN_NOTENOUGH("0604", "Amount remain not enough."), //
    REFUND_OVER_CAPTURED("0605", "Refund cannot be over captured Amount"), //
    AMOUNT_REFUND_OVER_AUTH("0606", "Amount refund over order amount"), //
    AMOUNT_CAPTURE_OVER_AUTH("0607", "Amount capture over order amount"), //
    /**
     * Real time bank
     */
    MISSING_OR_INVALID_PARTNER_TXN("0701", "Invalid or missing partner transaction"), //
    MISSING_OR_INVALID_REFERENCE_TXN("0702", "Invalid or missing reference"),
    /**
     * Card Info validation
     */
    CARD_EXPIRED("0800", "Card is expired"), //
    CARD_PAN_INVALID("0801", "Card number is invalid"), //
    CARD_CSC_INVALID("0802", "Card secure code is invalid"), //
    CARD_MPI_VER_TYPE_INVALID("0803", "ver_type paramater is invalid"), //
    CARD_MPI_VER_TOKEN_INVALID("0804", "ver_token paramater is invalid"), //
    CARD_MPI_DS_XID_INVALID("0805", "three_ds_xid paramater is invalid"), //
    CARD_MPI_DS_ECI_INVALID("0806", "three_ds_eci paramater is invalid"), //
    CARD_MPI_DS_ENROLL_INVALID("0807", "three_ds_eci paramater is invalid"), //
    CARD_MPI_DS_STATUS_INVALID("0808", "three_ds_status paramater is invalid"), //
    CARDHOLDER_NAME_INVALID("0809", "Cardholder name is invalid"), //
    CARD_EXP_MONTH_INVALID("0810", "Card expire month is invalid"),//
    CARD_EXP_YEAR_INVALID("0811", "Card expire year is invalid"),//

    CARD_INVALID("0812", "Card invalid."), //
    CARD_TOKEN_REQUIRED("0813", "Token required."), //
    CARD_TOKEN_UNSUPPORTED_ALGORITHM("0814", "Unsupported algorithm"), //
    CARD_TOKEN_INVALID("0815", "Invalid Card Token"), //
    CARD_TOKEN_CANT_GENERATE_KEY("0816", "Can not generate key"), //
    CARD_TOKEN_CANT_PROCESS_RSA("0817", "Can not process RSA"), //
    /**
     * System error
     */
    INVALID_CREDENTIALS("9001", "Invalid credentials"), //
    FORBIDDEN_ACCESS("9002", "Access not allowed"), //
    USER_NOT_FOUND("9003", "User does not exist"), //
    IP_NOT_AUTHORIZE("9004", "IP is not authorize"), //
    TOO_MANY_DUPLICATE("9005", "Too many duplicate to generate unique key."), //
    PARTNER_ERROR("9006", "Partner payment error."), //
    UNKNOWN_ERROR("9999", "Unknown error."), //
    ;
    private String message;
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private ErrorCode(String code, String message) {
        this.message = message;
        this.code = code;
    }

}
