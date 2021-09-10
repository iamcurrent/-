package com.example.client_part.fallback;

import com.example.client_part.Interface.StorageInterface;
import com.example.commons.entities.Storage;
import com.example.commons.entities.StoreObject;

import java.util.List;
import java.util.Map;

public class StorageFallBack implements StorageInterface {
    @Override
    public List<StoreObject> getAllShop() {
        return null;
    }

    @Override
    public StoreObject getByName(String name) {
        return null;
    }

    @Override
    public int updateLeft(Map map) {
        return 0;
    }

    @Override
    public String registerStore(Map map) {
        return null;
    }



}
