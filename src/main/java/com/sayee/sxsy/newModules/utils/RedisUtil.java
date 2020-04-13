package com.sayee.sxsy.newModules.utils;
 import java.io.ByteArrayInputStream;
 import java.io.ByteArrayOutputStream;
 import java.io.IOException;
 import java.io.ObjectInputStream;
 import java.io.ObjectOutputStream;
 import java.io.Serializable;
 import java.util.Set;
 import java.util.concurrent.TimeUnit;

 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.data.redis.core.RedisTemplate;
 import org.springframework.data.redis.core.ValueOperations;
 import org.springframework.stereotype.Service;

/**
 * Redis工具类
 * ：用于缓存数据
 * @author wanghaoyu
 *
 */

public class RedisUtil {
    private Logger logger = LoggerFactory.getLogger(RedisUtil.class);
    private RedisTemplate<Serializable, Object> redisTemplate;

    public void setRedisTemplate(RedisTemplate<Serializable, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 将对象-->byte[] (由于jedis中不支持直接存储object所以转换成byte[]存入)
     *
     * @param object
     * @return
     */
    public static byte[] serialize(Object object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            // 序列化
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
        } finally {
            try {
                oos.close();
                baos.close();
            } catch (IOException e) {
//                logger.error(e.getMessage(), e);
            }
        }
        return null;
    }

    /**
     * 将byte[] -->Object
     *
     * @param bytes
     * @return
     */
    public static Object unserialize(byte[] bytes) {
        ByteArrayInputStream bais = null;
        try {
            // 反序列化
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
        } finally {
            try {
                bais.close();
            } catch (IOException e) {
//                logger.error(e.getMessage(), e);
            }
        }
        return null;
    }

    /**
     * 批量删除对应的value
     *
     * @param keys
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 批量删除key
     *
     * @param pattern
     */
    public void removePattern(final String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0) {
            redisTemplate.delete(keys);
        }
    }

    /**
     * 删除对应的value
     *
     * @param key
     */
    public void remove(final String key) {
        logger.info("要移除的key为：" + key);
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        logger.info("要验证是否存在的key为：" + key);
        return redisTemplate.hasKey(key);
    }

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
//    public Object get(final String key) {
//        Object result = null;
//        ValueOperations<Serializable, Object> operations = redisTemplate
//                .opsForValue();
//        result = operations.get(key.getBytes());
//        return result;
//    }

    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate
                    .opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            logger.error("系统异常",e);
        }
        return result;
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate
                    .opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            logger.error("系统异常",e);
        }
        return result;
    }

}
