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
}
