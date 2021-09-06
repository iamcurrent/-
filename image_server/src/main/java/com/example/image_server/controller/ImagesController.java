package com.example.image_server.controller;


import com.example.commons.entities.Images;
import com.example.image_server.services.ImagesServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@Controller
@SuppressWarnings("all")
public class ImagesController {
    @Autowired
    ImagesServer imagesServer;

    @PostMapping("/get_images_info")
    @ResponseBody
    public List<Images> getImagesByOwner(@RequestBody String owner){
        List<Images> imagesByOwner = imagesServer.getImagesByOwner(owner);
        dataHandler(imagesByOwner);
        return imagesByOwner;
    }

    private void dataHandler(List<Images> data){
        for (int i = 0; i < data.size(); i++) {
            Images images = data.get(i);
            String image_address = images.getImage_address();
            try{
                FileImageInputStream fileImageInputStream = new FileImageInputStream(new File(image_address));
                byte [] bytes = new byte[(int)fileImageInputStream.length()];
                fileImageInputStream.read(bytes);
                Base64.Encoder encoder = Base64.getEncoder();
                String s = encoder.encodeToString(bytes);
                images.setImage_address(s);
                fileImageInputStream.close();
            }catch (Exception e){

            }
        }
        System.out.println(data.size());
    }


    @PostMapping("/getImgByFlag")
    @ResponseBody
    public List<Images> getImagesByFlag(@RequestBody boolean private_flag){
        List<Images> imagesByFlag = imagesServer.getImagesByFlag(private_flag);
        dataHandler(imagesByFlag);
        return imagesByFlag;
    }


    @PostMapping("/insert_images_info")
    @ResponseBody
    public String insertImages(@RequestBody Map images){
        String img_description =  (String)images.get("img_description");
        String img_time  = (String)images.get("img_time");
        String img_owner = (String)images.get("img_owner");
        String img_private = (String)images.get("img_private");
        String fileName = (String)images.get("fileName");
        String file = (String)images.get("file");
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decode = decoder.decode(file);
        File file1 = new File("./images/"+img_owner);
        if(!file1.exists()){
            file1.mkdirs();
            saveImg(decode,fileName);
        }else {
            String[] split = fileName.split("\\.");
            String date = img_time.split(" ")[0];
            String [] time = img_time.split(" ")[1].split(":");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(date);
            stringBuilder.append("-"+time[0]);
            stringBuilder.append("-"+time[1]);
            stringBuilder.append("-"+time[2]);
            fileName = "./images/"+img_owner+"/"+stringBuilder.toString()+".jpg";
            saveImg(decode,fileName);
        }

        Images images1 = new Images(img_owner,fileName,img_description,false,img_time,img_private.equals("true")?true:false,0);
        imagesServer.insertImage(images1);

        return "insert";
    }

    private void saveImg(byte [] bytes,String fileName){
        try {
            FileOutputStream outputStream = new FileOutputStream(new File(fileName));
            outputStream.write(bytes);
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
