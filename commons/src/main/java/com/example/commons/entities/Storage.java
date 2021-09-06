package com.example.commons.entities;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Storage {
    String storeName ;//店铺名字
    int visitNum ;//店铺的访问量
    int leftContain ;//可用的存储容量
    String tel ;//店铺的联系电话
    String address;// 店铺对应的地址
    int score;// 店铺对应的平均评分
    String visitMonth;//(一年) 店铺的月访问频次 //json
    String img_address ;//店铺对应的图片地址 //json
    String description;//店铺的描述
}
