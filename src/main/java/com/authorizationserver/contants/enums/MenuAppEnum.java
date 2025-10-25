package com.authorizationserver.contants.enums;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Objects;

public enum MenuAppEnum {
    ADMIN("ADMIN"),
    FCMS("FCMS"),
    ;

    private final String value;

    MenuAppEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static MenuAppEnum getEnum(String val) {
        return Arrays.stream(MenuAppEnum.values()).filter(s -> Objects.equals(s.value, val)).findFirst().orElse(null);
    }

    public static boolean contains(String val) {
        if(StringUtils.isEmpty(val)) {
            return false;
        }
        return Arrays.stream(MenuAppEnum.values()).anyMatch(s -> Objects.equals(s.value, val));
    }
}
