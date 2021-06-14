package org.spring.springboot.util;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Thread;
import sun.plugin.ClassLoaderInfo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhonglin
 * @Title:
 * @Package
 * @Description:
 * @date 2021/6/717:11
 */
@Slf4j
public class HashMapDemp{
    public static Map<String, Object> hashMap = new HashMap<String,Object>();

    public static ReentrantLock reentrantLock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        new java.lang.Thread(new Thread1()).start();
        new java.lang.Thread(new Thread1()).start();
        new java.lang.Thread(new Thread1()).start();
        new java.lang.Thread(new Thread1()).start();


    }

    public static class  Thread1 implements Runnable{
        public  int count = 100;
        @Override
        public synchronized void run() {
                    //hashMap.put(String.valueOf(i)+"-T1",i);
            while (true) {
                count --;
                log.info("剩余："+count);
                if (count == 0) {
                    break;
                }
            }

        }
    }

    public static class Thread2 implements Runnable{
        @Override
        public  void run() {
                for (int i=0;i<10;i++){
                }
        }
    }

    public static class Thread3 implements Runnable{
        @Override
        public  void run() {
            for (int i=0;i<10;i++){
            }
        }
    }
    public static class Thread4 implements Runnable{
        @Override
        public  void run() {
            for (int i=0;i<10;i++){

            }
        }
    }
}
