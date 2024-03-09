package com.jxys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * All rights Reserved, Designed By misteryliu.
 * 房子数据
 *
 * @author misteryliu@outlook.com
 * @since 2024/2/24 20:59 Copyright ©2024 misteryliu. All rights reserved.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "T2024022322thefutureofhome")
public class HouseInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "Id", type = IdType.AUTO)
    private Integer Id;

    @TableField("webName")
    private String webName;

    @TableField("houseName")
    private String houseName;

    @TableField("villageName")
    private String villageName;

    @TableField("houseNote")
    private String houseNote;

    @TableField("houseTotlePrice")
    private String houseTotlePrice;

    @TableField("houseUnitPrice")
    private String houseUnitPrice;

    @TableField("houseLink")
    private String houseLink;

    @TableField("houseImg")
    private String houseImg;

    @TableField("followNum")
    private String followNum;


}
