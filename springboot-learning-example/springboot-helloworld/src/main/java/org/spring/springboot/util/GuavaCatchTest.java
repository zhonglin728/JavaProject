package org.spring.springboot.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.cache.*;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.spring.springboot.entity.Student;
import org.spring.springboot.enums.ColorEnum;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: zhonglin
 * @Date: 2019/9/17 19:51
 * @Description:
 */
@Slf4j
public class GuavaCatchTest {
    private  static List<Student> list1 = new ArrayList<Student>();
    private  static   List<Student> list2 = new ArrayList<Student>();
    static {
        list1.add(new Student("e",10,"24", ColorEnum.GREEN));
        list1.add(new Student("b",1,"23", ColorEnum.GREEN));
        list1.add(new Student("d",23,"20", ColorEnum.GREEN));
        list1.add(new Student("c",56,"45", ColorEnum.GREEN));
        list1.add(new Student("f",34,"78", ColorEnum.GREEN));
        list1.add(new Student("g",32,"89", ColorEnum.GREEN));
        list1.add(new Student("a",12,"47", ColorEnum.GREEN));

        list2.add(new Student("周杰伦",3,"25", ColorEnum.YELLO));
        list2.add(new Student("罗志祥",7,"13", ColorEnum.RED));

    }

    public static  void main(String args []) throws JsonProcessingException {
        GuavaCatchTest.put("list",list1);
        val list = GuavaCatchTest.get("list");
        val res = String.format("%s",new ObjectMapper().writeValueAsString(list));
        log.info("结果：{}",res);

    }




        /**
         * 缓存项最大数量
         */
    private static final long GUAVA_CACHE_SIZE = 100000;
    /**
     * 缓存时间：分钟
     */
    private static final long GUAVA_CACHE_TIME = 10;

    /**
     * 缓存操作对象
     */
    private static LoadingCache<String, Object> GLOBAL_CACHE = null;

    static {
        try {
            GLOBAL_CACHE = loadCache(new CacheLoader<String, Object>() {
                @Override
                public Object load(String key) throws Exception {
                    // 该方法主要是处理缓存键不存在缓存值时的处理逻辑
                    if(log.isDebugEnabled())
                        log.debug("Guava Cache缓存值不存在，初始化空值，键名：{}", key);
                    return null;
                }
            });
        } catch (Exception e) {
            log.error("初始化Guava Cache出错", e);
        }
    }

    /**
     * 全局缓存设置
     * <ul>
     * <li>缓存项最大数量：100000</li>
     * <li>缓存有效时间（分钟）：10</li>
     * </ul>
     *
     * @param cacheLoader
     * @return
     * @throws Exception
     */
    private static <K, V> LoadingCache<K, V> loadCache(CacheLoader<K, V> cacheLoader) throws Exception {
        /*
         * maximumSize 缓存池大小，在缓存项接近该大小时， Guava开始回收旧的缓存项 expireAfterAccess 表示最后一次使用该缓存项多长时间后失效 removalListener 移除缓存项时执行的逻辑方法 recordStats 开启Guava Cache的统计功能
         */
        LoadingCache<K, V> cache = CacheBuilder.newBuilder().maximumSize(GUAVA_CACHE_SIZE).expireAfterAccess(GUAVA_CACHE_TIME, TimeUnit.MINUTES)
                .removalListener(new RemovalListener<K, V>() {
                    public void onRemoval(RemovalNotification<K, V> rn) {
                        if (log.isDebugEnabled())
                            log.debug("Guava Cache缓存回收成功，键：{}, 值：{}", rn.getKey(), rn.getValue());
                    }
                }).recordStats().build(cacheLoader);
        return cache;
    }

    /**
     * 设置缓存值
     *
     * @param key
     * @param value
     */
    public static void put(String key, Object value) {
        try {
            GLOBAL_CACHE.put(key, value);
            if (log.isDebugEnabled())
                log.debug("缓存命中率：{}，新值平均加载时间：{}", getHitRate(), getAverageLoadPenalty());
        } catch (Exception e) {
            log.error("设置缓存值出错", e);
        }
    }

    /**
     * 批量设置缓存值
     *
     * @param map
     */
    public static void putAll(Map<? extends String, ? extends Object> map) {
        try {
            GLOBAL_CACHE.putAll(map);
            if (log.isDebugEnabled())
                log.debug("缓存命中率：{}，新值平均加载时间：{}", getHitRate(), getAverageLoadPenalty());
        } catch (Exception e) {
            log.error("批量设置缓存值出错", e);
        }
    }

    /**
     * 获取缓存值
     * <p>注：如果键不存在值，将调用CacheLoader的load方法加载新值到该键中</p>
     *
     * @param key
     * @return
     */
    public static Object get(String key) {
        Object obj = null;
        try {
            obj = GLOBAL_CACHE.get(key);
            if (log.isDebugEnabled())
                log.debug("缓存命中率：{}，新值平均加载时间：{}", getHitRate(), getAverageLoadPenalty());
        } catch (Exception e) {
            log.error("获取缓存值出错", e);
        }
        return obj;
    }

    /**
     * 获取缓存值
     * <p>注：如果键不存在值，将直接返回 NULL</p>
     *
     * @param key
     * @return
     */
    public static Object getIfPresent(String key) {
        Object obj = null;
        try {
            obj = GLOBAL_CACHE.getIfPresent(key);
            if (log.isDebugEnabled())
                log.debug("缓存命中率：{}，新值平均加载时间：{}", getHitRate(), getAverageLoadPenalty());
        } catch (Exception e) {
            log.error("获取缓存值出错", e);
        }
        return obj;
    }

    /**
     * 移除缓存
     *
     * @param key
     */
    public static void remove(String key) {
        try {
            GLOBAL_CACHE.invalidate(key);
            if (log.isDebugEnabled())
                log.debug("缓存命中率：{}，新值平均加载时间：{}", getHitRate(), getAverageLoadPenalty());
        } catch (Exception e) {
            log.error("移除缓存出错", e);
        }
    }

    /**
     * 批量移除缓存
     *
     * @param keys
     */
    public static void removeAll(Iterable<String> keys) {
        try {
            GLOBAL_CACHE.invalidateAll(keys);
            if (log.isDebugEnabled())
                log.debug("缓存命中率：{}，新值平均加载时间：{}", getHitRate(), getAverageLoadPenalty());
        } catch (Exception e) {
            log.error("批量移除缓存出错", e);
        }
    }

    /**
     * 清空所有缓存
     */
    public static void removeAll() {
        try {
            GLOBAL_CACHE.invalidateAll();
            if (log.isDebugEnabled())
                log.debug("缓存命中率：{}，新值平均加载时间：{}", getHitRate(), getAverageLoadPenalty());
        } catch (Exception e) {
            log.error("清空所有缓存出错", e);
        }
    }

    /**
     * 获取缓存项数量
     *
     * @return
     */
    public static long size() {
        long size = 0;
        try {
            size = GLOBAL_CACHE.size();
            if (log.isDebugEnabled())
                log.debug("缓存命中率：{}，新值平均加载时间：{}", getHitRate(), getAverageLoadPenalty());
        } catch (Exception e) {
            log.error("获取缓存项数量出错", e);
        }
        return size;
    }

    /**
     * 获取所有缓存项的键
     *
     * @return
     */
    public static List<String> keys() {
        List<String> list = new ArrayList<String>();
        try {
            ConcurrentMap<String, Object> map = GLOBAL_CACHE.asMap();
            for (Map.Entry<String, Object> item : map.entrySet())
                list.add(item.getKey());
            if (log.isDebugEnabled())
                log.debug("缓存命中率：{}，新值平均加载时间：{}", getHitRate(), getAverageLoadPenalty());
        } catch (Exception e) {
            log.error("获取所有缓存项的键出错", e);
        }
        return list;
    }

    /**
     * 缓存命中率
     *
     * @return
     */
    public static double getHitRate() {
        return GLOBAL_CACHE.stats().hitRate();
    }

    /**
     * 加载新值的平均时间，单位为纳秒
     *
     * @return
     */
    public static double getAverageLoadPenalty() {
        return GLOBAL_CACHE.stats().averageLoadPenalty();
    }

    /**
     * 缓存项被回收的总数，不包括显式清除
     *
     * @return
     */
    public static long getEvictionCount() {
        return GLOBAL_CACHE.stats().evictionCount();
    }



}
