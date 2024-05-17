package com.derder.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 
 * @TableName generate
 */
@TableName(value ="generate")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Generate implements Serializable {
    /**
     * 
     */
    private String urlname;

    /**
     * 
     */
    private String url;

    /**
     * 
     */
    private String description;

    /**
     * 
     */
    private String strategyname;

    /**
     * 
     */
    private String method;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Generate other = (Generate) that;
        return (this.getUrlname() == null ? other.getUrlname() == null : this.getUrlname().equals(other.getUrlname()))
            && (this.getUrl() == null ? other.getUrl() == null : this.getUrl().equals(other.getUrl()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getStrategyname() == null ? other.getStrategyname() == null : this.getStrategyname().equals(other.getStrategyname()))
            && (this.getMethod() == null ? other.getMethod() == null : this.getMethod().equals(other.getMethod()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUrlname() == null) ? 0 : getUrlname().hashCode());
        result = prime * result + ((getUrl() == null) ? 0 : getUrl().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getStrategyname() == null) ? 0 : getStrategyname().hashCode());
        result = prime * result + ((getMethod() == null) ? 0 : getMethod().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", urlname=").append(urlname);
        sb.append(", url=").append(url);
        sb.append(", description=").append(description);
        sb.append(", strategyname=").append(strategyname);
        sb.append(", method=").append(method);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}