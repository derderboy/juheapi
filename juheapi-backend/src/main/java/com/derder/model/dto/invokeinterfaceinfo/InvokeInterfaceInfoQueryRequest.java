package com.derder.model.dto.invokeinterfaceinfo;

import lombok.Data;

import java.util.Date;

@Data
public class InvokeInterfaceInfoQueryRequest {
    /**
     * 主键
     */
    private Long id;

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

    /**
     * 创建时间
     */
    private Date createtime;
}
