package com.example.image_server.dao;


import com.example.commons.entities.Images;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ImagesDao {
    public List<Images> getImagesByOwner(String owner);
    public int insertImage(Images images);
    public List<Images> getImagesByFlag(boolean private_flag);
}
