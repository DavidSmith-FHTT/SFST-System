package com.ldf.demo.utils.config;

import com.ldf.demo.service.StyleTransferService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.ldf.demo.utils.RPC.getHessianClientBean;

@Configuration
public class HessionConfig {
    @Value("${hessian-url}")
    String url;
    
    @Bean
    public StyleTransferService getStyleTransferService(){

        StyleTransferService styleTransferService = null;
        try {
            styleTransferService = getHessianClientBean(StyleTransferService.class, url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return styleTransferService;
    }
}
