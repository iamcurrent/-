package com.example.client_part.controllers;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.client_part.Interface.StorageInterface;
import com.example.commons.entities.MonthVisit;
import com.example.commons.entities.Storage;
import com.example.commons.entities.StoreImages;
import com.example.commons.entities.StoreObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@SuppressWarnings("all")
public class StorageImplController {
    @Autowired
    StorageInterface storageInterface;

    @GetMapping("/getAllShop")
    public String getAllShop(Map map){
        List<StoreObject> allShop = storageInterface.getAllShop();
        System.out.println("店铺的个数:"+allShop.size());
        map.put("shops",allShop);
        List<String> imagesAddress = new ArrayList<>();
        for (StoreObject storage : allShop) {

            List<StoreImages> storeImages = storage.getStoreImages();
            imagesAddress.add(storeImages.get(0).getImgAddress());
        }
        map.put("shop_img",imagesAddress);
        //map.put("jsonObject",imagesAddress);
        return "storage_templates/offline";
    }

    @GetMapping("/details")
    public String getByName(String name,Map map){
        System.out.println(name);
        StoreObject byName = storageInterface.getByName(name);
        map.put("storage",byName.getStorage());
        map.put("images",byName.getStoreImages());
        MonthVisit monthVisit = byName.getMonthVisit();
        StringBuilder builder = new StringBuilder();
        builder.append(monthVisit.getNum1()+",");
        builder.append(monthVisit.getNum2()+",");
        builder.append(monthVisit.getNum3()+",");
        builder.append(monthVisit.getNum4()+",");
        builder.append(monthVisit.getNum5()+",");
        builder.append(monthVisit.getNum6()+",");
        builder.append(monthVisit.getNum7()+",");
        builder.append(monthVisit.getNum8()+",");
        builder.append(monthVisit.getNum9()+",");
        builder.append(monthVisit.getNum10()+",");
        builder.append(monthVisit.getNum11()+",");
        builder.append(monthVisit.getNum12());

        map.put("monthVisit",builder.toString());

        return "storage_templates/details";
    }

    @PostMapping("/update")
    @ResponseBody
    public int updateLeft(@RequestBody Map map){
        int i = storageInterface.updateLeft(map);
        return i;
    }

    /**
     *formData.append("owner",owner);
     *         formData.append("storeName",storeName);
     *         formData.append("description",description);
     *         formData.append("geo",geo);
     *         formData.append("tel",tel);
     *         formData.append("file",file);
     * @param request
     * @param multipartFile
     * @return
     */

    @PostMapping("/register")
    @ResponseBody
    public String registerStore(HttpServletRequest request, @RequestParam("file") MultipartFile multipartFile){
        /**
         *         String storeName  = map.get("storeName").toString();//店铺名字
         *         String owner = map.get("owner").toString();
         *         int leftContain = (int)map.get("leftContain");//可用的存储容量
         *         String tel = map.get("tel").toString();//店铺的联系电话
         *         String address = map.get("address").toString();// 店铺对应的地址
         *
         *         String description = map.get("description").toString();//店铺的描述
         *         String fileName = map.get("fileName").toString();
         *         StoreImages image = (StoreImages)map.get("image");
         */

        /**
         formData.append("owner",owner);
         formData.append("storeName",storeName);
         formData.append("leftContain",leftContain);
         formData.append("imgDesc",imgDesc);
         formData.append("storeDesc",storeDesc);
         formData.append("geo",geo);
         formData.append("tel",tel);
         formData.append("file",file);
         */


         String fileName = multipartFile.getOriginalFilename();
         String image = "" ;
        try {
            InputStream inputStream = multipartFile.getInputStream();
            byte [] bytes = new byte[(int)multipartFile.getSize()];
            inputStream.read(bytes);
            Base64.Encoder encoder = Base64.getEncoder();
            image = encoder.encodeToString(bytes);
        }catch (Exception e){

        }
        Map map = new HashMap(){
            {
                put("storeName",request.getParameter("storeName"));
                put("storeDesc",request.getParameter("storeDesc"));
                put("owner",request.getParameter("owner"));
                put("leftContain",Integer.parseInt(request.getParameter("leftContain")));
                put("tel",request.getParameter("tel"));
                put("address",request.getParameter("geo"));
            }
        };

        map.put("fileName",fileName);
        map.put("image",image);
        map.put("imgDesc",request.getParameter("imgDesc"));

        System.out.println(map);
        String s = storageInterface.registerStore(map);
        return "ok";
    }


}
