package com.jxys.utils;

/**
 * All rights Reserved, Designed By misteryliu.
 *
 * @author misteryliu@outlook.com
 * @since 2024/2/24 21:46 Copyright ©2024 misteryliu. All rights reserved.
 */
import java.util.Objects;

public class Coordinate {

    private double lng;
    private double lat;

    public Coordinate(double lng, double lat) {
        this.lng = lng;
        this.lat = lat;
    }

    // 重写equals和hashCode方法，用于Set的去重判断

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return Double.compare(that.lng, lng) == 0 &&
                Double.compare(that.lat, lat) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lng, lat);
    }
}
