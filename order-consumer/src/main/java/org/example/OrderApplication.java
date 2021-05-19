package org.example;

import com.netflix.loadbalancer.RandomRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class OrderApplication {


    /**
     * 负载均衡策略设置
     * 全局设置负载均衡策略，在启动类注入策略bean
     * @return
     */
    @Bean
    public RandomRule randomRule(){
        return new RandomRule();
    }

    /**
     * 注入远程调用http工具bean
     * @return
     */
    @Bean
    @LoadBalanced//负载均衡注解
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class,args);
    }
}
