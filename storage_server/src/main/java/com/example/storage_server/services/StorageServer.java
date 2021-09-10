package com.example.storage_server.services;

import com.example.commons.entities.MonthVisit;
import com.example.commons.entities.Storage;
import com.example.commons.entities.StoreImages;
import com.example.storage_server.dao.StorageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.desktop.PrintFilesEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StorageServer {
    @Autowired
    StorageDao storageDao;

    public List<Storage> getAllStorage(){

        return storageDao.getAllStorage();
    }

    public List<StoreImages> getImagesByStoreName(String storeName){
        return storageDao.getImagesByStoreName(storeName);
    }

    public MonthVisit getMonthVisitByStoreName(String storeName){
        return storageDao.getMonthVisitByStoreName(storeName);
    }

    public List<MonthVisit> getAllMonthVisit(){
        return storageDao.getAllMonthVisit();
    }

    public List<StoreImages> getAllImages(){
        return storageDao.getAllImages();
    }

    public Storage getStorageByName(String name){

        return storageDao.getStorageByName(name);
    }

    public int updateLeftContain(int left,String name){
        Map map = new HashMap();
        map.put("left",left);
        map.put("name",name);

        return storageDao.updateLeftContain(map);
    }

    public int updateVisitNum(String name){
        return storageDao.updateVisitNum(name);
    }
    public int updateVisitMonth(String s,String name){
        Map map = new HashMap();
        map.put("index",s);
        map.put("name",name);
        return storageDao.updateVisitMonth(map);
    }

    public void registerStore(Map map){
        storageDao.registerStore(map);
    }

    public void insertMonthVisit(String storeName){
        storageDao.insertMonthVisit(storeName);
    }
    public void insertImages(Map map){
        storageDao.insertImages(map);
    }

}
