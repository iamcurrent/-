package com.example.image_server.services;

import com.example.commons.entities.Images;
import com.example.image_server.dao.ImagesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@SuppressWarnings("all")
public class ImagesServer {
    @Autowired
    ImagesDao imagesDao;

    public List<Images> getImagesByOwner(String owner){
        return imagesDao.getImagesByOwner(owner);
    }

    public int insertImage(Images images){
        return imagesDao.insertImage(images);
    }

    public List<Images> getImagesByFlag(boolean private_flag){
        return imagesDao.getImagesByFlag(private_flag);
    }






}
