package com.lzj.walletpayment.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 交易记录表
 * @TableName record
 */
@ApiModel(value = "交易记录对象")
@TableName(value ="record")
@Data
public class Record implements Serializable {
    /**
     * 消费记录id
     */
    @ApiModelProperty(value = "消费记录id")
    @TableId(type = IdType.AUTO)
    private Integer rId;

    /**
     * 金额
     */
    @ApiModelProperty(value = "金额")
    private double money;

    /**
     * 余额
     */
    @ApiModelProperty(value = "余额")
    private double balance;

    /**
     * 类型
     */
    @ApiModelProperty(value = "类型")
    private String type;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 钱包id
     */
    @ApiModelProperty(value = "钱包id")
    private Integer wId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}