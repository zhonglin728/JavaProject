package org.spring.springboot.web;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.spring.springboot.async.GitHubLookupService;
import org.spring.springboot.exception.OrderPeriodException;
import org.spring.springboot.response.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @Auther: zhonglin
 * @Date: 2019/9/15 16:08
 * @Description:
 */

@RequestMapping(value = "async")
@RestController
@Slf4j
public class AsyncController {
    @Autowired
    private GitHubLookupService gitHubLookupService;

    @GetMapping(value = "github")
    public ResponseEntity<ResponseResult> asyncTest() {
        ResponseResult responseResult = null;
        try {
// Start the clock
            long start = System.currentTimeMillis();

            // Kick of multiple, asynchronous lookups
            CompletableFuture<Map> page1 = gitHubLookupService.findUser("vue");
            CompletableFuture<Map> page2 = gitHubLookupService.findUser("js");
            CompletableFuture<Map> page3 = gitHubLookupService.findUser("String");
            CompletableFuture<Map> page4 = gitHubLookupService.findUser("java");
            CompletableFuture<Map> page5 = gitHubLookupService.findUser("spring");
            CompletableFuture<Map> page6 = gitHubLookupService.findUser("string");

            // Wait until they are all done
            //join() 的作用：让“主线程”等待“子线程”结束之后才能继续运行
            CompletableFuture.allOf(page1, page2, page3, page4, page5, page6).join();
            //log.info("--> " + page1.get());
            //log.info("--> " + page2.get());
            //log.info("--> " + page3.get());
            //等待任务完成！
           /* for(;;){
                if (page1.isDone() && page2.isDone() && page3.isDone() && page4.isDone() && page5.isDone() && page6.isDone()){
                    break;
                }
                Thread.sleep(100);
                log.info("等待ing!.......");
            }*/
            float exc = (float) (System.currentTimeMillis() - start) / 1000;
            log.info("Elapsed time: " + exc + " seconds");

            ArrayList<Map> strings = Lists.newArrayList(page1.get(), page2.get(), page3.get(), page4.get(), page5.get(), page6.get());
            responseResult = new ResponseResult(strings);

        } catch (Exception e) {
            e.printStackTrace();
            throw new OrderPeriodException("444" , e.getMessage());

        }

        return new ResponseEntity<>(responseResult, HttpStatus.OK);
    }

}
