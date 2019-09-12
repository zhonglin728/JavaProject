package org.spring.springboot.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @ClassName PropertiesUtil
 * @Description: TODO
 * @Author zhonglin
 * @Date 2019/9/9
 * @Version V1.0
 **/
@Component
@ConfigurationProperties(prefix = "eureka.client")
@Getter
@Setter
public class EurekaConfig {
    private Map serviceUrl;
}
