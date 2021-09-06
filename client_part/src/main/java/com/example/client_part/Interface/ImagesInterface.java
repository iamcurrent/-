package com.example.client_part.Interface;

import com.example.client_part.config.RedisConfig;
import com.example.commons.entities.Images;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@FeignClient(value = "images-server")
public interface ImagesInterface {
    @PostMapping("/get_images_info")
    @Cacheable(value = "images",key = "'image'+#owner")
    public List<Images> getImagesByOwner(@RequestBody String owner);

    @PostMapping("/insert_images_info")
    public String insertImages(@RequestBody Map images);

    @PostMapping("/getImgByFlag")
    public List<Images> getImagesByFlag(@RequestBody boolean private_flag);


}
