package com.example.commons.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageProxy {
   public Images imageObj;
   public String image;

    public Images getImageObj() {
        return imageObj;
    }

    public void setImageObj(Images imageObj) {
        this.imageObj = imageObj;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
