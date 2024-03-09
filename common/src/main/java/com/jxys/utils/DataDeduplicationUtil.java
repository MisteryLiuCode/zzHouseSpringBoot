package com.jxys.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jxys.entity.Location;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * All rights Reserved, Designed By misteryliu.
 * 数据去重
 *
 * @author misteryliu@outlook.com
 * @since 2024/2/24 21:44 Copyright ©2024 misteryliu. All rights reserved.
 */
public class DataDeduplicationUtil {
    public static void main(String[] args) {
        String path = "/Users/liushuaibiao/Downloads/同步空间/work/springboot-scaffold/api/demo1.json";
        String deduplicationPath = "/Users/liushuaibiao/Downloads/同步空间/work/springboot-scaffold/api/demoDeduplication1.json";
        dataDeduplication(path, deduplicationPath);
    }


    public static boolean dataDeduplication(String path, String deduplicationPath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Map<String, Integer> countMap = new HashMap<>();
            // 读取JSON文件
            List<Location> locationList = objectMapper.readValue(new File(path), new TypeReference<List<Location>>() {
            });
            System.out.println("去重前数据量：" + locationList.size());
            // 对lng和lat进行去重
            Set<Coordinate> uniqueCoordinates = new HashSet<>();
            List<Location> results = new ArrayList<>();

            for (Location data : locationList) {
                Coordinate coordinate = new Coordinate(data.getLng(), data.getLat());
                if (!uniqueCoordinates.contains(coordinate)) {
                    uniqueCoordinates.add(coordinate);
                    results.add(data);
                }
            }
            System.out.println("去重后数据量：" + results.size());
            // 将数据写入新的json文件
            try (FileWriter writer = new FileWriter(deduplicationPath)) {
                writer.write("[");
                for (int i = 0; i < results.size(); i++) {
                    Location location = results.get(i);
                    double lng = location.getLng();
                    double lat = location.getLat();
                    String name = location.getName();

                    // Create a JSON object with lng and lat
                    JSONObject locationJson = new JSONObject();
                    locationJson.put("lng", lng);
                    locationJson.put("lat", lat);
                    locationJson.put("name", name);

                    // Convert the JSON object to a pretty-printed string
                    String jsonString = JSON.toJSONString(locationJson, true);

                    // Write the JSON string to the file
                    writer.write(jsonString);
                    if (i != results.size() - 1) {
                        // Assuming you want to append each location in separate lines
                        writer.write(",");
                    }
                    // Assuming you want to append each location in separate lines
                    writer.write(System.lineSeparator());
                }
                writer.write("]");
                return true;
            } catch (IOException e) {
                return false;
            }


            //            // 遍历地区,统计区域数量
            //            for (Location location : result) {
            //                String url = "https://restapi.amap.com/v3/geocode/regeo?output=json&location=" + location.getLng() + "," + location.getLat() + "&key=0fb664b477f15256035f259fd406defc&radius=500&extensions=base";
            //                String res = HttpUtil.createGet(url).execute().body();
            //                GaodeLocation gaodeLocation = JSONObject.parseObject(res, GaodeLocation.class);
            //                String district = gaodeLocation.getRegeocode().getAddressComponent().getDistrict();
            //                if (countMap.containsKey(district)) {
            //                    // 如果Map中已经存在该key，则将其value加1
            //                    countMap.put(district, countMap.get(district) + 1);
            //                } else {
            //                    // 如果Map中不存在该key，则将其加入Map，并将value设为1
            //                    countMap.put(district, 1);
            //                }
            //            }
            //            // 打印统计结果
            //            for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            //                System.out.println(entry.getKey() + ": " + entry.getValue());
            //            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


}

