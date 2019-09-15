package org.spring.springboot.async;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @Auther: zhonglin
 * @Date: 2019/9/15 16:06
 * @Description:
 */
@Service
@Slf4j
public class GitHubLookupService {
    //private static final Logger logger = LoggerFactory.getLogger(GitHubLookupService.class);

    @Autowired
    private RestTemplate restTemplate;

    // 这里进行标注为异步任务，在执行此方法的时候，会单独开启线程来执行(并指定线程池的名字)
    @Async("taskExecutor")
    public CompletableFuture<Map> findUser(String code) throws InterruptedException {
        log.info("Looking up " + code);
        //String url = String.format("https://api.github.com/users/%s", code);
        String url = String.format("https://api.github.com/search/repositories?q=%s+language:javascript&sort=stars&order=desc",code);
        Map results = restTemplate.getForObject(url, Map.class);
        // Artificial delay of 3s for demonstration purposes
        Thread.sleep(3000L);
        return CompletableFuture.completedFuture(results);
    }
}
