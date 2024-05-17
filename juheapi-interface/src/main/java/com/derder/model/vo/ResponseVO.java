package com.derder.model.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseVO implements Serializable {

    String type;
    Object data;
    public ResponseVO( String type, Object data) {
        this.type=type;
        this.data=data;
    }
}
