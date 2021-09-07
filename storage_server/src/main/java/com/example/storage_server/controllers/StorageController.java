package com.example.storage_server.controllers;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.example.commons.entities.Storage;
import com.example.storage_server.dao.StorageDao;
import com.example.storage_server.services.StorageServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@SuppressWarnings("all")
public class StorageController {
    //在这里涉及到访问量等的更新
    @Autowired
    StorageServer storageServer;
    //进入线下获取所有店铺的简要信息
    @GetMapping("/getAllShop")
    @ResponseBody
    public List<Storage> getAllShop(){

        return storageServer.getAllStorage();
    }
    //获取一个店铺对应的所有信息
    @PostMapping("/getByName")
    @ResponseBody
    public Storage getByName(@RequestBody String name){
        //涉及到访问量的更新
        System.out.println(name);
        Storage storage = storageServer.getStorageByName(name);
        storageServer.updateVisitNum(storage.getVisitNum()+1,name);
        //修改一个月的所有访问
        String visitMonth = storage.getVisitMonth();
        JSONObject jsonObject = JSON.parseObject(visitMonth);
        JSONArray jsonArray = (JSONArray) jsonObject.get("visitMonth");
        Date date = new Date();
        int month = date.getMonth();
        jsonArray.set(month-1,(int)jsonArray.get(month-1)+1);
        jsonObject.put("visitMonth",jsonArray);
        storageServer.updateVisitMonth(jsonObject.toJSONString(),name);

        return storage;
    }

    //修改库存需要使用分布式锁，同时订单提交，修改账户，修改库存需要分布式事务的支持
    //对库存进行更新,加库存或是减库存
    @PostMapping("/update")
    @ResponseBody
    public int updateLeft(@RequestBody Map map){
        System.out.println("收到的信息："+map);
        //使用了位置就进行减库存，否则进行加库存
        Storage storage = storageServer.getStorageByName((String) map.get("shopName"));
        int newLeft = storage.getLeftContain()+Integer.parseInt(map.get("code").toString());
        int i = storageServer.updateLeftContain(newLeft, (String) map.get("shopName"));
        return i;
    }




}
