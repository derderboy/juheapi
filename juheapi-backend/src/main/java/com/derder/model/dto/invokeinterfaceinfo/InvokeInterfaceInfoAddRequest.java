package com.derder.model.dto.invokeinterfaceinfo;

import lombok.Data;

@Data
public class InvokeInterfaceInfoAddRequest {
    /**
     * 调用用户 id
     */
    private Long userid;

    /**
     * 接口 id
     */
    private Long interfaceinfoid;

    /**
     * 调用ip
     */
    private String ip;
}
