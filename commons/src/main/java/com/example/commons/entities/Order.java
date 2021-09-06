package com.example.commons.entities;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {
    int id;//当前的订单编号，用于后续的订单识别，作为数据库的主键
    String account; //预约人的姓名,数据库的主键
    String tel ;//预约人的电话
    String date ;//预约的时间
    int money ;//预约对应的订单金额 按照物品大小和保存年限计算
    int pay;//当前是否已经支付 0/1
    int submit ;//当前是否已经上交实物 0/1
    int saveTime ;//物品保存的时限、
    String storeName;//预约的店铺
    String thing;//保存的物品图片地址
}
