package com.jxys.entity;

import lombok.Data;

import java.util.List;

/**
 * All rights Reserved, Designed By misteryliu.
 * 高德逆地理
 *
 * @author misteryliu@outlook.com
 * @since 2024/2/24 15:49 Copyright ©2024 misteryliu. All rights reserved.
 */
@Data
public class ReverseLocation {

    private String status;
    private String info;
    private String infocode;
    private String count;
    private List<Geocodes> geocodes;
}
