package com.example.client_part.controllers;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.client_part.Interface.StorageInterface;
import com.example.commons.entities.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@SuppressWarnings("all")
public class StorageImplController {
    @Autowired
    StorageInterface storageInterface;

    @GetMapping("/getAllShop")
    public String getAllShop(Map map){
        List<Storage> allShop = storageInterface.getAllShop();
        System.out.println("店铺的个数:"+allShop.size());
        map.put("shops",allShop);
        List<String> imagesAddress = new ArrayList<>();
        for (Storage storage : allShop) {
            String img_address = storage.getImg_address();
            JSONObject jsonObject = JSON.parseObject(img_address);
            Object img_address1 = jsonObject.get("img_address");
            JSONArray jsonArray = (JSONArray)img_address1;
            imagesAddress.add((String)jsonArray.get(0));
        }
        JSONObject jsonObject = new JSONObject();

        imagesAddress = imagesAddress.subList(0,(int)(imagesAddress.size()/2));
        System.out.println(imagesAddress);
        map.put("shop_img",imagesAddress);
        map.put("jsonObject",jsonObject);
        return "storage_templates/offline";
    }

    @GetMapping("/details")
    public String getByName(String name,Map map){
        System.out.println(name);
        Storage storage = storageInterface.getByName(name);
        String img_address = storage.getImg_address();
        JSONObject jsonObject = JSON.parseObject(img_address);
        Object img_address1 = jsonObject.get("img_address");
        JSONArray jsonArray = (JSONArray)img_address1;
        List<String> list = new ArrayList<>();
        for (Object o : jsonArray) {
            list.add(o.toString());
        }

        JSONObject jsonObject1 = JSON.parseObject(storage.getVisitMonth());
        JSONArray visitMonth = (JSONArray) jsonObject1.get("visitMonth");

        map.put("visitMonth",visitMonth);
        map.put("shop_images",list);
        map.put("shop",storage);
        return "storage_templates/details";
    }

    @PostMapping("/update")
    @ResponseBody
    public int updateLeft(@RequestBody Map map){
        int i = storageInterface.updateLeft(map);
        return i;
    }


}
