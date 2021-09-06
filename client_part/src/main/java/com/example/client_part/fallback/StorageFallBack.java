package com.example.client_part.fallback;

import com.example.client_part.Interface.StorageInterface;
import com.example.commons.entities.Storage;

import java.util.List;
import java.util.Map;

public class StorageFallBack implements StorageInterface {
    @Override
    public List<Storage> getAllShop() {
        return null;
    }

    @Override
    public Storage getByName(String name) {
        return null;
    }

    @Override
    public int updateLeft(Map map) {
        return 0;
    }
}
