package com.zwg.javabase;

import com.zwg.javabase.entity.People;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: 张文刚
 * @Date: 2019/02/05  22:25
 * @Version: V1.0
 * @Description:
 */
@Configuration
public class SpringConfiguration {

    @Bean
    public People getPeople(){
        return new People();
    }


}
