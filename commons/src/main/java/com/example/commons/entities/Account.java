package com.example.commons.entities;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account implements Serializable {
    String name="" ;//当前用户对用的名字，账户的主键
    int remainder=0 ;//当前用户的余额
    String cardId="";//卡号
    String bank=""; //银行
}
