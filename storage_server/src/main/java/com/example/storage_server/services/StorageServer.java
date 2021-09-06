package com.example.storage_server.services;

import com.example.commons.entities.Storage;
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

    public Storage getStorageByName(String name){

        return storageDao.getStorageByName(name);
    }

    public int updateLeftContain(int left,String name){
        Map map = new HashMap();
        map.put("left",left);
        map.put("name",name);

        return storageDao.updateLeftContain(map);
    }

    public int updateVisitNum(int num,String name){
        Map map = new HashMap();
        map.put("num",num);
        map.put("name",name);
        return storageDao.updateVisitNum(map);
    }
    public int updateVisitMonth(String s,String name){
        Map map = new HashMap();
        map.put("s",s);
        map.put("name",name);
        return storageDao.updateVisitMonth(map);
    }


}
