package com.derder.model.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum ResultTypeEnum {
    STRING("字符串", "string"),
    IMAGE("图片", "url");

    private final String text;

    private final String value;

    ResultTypeEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 获取值列表
     *
     * @return
     */
    public static List<String> getValues() {
        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
    }

    public String getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
}
