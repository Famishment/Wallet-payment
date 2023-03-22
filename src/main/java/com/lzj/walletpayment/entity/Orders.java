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
 * 订单表
 * @TableName orders
 */
@ApiModel(value = "订单表")
@TableName(value ="orders")
@Data
public class Orders implements Serializable {
    /**
     * 订单id
     */
    @ApiModelProperty(value = "订单id")
    @TableId(type = IdType.AUTO)
    private Integer oId;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String commodity;

    /**
     * 金额
     */
    @ApiModelProperty(value = "金额")
    private double money;

    /**
     * 订单状态
     */
    @ApiModelProperty(value = "订单状态")
    private String status;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 钱包id
     */
    @ApiModelProperty(value = "钱包id")
    private Integer wId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}