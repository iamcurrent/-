package com.example.client_part.controllers;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.client_part.Interface.ImagesInterface;
import com.example.client_part.Interface.LoginInterface;
import com.example.commons.entities.ImageProxy;
import com.example.commons.entities.Images;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.awt.*;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Controller
@SuppressWarnings("all")
public class RegisterAndLogin {

    @Autowired
    LoginInterface loginInterface;

    @Autowired
    ImagesInterface imagesInterface;

    @Autowired
    RestTemplate restTemplate;
   //对其进行登录验证，并使用cookie实现持久化登录
    @ResponseBody
    @PostMapping("/to_login_server")
    public String toLogin(@RequestBody Map map,HttpServletResponse response){
        String s = loginInterface.doLogin(map);
        if(s.equals("ok")){
            Cookie cookie = new Cookie("token",(String) map.get("username"));
            cookie.setMaxAge(60);

            response.addCookie(cookie);
        }
        return s;
    }

    /**
     *  let send_data = {
     *                     "owner":img_owner,
     *                     "image_address":img_address,
     *                     "description":img_description,
     *                     "online_flag":"n",
     *                     "date":img_time,
     *                     "private_flag":flag
     *                 };
     *
     *     String owner;//图片的上传者
     *     String image_address;//图片对应的地址
     *     String description;//图片对应的描述信息
     *     String online_flag;//图片对应的物体是否在线下存储
     *     String date;//图片上传的时间
     *     int private_flag;//指示我们的图像是公开还是私有
     * @param map
     * @return
     */

    //上传自己的图片
    @PostMapping("/publish")
    @ResponseBody
    public String publishImg(HttpServletRequest request, @RequestParam("file") MultipartFile multipartFile){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String format = simpleDateFormat.format(calendar.getTime());
        String img_description =  request.getParameter("img_description");
        String img_time  = format;
        String img_owner = request.getParameter("img_owner");
        String img_private = request.getParameter("img_private");
        Map map = new HashMap();
        map.put("img_description",img_description);
        map.put("img_time",img_time);
        map.put("img_owner",img_owner);
        map.put("img_private",img_private);
        map.put("fileName",multipartFile.getOriginalFilename());

        try {
            InputStream inputStream = multipartFile.getInputStream();
            byte[] data = new byte[(int) multipartFile.getSize()];
            inputStream.read(data);
            Base64.Encoder encoder = Base64.getEncoder();
            String s = encoder.encodeToString(data);
            map.put("file",s);
        }catch (Exception e){
            e.printStackTrace();
        }

        imagesInterface.insertImages(map);
        return "ok";
    }

    //去到注册服务
    @PostMapping("/to_register_server")
    @ResponseBody
    public String toRegister(@RequestBody Map map){
        String s = loginInterface.doRegister(map);
        System.out.println("注册的返回值:"+s);
        return s;
    }

    //去到登录页面
    @GetMapping("/login_page")
    public String getLoginPage(){

        return "r_l/login";
    }

    //所有公共的访问都要进行限流
    //去公共的主页

    @GetMapping("/index")
    public String toIndex(HttpServletRequest request, Map map){
        List<ImageProxy> res  = imagesInterface.getImagesByFlag(false);
        System.out.println(res.size());
        map.clear();
        map.put("images",res);
        Object token1 = request.getSession().getAttribute("token");//这里验证是不是已经登录
        if(token1!=null&&!"null".equals(token1)){
            return "s_sour/index";
        }
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("token")){
                request.getSession().setAttribute("token",cookie.getValue());
                break;
            }
        }
        Object token = request.getSession().getAttribute("token");
        if(token==null){
            request.getSession().setAttribute("token","null");
        }

        return "s_sour/index";
    }


    //去注册页面
    @GetMapping("/register_page")
    public String getRefisterPage(){

        return "r_l/register";
    }

    //去到个人主页
    @GetMapping("/show_page")
    public String showPage(HttpServletRequest request,Map map){
        Object token = request.getSession().getAttribute("token");
        List<Images> imagesByOwner = imagesInterface.getImagesByOwner((String) token);
        map.put("pri_images",imagesByOwner);
        return "moban_source1_pages/index1";
    }

    //退出登录
    @GetMapping("/login_out")
    public String loginOut(HttpServletRequest request,HttpServletResponse response){
        Cookie cookie = get(request,"token");
        if(cookie!=null) {
            set(response, "token", null, 0);
        }
        Object token = request.getSession().getAttribute("token");

        if(token!=null&&!token.equals("null")){
            request.getSession().removeAttribute("token");
            request.getSession().setMaxInactiveInterval(0);
        }

        return "redirect:/login_page";
    }
    //将对应的cookie设置为失效

    private static Cookie get(HttpServletRequest request,
                             String name) {
        //1.将cookies放到map中去
        Map<String, Cookie> cookieMap = new HashMap<>();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        //2.查找是否存在cookie,是则返回查找到的cookie
        if (cookieMap.containsKey(name)) {
            return cookieMap.get(name);
        } else {
            return null;
        }
    }


    private static void set(HttpServletResponse response,
                           String name,
                           String value,
                           int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }


}
