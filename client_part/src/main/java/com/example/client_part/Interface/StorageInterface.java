package com.example.client_part.Interface;

import com.example.commons.entities.Storage;
import com.example.commons.entities.StoreObject;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@FeignClient("storage-server")
public interface StorageInterface {
    @GetMapping("/getAllShop")
    //@Cacheable(value = "allShop")
    public List<StoreObject> getAllShop();

    @PostMapping("/getByName")
    public StoreObject getByName(@RequestBody String name);

    @PostMapping("/update")
    public int updateLeft(@RequestBody Map map);

    @PostMapping("/register")
    public String registerStore(@RequestBody Map map);

}
