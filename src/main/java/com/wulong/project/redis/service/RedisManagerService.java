package com.wulong.project.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * @Author: wulong
 * @Date: 2019/1/10 14:16
 * @Email: 491925129@qq.com
 */
@Service
public class RedisManagerService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, Serializable> serializableRedisTemplate;

    /**
     * 缓存字符串到redis中,无过期时间
     * @param key      键
     * @param value    值
     * @return
     */
    public boolean redisSetString(String key, String value) {
        try {
            stringRedisTemplate
                    .opsForValue()
                    .set(key,value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 缓存字符串到redis中,有过期时间
     * @param key           键
     * @param value         值
     * @param expriTime     过期时间（long格式）
     * @param timeUnit      时间类型（TimeUnit.SECONDS秒）
     * @return
     */
    public boolean redisSetString(String key, String value, long expriTime, TimeUnit timeUnit) {
        try {
            stringRedisTemplate
                    .opsForValue()
                    .set(key,value,expriTime,timeUnit);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 缓存serializable序列化对象到redis中，无过期时间
     * @param key
     * @param object
     * @return
     */
    public boolean redisSetObject(String key,Serializable object) {
        try {
            serializableRedisTemplate
                    .opsForValue()
                    .set(key, object);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 缓存Serializable序列化对象到redis中，有过期时间
     * @param key
     * @param object
     * @param expriTime
     * @param timeUnit
     * @return
     */
    public boolean redisSetObject(String key, Serializable object, long expriTime, TimeUnit timeUnit) {
        try {
            serializableRedisTemplate
                    .opsForValue()
                    .set(key, object, expriTime, timeUnit);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 从redis中根据key获取value
     * @param key
     * @return
     */
    public String redisGetByKey(String key) {
        try {
            return stringRedisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
