package com.derder.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * IP黑名单
 * @TableName ip_blacklist
 */
@TableName(value ="ip_blacklist")
@Data
public class IpBlacklist implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * IP地址
     */
    @TableField(value = "ip_address")
    private String ip_address;

    /**
     * 
     */
    @TableField(value = "userid")
    private Long userid;

    /**
     * 添加时间
     */
    @TableField(value = "added_at")
    private Date added_at;

    /**
     * 加入黑名单原因
     */
    @TableField(value = "reason")
    private String reason;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}