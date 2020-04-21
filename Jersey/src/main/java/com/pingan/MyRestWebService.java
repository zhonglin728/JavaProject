package com.pingan;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * jersey 扫描包
 *
 * @author Administrator
 */
public class MyRestWebService extends ResourceConfig {
    @SuppressWarnings("deprecation")
    public MyRestWebService() {
        packages("com.pingan.restful");
        register(LoggingFilter.class);
    }

}
