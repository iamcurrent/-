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
    boolean pay;//当前是否已经支付 0/1
    boolean submit ;//当前是否已经上交实物 0/1
    int saveTime ;//物品保存的时限、
    String storeName;//预约的店铺
    String ig_address;//保存的物品图片地址

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public boolean isPay() {
        return pay;
    }

    public void setPay(boolean pay) {
        this.pay = pay;
    }

    public boolean isSubmit() {
        return submit;
    }

    public void setSubmit(boolean submit) {
        this.submit = submit;
    }

    public int getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(int saveTime) {
        this.saveTime = saveTime;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getIg_address() {
        return ig_address;
    }

    public void setIg_address(String ig_address) {
        this.ig_address = ig_address;
    }
}
