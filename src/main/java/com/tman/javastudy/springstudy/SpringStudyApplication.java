package com.tman.javastudy.springstudy;

import com.tman.javastudy.service.GoodsService;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;

//@SpringBootApplication
@ComponentScan("com.tman.javastudy")
public class SpringStudyApplication implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(SpringStudyApplication.class, args);
        GoodsService goodService = applicationContext.getBean( GoodsService.class);

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }
}
