package com.jxys.user.service.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.jxys.base.entity.BaseService;
import com.jxys.entity.Geocodes;
import com.jxys.entity.HouseInfo;
import com.jxys.entity.ReverseLocation;
import com.jxys.mapper.HouseInfoMapper;
import com.jxys.user.service.GaodeLocationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class GaodeLocationServiceImpl extends BaseService implements GaodeLocationService {

    @Resource
    private HouseInfoMapper houseInfoMapper;

    private String prefixUrl = "https://restapi.amap.com/v3/geocode/geo?address=";

    private String suffixUrl = "&key=0fb664b477f15256035f259fd406defc";

    @Override
    public void houseNameToLngLat() {

        QueryWrapper<HouseInfo> select = new QueryWrapper<HouseInfo>().select(
                "villageName,houseTotlePrice,houseLink");
        // 拿到所有数据
        List<HouseInfo> houseList = houseInfoMapper.selectList(select);
        // 指定要写入的文件路径
        String filePath = "demo.json";
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("[");
            for (HouseInfo house : houseList) {
                // 调用高德逆地理解析
                String url = prefixUrl + house.getVillageName() + suffixUrl;
                String result = HttpUtil.createRequest(Method.GET, url).execute().body();
                ReverseLocation locationRes = JSONObject.parseObject(result, ReverseLocation.class);
                if (locationRes.getStatus().equals("1")) {
                    List<Geocodes> geocodesList = locationRes.getGeocodes();
                    if (CollectionUtils.isNotEmpty(geocodesList)) {
                        Geocodes geocodes = geocodesList.get(0);
                        String[] split = geocodes.getLocation().split(",");
                        String lng = split[0];
                        String lat = split[1];
                        String houseName = house.getVillageName();

                        // 创建一个JSONObject对象
                        JSONObject json = new JSONObject();
                        json.put("lng", lng);
                        json.put("lat", lat);
                        json.put("name", houseName);

                        // Convert the JSON object to a pretty-printed string
                        String jsonString = JSON.toJSONString(json, true);

                        // Write the JSON string to the file
                        writer.write(jsonString);
                        writer.write(",");
                        writer.write(System.lineSeparator());
                    }

                }
            }
            writer.write("]");
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
