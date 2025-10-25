package com.authorizationserver.exception;

public enum ErrorCodeType {
    /**
     * General validation
     */
    MISSING_REQUEST("0001", "Request is missing"), //
    INVALID_REQUEST("0002", "Request is invalid"), //
    MISSING_PARAMETER("0003", "Parameter {0} is required"), //
    INVALID_PARAMETER_FORMAT("0004", "Invalid format for parameter {0}"), //
    INVALID_API_VERSION("0005", "Invalid API version"), //
    INVALID_SIGNATURE("0006", "Invalid Signature"), //
    SIGNATURE_MISSING("0007", "Signature missing"), //
    MIN_MAX_INVALID("0008", "Parameter {0} length must be in range {1} and {2}"),//
    MIN_LENGTH_INVALID("0009", "Parameter {0} must at least {1} length"),//
    MAX_LENGTH_INVALID("0010", "Parameter {0} must below {1} length"),//
    FIELD_COMPARE_ERROR("0011", "Parameter {0} must {1} parameter {2}"),//
    DATA_PERMISSION("0012", "Invalid permission for change data."),//
    STATE_AND_COUNTRY_COMBINE_INVALID("0013", "Country and State combine is invalid."),//
    INVALID_LANG("0014", "Invalid or not support language."),//
    INVALID_FILE_SIZE("0015", "Invalid file size."),
    INVALID_ACTIVE_CODE("0016", "Invalid active code."),
    INVALID_STATUS("0017", "Invalid status."),
    TIMEOUT("0999", "Process has been timeout."),
    INTEGRATION_ERROR("0999", "Integration error"),

    NOT_FOUND_ROOT_USER("0999", "Not Found Root User"),
    USER_ALREADY_LOCKED("0999", "User already locked"),
    USER_ALREADY_UNLOCKED("0999", "User already unlocked"),

    CALL_API_ERROR("5000", "Intigration api error {0}"),
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

    WRONG_EMAIL_LOGIN("0111", "Wrong email"),

    WRONG_PASSWORD_LOGIN("0111", "Wrong password"),

    WRONG_RETYPE_PASSWORD_LOGIN("0111", "Wrong retype password"),

    NOT_MATCH_PASSWORD("0111", "Password does not match."),

    WRONG_SAME_PASSWORD("0112", "New password same as old one"),

    INVALID_EMAIL("0113", "Invalid email"),

    INVALID_OLD_PASSWORD("0114", "Invalid old password"),

    INVALID_NEW_PASSWORD("0115", "Invalid new password"),

    INVALID_RE_PASSWORD("0116", "Invalid retype password"),

    PATTERN_PASSWORD("0117", "Password do not match the pattern. It require a minimum length 8 characters, contain both numeric and alphabet characters and have at least one of special characters: @ # $ % ^, capitalize letter."),

    LAST_PASSWORD("0118", "Password is the same one of the last four passwords has used"),

    CHANGE_PASSWORD_SUCCESS("0119", "You have changed your password successfully"),

    DEPOSIT_EXISTED("0120", "An attempt to process this transaction has already been made. It is under processing/completed. Please raise a new payment request or contact support."),

    API_CREDENTIAL_SETTING_INVALID("0121", "Invalid API setting."),

    /**
     * Payer
     */
    PAYERINFO_MISSING_OR_INVALID("0201", "Payer is missing or invalid"), //

    PAYER_COUNTRY_MISSING_OR_INVALID("0202", "Payer's country is missing or invalid"), //

    PAYER_COUNTRY_INVALID_OR_NOTSUPPORT("0203", "Payer's country is invalid or not supported"), //

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

    MERCHANT_NOT_ALLOW_PERMISSION("0306", "Invalid Permission"), //

    MERCHANT_VCN_AGENT_INFO_INVALID("0307", "Invalid VCN agent information"), //

    MERCHANT_VCN_AGENT_CREDENTIAL_INVALID("0308", "Invalid VCN agent credential"), //

    MERCHANT_VCN_AGENT_CREDENTIAL_BLOCKED("0309", "VCN agent credential is blocked or expired"), //

    MERCHANT_VCN_AGENT_OTP_INVALID("0310", "Invalid OTP"), //

    KYC_NOT_FOUND("0311", "The document is not found."), //

    MERCHANT_VCN_AGENT_BALANCE("0312", "Agent do not have enough balance"), //
    MERCHANT_VCN_AGENT_OTP_EMAIL_INVALID_PERMISSION("0313", "The email do not have permission to receive OTP."), //

    MERCHANT_VCN_AGENT_INFO_MISSING("0314", "VCN agent information is missing."), //
    MERCHANT_NOTIFICATION_URL_INVALID("0315", "The url notification is invalid."), //
    MERCHANT_ACCOUNT_INVALID("0316", "The agent do not have wallet account default. Please contact administrator."), //
    MERCHANT_ACCOUNT_NOT_SUPPORT("0317", "The account with the currency {0} do not exist. Please contact administrator for support."), //
    MERCHANT_VCN_AGENT_CREDENTIAL_NOT_MATCH("0318", "Agent account and credential are not match."), //
    MERCHANT_AUTO_PROVISION_INVALID("0319", "Wrong auto provision service code."), //
    ORDER_DO_NOT_BELONG_TO_MERCHANT("0320", "The order do not belong to the merchant account."), //
    NO_PROVIDER_FOR_MERCHANT("0321", "Do not have provider configure for this merchant and currency"), //
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
    MISSING_RECURRING_TXN("0414", "The recurring txn is missing"), //
    INVALID_RECURRING_TXN("0415", "The recurring txn is invalid"), //
    INVALID_RECURRING_TOKEN("0416", "The recurring card token is invalid"), //
    INVALID_RECURRING_CONFIGURE("0417", "The recurring is not enable"), //
    NOT_SUPPORT_RECURRING("0418", "The recurring is not support by the partner"), //
    INVALID_RECURRING_CARD("0419", "The recurring card is invalid"), //
    /**
     * Tariff and payment option
     */
    PAYMENT_OPTION_MISSING("0500", "Payment Option is missing"), //

    NO_TARIFF_FOUND("0501", "No tariff found for ecom"), //

    NO_PAYMNET_PARTNER_FOUND("0502", "No Payment Partner found."), //

    PAYMENT_OPTION_INVALID("0503", "Payment Option is invalid"), //

    NONE_PRODUCT_CONFIGURE("0504", "The product is not configure for this merchant"),//
    PAYMENT_OPTION_FCMS_NOT_EXIST("0504", "paymentOption.required"),// This account does not have any payment option with Type = FCMS.
    NO_PAYMNET_PARTNER_NOT_MATCH_PAYMENTOPTION("0505", "There is not payment processor available for the selected option"), //

    COMMON_ERROR("0400", ""), //

    /**
     * CAPTURE,REFUND,VOID
     */
    AMOUNT_MISSING_OR_INVALID("0600", "Amount is missing or invalid."), //

    PARTNER_STATUS_MISSING_OR_INVALID("0608", "Partner status is missing or invalid."), //

    CURRENCY_MISSING_OR_INVALID("0601", "Currency is missing or invalid."), //

    STATEMENT_MISSING_OR_INVALID("0602", "Statement is missing or invalid."), //

    PAYMODE_MISSING_OR_INVALID("0603", "Order process with partner not supported"), //

    AMOUNT_REMAIN_NOTENOUGH("0604", "Amount remain not enough."), //

    REFUND_OVER_CAPTURED("0605", "Refund cannot be over captured Amount"), //

    AMOUNT_REFUND_OVER_AUTH("0606", "Amount refund over order amount"), //

    AMOUNT_CAPTURE_OVER_AUTH("0607", "Amount capture over order amount"), //

    MULTI_REQUEST_FAILURE("0609", "Another process working on this order. So we rejected this request."), //

    AMOUNT_CURRENCY_INVALID("0610", "The amount and currency combine is invalid"), //
    AMOUNT_DEPOSIT_INVALID("0611", "The amount deposit {0} must greater request deposit amount {1} and fee amount{2}"), //

    /**
     * Real time bank
     */
    MISSING_OR_INVALID_PARTNER_TXN("0701", "Invalid or missing partner transaction"), //

    MISSING_OR_INVALID_REFERENCE_TXN("0702", "Invalid or missing reference or pnr"),

    BANK_ACCOUNT_NUMBER_EXISTS("0702", "Account Number already exists"),
    BANK_ACCOUNT_NOT_EXISTS("0703", "Bank Account {0} not valid"),

    /**
     * Mobile wallet
     */
    MOBILEWALLET_NUMBER_EXISTS("1000", "Mobile Wallet Number already exists"),

    MOBILEWALLET_REQUIRED_WALLET_NUMBER("1001", "Mobile Wallet required number wallet"),

    MOBILEWALLET_REQUIRED_ACCOUNT_NAME("1002", "Mobile Wallet required account name"),

    MOBILEWALLET_REQUIRED_OPERATOR_NAME("1003", "Mobile Wallet required operator name"),

    EMAIL_EXISTED_IN_SYSTEM("1004", "The email {0} already exist in system"),
    MOBILE_WALLET_NOT_EXISTS("0703", "Mobile wallet {0} not valid"),


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

    CARD_TO_TOPUP_MISSING("0818", "Top-up Card is missing"),//
    CARD_BILLING_TO_DATA_MISSING("0819", "Billing data is missing"),//
    CARD_BILLING_TO_DATA_FIELD_MISSING("0820", "Billing field \"{0}\" is missing or invalid"),//

    /**
     * Withdraw API
     */
    INVALID_WITHDRAW_DEST_REFERENCE("1001", "Empty or invalid value for withdraw destination account"),//
    WITHDRAW_RULE_USER_STATUS_INVALID("1002", "withdraw.rule.user.status"),//
    WITHDRAW_RULE_USER_BALANCE_INVALID("1003", "rules.invalidBalance"),//
    WITHDRAW_BALANCE_FAILURE("1004", "payment.balance.faiure"),// You do not have sufficient balance to complete the transaction.
    WITHDRAW_BALANCE_EXCEEDED_LIMIT("1005", "payment.failure.rule.amountpertrans"),// You have exceeded your limit allowed, per transaction. Please contact support to increase your limit.

    /**
     * VCN Error Code
     */
    VCN_CARD_CLOSED("8001", "Card has been closed"),//

    VCN_CARD_ACTIVATED("8002", "Card has already activated"),//

    VCN_CARD_BLOCKED("8003", "Card has already blocked"),//

    VCN_CARD_CREATE_FAILURE("8004", "Cannot create card: {0}"),//

    VCN_CARD_NOT_ENOUGH_BALANCE("8005", "The card balance is not enough."),//

    VCN_CARD_REQUEST_NOT_FOUND("8006", "The request is not found."),//

    VCN_CARD_REQUEST_INVALID_STATUS("8007", "The status of request is invalid."),//

    VCN_CARD_CREDIT_FAILURE("8008", "Cannot credit fund to card: "),//
    VCN_TRANSFER_MISSING_TO_CARD("8009", "Received card is missing "),//
    VCN_TRANSFER_INVALID_TO_CARD("8010", "Received card is invalid"),//
    VCN_CARD_GENERAL_ERROR("8999", "General Error: "),//

    VCN_CARD_NOT_FOUND("8011", "AgentVcnProvider not found "),//
    VCN_CARD_CHANGE_BLOCK_ERROR("8012", "VirtualCard change status error {0}"),//
    VCN_CARD_NOT_OWNER_CARD("8013", "You are not owner of card {0}, not have right to see card info"),//
    VCN_CARD_GET_TRANSACTION_ERROR("8013", "VirtualCard get transaction history error {0}"),//
    GET_GL_SESSION_ERROR("8014", "Get GloSession error {0}"),//

    /**
     * System error
     */
    INVALID_CREDENTIALS("9001", "Invalid credentials"), //

    FORBIDDEN_ACCESS("9002", "Access not allowed"), //

    USER_NOT_FOUND("9003", "User does not exist"), //
    USER_NOT_FOUND_VIA_EMAIL("9003", "User does not exist with email {0}"), //

    IP_NOT_AUTHORIZE("9004", "IP is not authorize"), //

    TOO_MANY_DUPLICATE("9005", "Too many duplicate to generate unique key."), //

    PARTNER_ERROR("9006", "Partner payment error."), //

    NO_EXCHANGE_RATE_SETUP("9007", "No exchange rate is setup for currency {0}."), //

    PARTNER_NOT_SUPPORT_VOID("9008", "Partner is not support void"), //

    CREDENTIAL_NOT_LINK_WITH_MERCHANT("9009", "No merchant account link with this API"), //

    USER_NOT_ENOUGH_RIGHT("9010", "User does not have right to do this action"), //

    CMS_USER_NOT_FOUND("404", "User does not exist"), //
    /**
     * Card Info validation
     */
    THREEDS_SECURE_RESPONSEURL_ERROR("9010", "responseURL enum : SIMPLE or CUSTOM"), //

    THREEDS_SECURE_CHECK3DS_ERROR("9011", "Threeds have error"), //

    THREEDS_SECURE_ID_INVALID("9012", "Threeds id is invalid"), //

    LIMIT_QUERY_RATE("9013", "Reach to limit inquery for one transaction"),//

    INVALID_SYSTEM_PARAMETER_CONFIG("9014", "Reach to limit inquery for one transaction"),//
    TOKEN_EXPIRED("9015", "Token was expired"),//
    UNKNOWN_ERROR("9999", "Unknown error."), //
    /**
     * Voucher Error Code
     */
    INVALID_CATEGORY("7001", "Invalid voucher category."),//
    OUT_OF_STOCK("7002", "Out of stock: {0}"),//
    SELL_OVER_BUY("7003", "Sell over the number of buying vouchers for category: {0}"),

    PRODUCT_NOT_FOUND("7004", "Product for user email: {0} and type {1} not found"),
    COUNTRY_NOT_FOUND("7005", "Country not exist with code: {0}"),
    CURRENCY_NOT_FOUND("7006", "Currency not exist with code: {0}"),

    INVALID_MERCHANT_STATUS("7007", "invalid.merchant.status"),
    AMOUNT_INVALID_NUMBER("7008", "amount.invalid"),
    ERROR_FEE_OVER_AMOUNT("7009", "error.fee.over.amount"),
    INVALID_DATE_FORMAT("7009", "date.format.invalid"),
    INVALID_QUERY_STATUS("7010", "invalid.query.status"),
    INVALID_TRANSACTION_TYPE("7011", "invalid.trans.type"),
    PAYOUT_TRANSACTION_NOT_EXIST("7012", "Payout with id {0} not found"),
    TRANSACTION_NOT_EXIST_ID("7012", "Transaction with id {0} not found"),

    REFUND_REQUEST_NOT_EXIST_ID("7012", "Refund Request with id {0} not found"),
    CAPTURE_REPORT_NOT_EXIST_ID("7012", "Settlement Report Capture with id {0} not found"),

    INVALID_TAX_TYPE("7013", "invalid.tax.type"),

    /*integration error*/
    CREATE_FLOCASH_ORDER_SERVICE_UNAVAILABLE("9999", "flocash.service.create.order.unavailable"),
    FLOCASH_GET_PAYMENT_OPTION_SERVICE_UNAVAILABLE("9999", "flocash.get.payment.option.service.unavailable"),
    FLOCASH_CREATE_DEPOSIT_SERVICE_UNAVAILABLE("9999", "flocash.create.deposit.service.unavailable"),
    FLOCASH_GET_DEPOSIT_FEE_SERVICE_UNAVAILABLE("9999", "flocash.get.deposit.fee.service.unavailable"),
    FLOCASH_GET_ORDER_STATUS_SERVICE_UNAVAILABLE("9999", "flocash.get.order.status.service.unavailable"),

    VIRTUAL_CARD_NOT_FOUND("7100", "Virtual card with id {0} not found"),
    ACCOUNT_NOT_EXIST("7101", "Account not exist with email: {0}"),
    TRANS_TYPE_INVALID("7102", "TransType invalid! correct value is {0}"),
    VIRTUAL_CARD_NOT_ENOUGH_MONEY("7103", "Unload amount must be equal or less than current balance"),
    INVALID_TYPE_SEARCH_FUND("7104", "Invalid query param 'type' for search fund"),
    INVALID_TYPE_SEARCH_VIRTUAL_CARD("7105", "Invalid query param 'block' for search virutal card"),

    VCN_ERROR_KES_ACCOUNT_REQUIRED("7106", "vcn.error.kes.account.required"),

    KEY_SECURE_APP_MISSING_CONFIG("7107", "secure.app.password configure not exist"),
    DEK_SECURE_APP_MISSING_CONFIG("7108", "secure.app.dek configure not exist"),
    GET_SECURE_HELPER_ERROR("7109", "Get decrypt secure helper error"),
    VIRTUAL_CARD_BILLING_ADDRESS_NOT_FOUND("7110", "Virtual card billing address of virtual card id {0} not found"),

    EXPORT_FILE_NOT_EXIST("7111", "Export file history not exist!"),
    TRANSACTION_NOT_EXIST("7112", "Transaction with transNumber {0} not exist!"),
    ECOM_DETAILS_NOT_EXIST("7112", "Transaction with txnId {0} not exist!"),
    INVALID_VCN_CARD("7113", "VcnId not valid"),
    INVALID_LOGIN_USER_ROLE("7114", "Role not valid"),
    INVALID_LOGIN_USER_STATUS("7115", "User Status not valid"),
    USER_ALREADY_EXIST("7116", "User already exist"), //
    INVALID_USER_STATUS("7118", "User status not valid! correct value is {0}"),
    INVALID_USER_ROLE("7118", "User role not valid! correct value is {0}"),
    REFUND_REQUEST_NOT_FOUND("7119", "RefundRequest not found with refundId {0}"),
    USER_NAME_ALREADY_EXIST("7120", "Username already exist"), //
    EMAIL_ALREADY_EXIST("7121", "Email already exist"), //
    ECOM_SETTING_OF_USER_NOT_FOUND("7112", "Not found ecommerce setting of user with id {0}"), //

    MID_SPECTRA_VALUE_NOT_EXIST_CONFIGURE("7120", "Invalid mid value code."),
    TID_SPECTRA_VALUE_NOT_EXIST_CONFIGURE("7121", "Invalid tid value code."),

    MANAGED_SPECTRA_VALUE_NOT_EXIST_CONFIGURE("7122", "Invalid manager spectra {0} value code."),
    MANAGED_SPECTRA_CONFIGURE_MISSING("7123", "User not yet have spectra configure! please check with admin"),
    MANAGED_SPECTRA_NOT_FOUND("7124", "Manager spectra {0} does not exist"), //
    MERCHANT_SPECTRA_NOT_FOUND("7125", "Merchant spectra {0} does not exist"), //
    TERMINAL_SPECTRA_NOT_FOUND("7126", "Terminal spectra {0} does not exist"), //

    INVALID_WITHDRAW_TYPE("7127", "WithdrawType not valid! correct value is {0}"),

    WITHDRAW_TRAN_NOT_FOUND("7128", "Withdraw transaction not found with id {0}"),
    PNA_TYPE_NOT_EXISTS("7129", "PnaType not exists with code {0}"),
    PNA_STATUS_NOT_EXISTS("7130", "PnaStatus not exists with code {0}"),

    MID_SPECTRA_NOT_EXIST("7131", "Mid spectra {0} does not exist or you not have right to see"), //
    MERCHANT_SPECTRA_ALREADY_EXIST("7132", "Mid spectra '{0}' vs '{1}' already exist"),
    TERMINAL_SPECTRA_ALREADY_EXIST("7132", "Terminal spectra '{0}' vs Mid Spectra'{1}' already exist"),
    MANAGED_SPECTRA_ALREADY_EXIST("7133","ManagedSpectra for user already exist try other value! " ),
    USER_FIELD_REQUIRED("7134", "user field is require!"), //

    INVALID_STATUS_SEND_CARD("7135", "Your account must be verified to send money. Go to account summary page and select Verify account link.!"), //
    SEND_MONEY_TO_SELF("7136", "You cannot send money to your own account."),
    SEND_MONEY_RULE_USER_BALANCE_INVALID("7137", "rules.invalidBalance"),//
    ACCOUNT_CURRENCY_NOT_EXIST("7101", "Account not have account in currency: {0}"),
    LOGIN_USER_EMAIL_EXIST("7101", "Login user email already exist: {0}"),
    LOGIN_USER_USERNAME_EXIST("7101", "Login user username already exist: {0}"),
    CMS_USER_MERCHANT_GROUP_EMPTY_ADD("7102", "merchants email is empty! please check again"),
    CMS_USER_MERCHANT_GROUP_EMPTY("7103", "merchants email is empty! please check again"),
    CMS_USER_MERCHANT_GROUP_NOT_FOUND("7104", "merchants not exist in group to deleted! please check again"),
    CMS_USER_MERCHANT_GROUP_ALREADY_EXIST("7106", "merchant already exist in group email {0}"),
    SETTLEMENT_CURRENCY_NOT_FOUND("7107", "Settlement currency not found with id {0} or you not have right to view this"), //

    NO_ZOHO_PROVIDER_CONFIG_FOUND("8000", "There is no zoho provider config!"), //
    NOT_ABLE_TO_GET_ZOHO_TOKEN("8001", "Not able to get zoho token please check again!"), //
    ECOMMERCE_ORDER_NOT_EXIST_ID("8002", "Ecommerce order with id {0} not found"), //

    INVALID_ORDER_INQUIRY("8003", "flocash.ecommerce.can.not.inquiry"),
    INVALID_PARTNER_ORDER_INQUIRY("8004", "flocash.ecommerce.inquiry.partner.not.implement"),
    PAYMENT_PARTNER_NOT_EXIST("8005", "payment partner not exist in order {0}"),
    INVALID_ORDER_REFUND("8006", "flocash.ecommerce.can.not.refund"),
    REFUND_AMOUNT_INVALID("8007", "flocash.ecommerce.refund.refundAmount.greater.than.amount"),
    ERROR_ECOMMERCE_PROCESS("8008", "flocash.ecommerce.can.not.process"),
    FAILED_REFUND_PROCESS("8009", "flocash.ecommerce.refund.failed"),
    CAN_NOT_REFUND_PROCESS("8010", "flocash.ecommerce.can.not.refund"),
    CAN_NOT_ACTIVE_ECOMMERCE_ORDER("8010", "flocash.ecommerce.can.not.active"),
    FLOCASH_CAPTURE_AMOUNT_INVALID("8010", "flocash.capture.amount.invalid"),
    FLOCASH_SKYE_STATUS("8010", "flocash.skye.status"),
    ECOMMERCE_NOTIFY_NOT_EXIST_ID("8011", "Ecommerce notify with id {0} not found"), //
    RECONCILE_EXCEPTION_NOT_EXIST("8012", "Reconcile Exception with id {0} not exist!"),

    CREATE_RECONCILE_EXCEPTION_FAIL("8013", "Create reconcile exception error!"),
    ACQUIRER_NOT_FOUND("8014","Acquired not found with id {0}" ),
    API_CREDENTIAL_NOT_FOUND("8015", "API Credential not found with id {0} not exist or already delete"),
    PAYMENT_PARTNER_NOT_FOUND("8016", "Payment Partner with id {0} not exist or already delete"),
    PAYMENT_OPTION_NOT_FOUND("8017", "Payment Option with id {0} not exist or already delete"),
    PAYMENT_OPTION_TYPE_INVALID("8018", "Payment Option type {0} is invalid"),

    PAYMENT_METHOD_NOT_FOUND("8019", "Payment method with id {0}"),
    PAYMENT_METHOD_OPTION_INVALID("8020", "Payment Method option {0} is invalid"),
    PAYMENT_METHOD_TYPE_INVALID("8021", "Payment Method type {0} is invalid"),
    PAYMENT_METHOD_STATUS_INVALID("8022", "Payment Method status {0} is invalid"),

    ACCOUNT_NOT_FOUND("8023", "Merchant {0} not have account in currency {1}"),

    MENU_APP_INVALID("8024", "Menu type '{0}' is invalid"),
    MENU_OPERATOR_INVALID("8024", "Menu operator '{0}' is invalid"),
    MENU_APP_NOT_FOUND("8025", "Menu type '{0}' not exist or already delete"),
    MENU_APP_PARENT_NOT_FOUND("8025", "Menu parent '{0}' not exist or already delete"),

    MERCHANT_ALERT_RULE_NOT_FOUND("8026", "Merchant alert rule not found with id {0}"), //

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

    ErrorCodeType(String code, String message) {
        this.message = message;
        this.code = code;
    }

    public static void main(String... args) {
        for (ErrorCodeType e : ErrorCodeType.values()) {
            System.out.println(e.code + "=" + e.message);
        }
    }
}