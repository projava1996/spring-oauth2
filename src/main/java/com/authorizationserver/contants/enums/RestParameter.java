package com.authorizationserver.contants.enums;

import java.util.regex.Pattern;

public enum RestParameter {
	/* header parameters */
	HEADER_REQUEST_ID("Request-Id", null, "[0-9]{6,10}"), //
	HEADER_REFERENCE_ID("Reference-Id", null, "[0-9]{12}"), //
	HEADER_ERROR_ID("Error-Id", null, "[A-Z][A-Z0-9]*(_[A-Z0-9]*)*"), //
	HEADER_ERROR_CODE("Error-Code", null, "[A-Z][A-Z0-9]*(_[A-Z0-9]*)*"), //
	HEADER_ERROR_MESSAGE("Error-Message", null, ".+"), //
	HEADER_ERROR_MESSAGE_PARAM("Error-Message-Params", null, ".+"), //
	USER_NICK("user.nick", "nick", ".+"), //
	MERCHANT_ACCOUNT("merchantAccount", "merchantAccount", ".+"), //
	CURRENCY("currency", "currency", "[0-9]{3,3}"), //
	EXCHANGE_SOURCE("exchange.source", "source", ".+"), //
	EXCHANGE_DESTINATION("exchange.destination", "destination", ".+"), //
	ORDER_TRACKING("order.tracking", "tracking", ".+"), //
	COUNTRY_CODE("country.code", "code", ".+"), //
	COUNTRY_HASMETHOD("country.hasMethod", "hasMethod", "(true|false)"), //
	PENDING_DEPOSIT_ID("deposit.depositId", "depositId", ".+"), //
	PAYMENT_OPTION("paymentOption","paymentOption",".+"),//
	PAGING_SIZE("pageSize","pageSize","[0-9]{0,12}"),//
	PAGING_NEXTPAGE("nextPage","nextPage","[0-9]{0,12}"),//
	FROMDATE_QUERY("fromDate","fromDate",".+"),//
	TODATE_QUERY("toDate","toDate",".+"),//
	ORDERID_QUERY("orderId","orderId",".+"),//
	;

	private final String key;
	private final String variable;
	private final Pattern pattern;
	private final int minLength;
	private final int maxLength;

	private RestParameter(final String key) {
		this(key, null);
	}

	private RestParameter(final String key, final String variable) {
		this(key, variable, "");
	}

	private RestParameter(final String key, final String variable,
                          final String pattern) {
		this(key, variable, pattern, 0, Integer.MAX_VALUE);
	}

	private RestParameter(final String key, final String variable,
                          final Pattern pattern) {
		this(key, variable, pattern, 0, Integer.MAX_VALUE);
	}

	private RestParameter(final String key, final String variable,
                          final String pattern, final int minLength, final int maxLength) {
		this(key, variable, pattern == null ? null : Pattern.compile(pattern),
				minLength, maxLength);
	}

	private RestParameter(final String key, final String variable,
                          final Pattern pattern, final int minLength, final int maxLength) {
		this.key = key;
		this.variable = variable;
		this.pattern = pattern;
		this.minLength = Math.min(Math.max(0, minLength), maxLength);
		this.maxLength = Math.max(minLength, maxLength);
	}

	public boolean matches(final String value) {
		if (value == null) {
			return false;
		}
		if (pattern != null && !pattern.matcher(value).matches()) {
			return false;
		}
		if (value.length() < minLength) {
			return false;
		}
		if (value.length() > maxLength) {
			return false;
		}
		return true;
	}

	public String getKey() {
		return key;
	}

	public String getVariable() {
		return variable;
	}

	public Pattern getPattern() {
		return pattern;
	}

	public int getMinLength() {
		return minLength;
	}

	public int getMaxLength() {
		return maxLength;
	}

}
