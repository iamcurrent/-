package com.example.client_part.fallback;

import com.example.client_part.Interface.ImagesInterface;
import com.example.commons.entities.Images;

import java.util.List;
import java.util.Map;

public class ImagesFallBack implements ImagesInterface {
    @Override
    public List<Images> getImagesByOwner(String owner) {
        return null;
    }

    @Override
    public String insertImages(Map images) {
        return null;
    }

    @Override
    public List<Images> getImagesByFlag(boolean private_flag) {
        return null;
    }
}
