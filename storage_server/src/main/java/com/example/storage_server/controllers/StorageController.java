package com.example.storage_server.controllers;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.example.commons.entities.MonthVisit;
import com.example.commons.entities.Storage;
import com.example.commons.entities.StoreImages;
import com.example.commons.entities.StoreObject;
import com.example.storage_server.dao.StorageDao;
import com.example.storage_server.services.StorageServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.stream.FileImageOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@SuppressWarnings("all")
public class StorageController {
    //在这里涉及到访问量等的更新
    @Autowired
    StorageServer storageServer;
    //进入线下获取所有店铺的简要信息
    @GetMapping("/getAllShop")
    @ResponseBody
    public List<StoreObject> getAllShop(){
        List<Storage> allStorage = storageServer.getAllStorage();//获取所有商店
        List<StoreImages> allImages = storageServer.getAllImages();//获取商店对应的图片
        List<StoreObject> storeObjects = new ArrayList<>();
        allStorage.parallelStream().forEach(o->{
            List<StoreImages> collect = allImages.parallelStream().filter(o1 ->
                    o.getStoreName() .equals( o1.getStoreName())
            ).collect(Collectors.toList());
            storeObjects.add(new StoreObject(o,new ArrayList<>(){{add(collect.get(0));}},null));
        });

        storeObjects.parallelStream().forEach(o->{
            List<StoreImages> storeImages = o.getStoreImages();
            try{
                FileInputStream inputStream = new FileInputStream(new File(storeImages.get(0).getImgAddress()));
                FileChannel channel = inputStream.getChannel();
                ByteBuffer buffer = ByteBuffer.allocate((int)channel.size());
                channel.read(buffer);
                buffer.flip();
                byte [] bytes = new byte[(int)channel.size()];
                buffer.get(bytes,0,bytes.length);
                Base64.Encoder encoder = Base64.getEncoder();
                String s = encoder.encodeToString(bytes);
                o.getStoreImages().get(0).setImgAddress(s);
                channel.close();
                inputStream.close();
            }catch (Exception e){
            }
        });

        return storeObjects;
    }
    //获取一个店铺对应的所有信息
    @PostMapping("/getByName")
    @ResponseBody
    public StoreObject getByName(@RequestBody String name){
        //涉及到访问量的更新
        System.out.println(name);
        Storage storage = storageServer.getStorageByName(name);
        storageServer.updateVisitNum(name);
        Date date = new Date();
        int month = date.getMonth();
        String index = "num"+month;
        storageServer.updateVisitMonth(index,name);
        MonthVisit monthVisitByStoreName = storageServer.getMonthVisitByStoreName(name);
        List<StoreImages> imagesByStoreName = storageServer.getImagesByStoreName(name);
        imagesByStoreName.parallelStream().forEach(o->{
            try{
                FileInputStream inputStream = new FileInputStream(new File(o.getImgAddress()));
                FileChannel channel = inputStream.getChannel();
                ByteBuffer buffer = ByteBuffer.allocate((int)channel.size());
                channel.read(buffer);
                buffer.flip();
                byte [] bytes = new byte[(int)channel.size()];
                buffer.get(bytes,0,bytes.length);
                Base64.Encoder encoder = Base64.getEncoder();
                String s = encoder.encodeToString(bytes);
                o.setImgAddress(s);
                channel.close();
                inputStream.close();
            }catch (Exception e){
            }
        });

        //修改一个月的所有访问

        StoreObject object = new StoreObject(storage,imagesByStoreName,monthVisitByStoreName);
        return object;
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


    @PostMapping("/register")
    @ResponseBody
    public String registerStore(@RequestBody Map map){
        /**
         *         Map map = new HashMap(){
         *             {
         *                 put("storeName",request.getParameter("storeName"));
         *                 put("storeDesc",request.getParameter("storeDesc"));
         *                 put("owner",request.getParameter("owner"));
         *                 put("leftContain",Integer.parseInt(request.getParameter("leftContain")));
         *                 put("tel",request.getParameter("tel"));
         *                 put("address",request.getParameter("geo"));
         *             }
         *         };
         *
         *         map.put("fileName",fileName);
         *         map.put("image",image);
         *         map.put("imgDesc",request.getParameter("imgDesc"));
         */
        //获取当前时间
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String time = simpleDateFormat.format(calendar.getTime());

        //构造商店数据库信息
        Map map1 = new HashMap();
        map1.put("storeName",map.get("storeName").toString());
        map1.put("owner",map.get("owner").toString());
        map1.put("rtime",time);
        map1.put("visitNum",0);
        map1.put("leftContain",Integer.parseInt(map.get("leftContain").toString()));
        map1.put("tel",map.get("tel").toString());
        map1.put("address",map.get("address").toString());
        map1.put("score",0);
        map1.put("description",map.get("storeDesc").toString());
        map1.put("auth",false);
        String image = map.get("image").toString();
        String fileName = map.get("fileName").toString();
        String imgDesc = map.get("imgDesc").toString();

        /**
         *      String storeName;
         *     String imgAddress;
         *     String uploadTime;
         *     String imgDisc;
         */

        Map map2 = new HashMap(); //把图片存储到数据库
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decode = decoder.decode(image);
        fileName = nameConvert(fileName,map.get("storeName").toString(),time);
        map2.put("storeName",map.get("storeName").toString());
        map2.put("imgAddress",fileName);
        map2.put("uploadTime",time);
        map2.put("imgDisc",imgDesc);
        try {
            File file1 = new File("./StoreImages/" + map.get("storeName").toString());
            if(!file1.exists()) {
                file1.mkdirs();
                saveImg(decode,fileName);
            }else {
                saveImg(decode,fileName);
            }
        }catch (Exception e){
        }
        storageServer.registerStore(map1);
        storageServer.insertMonthVisit(map.get("storeName").toString());
        storageServer.insertImages(map2);
        return "ok";
    }

    private String nameConvert(String fileName,String storeName,String dateTime){
        String[] split = fileName.split("\\.");
        String date = dateTime.split(" ")[0];
        String [] time = dateTime.split(" ")[1].split(":");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(date);
        stringBuilder.append("-"+time[0]);
        stringBuilder.append("-"+time[1]);
        stringBuilder.append("-"+time[2]);
        fileName = "./StoreImages/"+storeName+"/"+split[0]+stringBuilder.toString()+".jpg";
        return  fileName;
    }

    private void saveImg(byte [] data,String fileName){
        try {
            ByteBuffer buffer = ByteBuffer.allocate(data.length);
            buffer.put(data);
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            FileChannel channel = fileOutputStream.getChannel();
            buffer.flip();
            channel.write(buffer);
            channel.close();
            fileOutputStream.close();

        }catch (Exception e){

        }
    }


}
