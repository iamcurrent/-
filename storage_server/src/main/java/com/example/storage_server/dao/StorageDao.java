package com.example.storage_server.dao;

import com.example.commons.entities.Impl.StorageImpl;
import com.example.commons.entities.MonthVisit;
import com.example.commons.entities.Storage;
import com.example.commons.entities.StoreImages;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface StorageDao {
    public List<Storage> getAllStorage(); //获取所用商店的信息
    public Storage getStorageByName(String name);//通过名字获取对应的信息
    public int updateLeftContain(Map map);//更新剩余容量
    public int updateVisitNum(String name);//更新访问量
    public int updateVisitMonth(Map map);//更新这一年的月访问量
    public List<StoreImages> getImagesByStoreName(String storeName);
    public MonthVisit getMonthVisitByStoreName(String storeName);
    public List<StoreImages> getAllImages();
    public List<MonthVisit> getAllMonthVisit();
    public void registerStore(Map map);
    public void insertMonthVisit(String storeName);
    public void insertImages(Map map);
}
