package com.example.order_server.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class RedisLock {
        @Resource
        StringRedisTemplate template;
        public boolean tryLock(String key, String value, int expireTime, TimeUnit timeUnit) {
            Boolean flag = template.opsForValue().setIfAbsent(key, value, expireTime, timeUnit);
            if (flag == null || !flag) {
                log.info("申请锁(" + key + "," + value + ")失败");
                return false;
            }
            log.error("申请锁(" + key + "," + value + ")成功");
            return true;
        }

        public void unLock(String key, String value) {
            String script = "if redis.call('get', KEYS[1]) == KEYS[2] then return redis.call('del', KEYS[1]) else return 0 end";
            RedisScript<Long> redisScript = new DefaultRedisScript<>(script, Long.class);
            Long result = template.execute(redisScript, Arrays.asList(key, value));
            if (result == null || result == 0) {
                log.info("释放锁(" + key + "," + value + ")失败,该锁不存在或锁已经过期");
            } else {
                log.info("释放锁(" + key + "," + value + ")成功");
            }
        }

}

