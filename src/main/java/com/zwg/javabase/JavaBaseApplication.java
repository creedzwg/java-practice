package com.zwg.javabase;

import com.zwg.javabase.entity.People;
import com.zwg.javabase.zk.ZkMain;
import org.springframework.beans.BeansException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

@SpringBootApplication
public class JavaBaseApplication implements CommandLineRunner , ApplicationContextAware {

    public static void main(String[] args) {
        SpringApplication.run(JavaBaseApplication.class, args);
    }

    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     * @throws Exception on error
     */
    @Override
    public void run(String... args) throws Exception {

        ZkMain.main(args);






    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, People> beansOfType = applicationContext.getBeansOfType(People.class);
        System.out.println(beansOfType);

    }
}
