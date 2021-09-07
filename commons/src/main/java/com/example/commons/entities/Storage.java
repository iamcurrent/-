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
    //String visitMonth;//(一年) 店铺的月访问频次 //json
    //String img_address ;//店铺对应的图片地址 //json
    String description;//店铺的描述

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public int getVisitNum() {
        return visitNum;
    }

    public void setVisitNum(int visitNum) {
        this.visitNum = visitNum;
    }

    public int getLeftContain() {
        return leftContain;
    }

    public void setLeftContain(int leftContain) {
        this.leftContain = leftContain;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getVisitMonth() {
        return visitMonth;
    }

    public void setVisitMonth(String visitMonth) {
        this.visitMonth = visitMonth;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
