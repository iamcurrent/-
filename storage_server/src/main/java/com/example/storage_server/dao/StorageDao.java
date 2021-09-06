package com.example.storage_server.dao;

import com.example.commons.entities.Impl.StorageImpl;
import com.example.commons.entities.Storage;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface StorageDao {
    public List<Storage> getAllStorage();
    public Storage getStorageByName(String name);
    public int updateLeftContain(Map map);
    public int updateVisitNum(Map map);
    public int updateVisitMonth(Map map);

}
