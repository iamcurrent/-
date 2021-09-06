package com.example.commons.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Images {
    String owner;//图片的上传者
    String image_address;//图片对应的地址
    String description;//图片对应的描述信息
    boolean entry;//图片对应的物体是否在线下存储
    String upload_time;//图片上传的时间
    boolean private_flag;//指示我们的图像是公开还是私有
    long click_number;//用于记录当前图片的点赞个数

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getImage_address() {
        return image_address;
    }

    public void setImage_address(String image_address) {
        this.image_address = image_address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isEntry() {
        return entry;
    }

    public void setEntry(boolean entry) {
        this.entry = entry;
    }

    public String getUpload_time() {
        return upload_time;
    }

    public void setUpload_time(String upload_time) {
        this.upload_time = upload_time;
    }

    public boolean isPrivate_flag() {
        return private_flag;
    }

    public void setPrivate_flag(boolean private_flag) {
        this.private_flag = private_flag;
    }

    public long getClick_number() {
        return click_number;
    }

    public void setClick_number(long click_number) {
        this.click_number = click_number;
    }
}
