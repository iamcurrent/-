package com.example.client_part;

import com.example.client_part.Interface.AccountInterface;
import com.example.client_part.Interface.OrderInterface;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class ClientPartApplicationTests {

    @Autowired
    AccountInterface accountInterface;

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Autowired
    OrderInterface orderInterface;

    @Test
    void contextLoads() {
    }

    @Test
    public void update(){
        orderInterface.updatePayFlag(2);//修改订单为支付状态
    }

}
