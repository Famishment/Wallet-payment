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
 * 银行卡表
 * @TableName bank_card
 */
@ApiModel(value = "银行卡表")
@TableName(value ="bank_card")
@Data
public class BankCard implements Serializable {
    /**
     * 银行卡id
     */
    @ApiModelProperty(value = "银行卡id")
    @TableId(type = IdType.AUTO)
    private Integer bId;

    /**
     * 银行卡编号
     */
    @ApiModelProperty(value = "银行卡编号")
    private String cardName;

    /**
     * 余额
     */
    @ApiModelProperty(value = "余额")
    private double balance;

    /**
     * 钱包id
     */
    @ApiModelProperty(value = "钱包id")
    private Integer wId;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}