package org.example.service.impl;

import org.example.pojo.Order;
import org.example.pojo.Product;
import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 三种调用方式去调用远程服务
     * 1.DiscoveryClient：通过获取袁术句获取服务注册信息
     * 2.LoadBalancerClient:Ribbon的负载均衡器调用
     * 3.@LoadBalanced:通过注解开启Ribbon的负载均衡器
     */

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Override
    public Order getOrderInfo() {
            return new Order(1,"北京",new BigDecimal("30000"),
                    getProductList());
    }

    /**
     * 1.DiscoveryClient：通过获取袁术句获取服务注册信息
     * @return
     */
    public List<Product> getProductList(){
        //去服务中心获取所有的服务列表
        List<String> serviceIds = discoveryClient.getServices();
        if (CollectionUtils.isEmpty(serviceIds)) {
            return null;
        }
        //根据服务名称获取服务
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("product-service");
        if(CollectionUtils.isEmpty(serviceInstances)){
            return null;
        }
        //获取一个服务,拼接服务地址
        ServiceInstance serviceInstance = serviceInstances.get(0);
        StringBuffer sb = new StringBuffer();
        sb.append("http://").append(serviceInstance.getHost()).append(":").append(serviceInstance.getPort()).append("/product/list");
        //发送请求
        ResponseEntity<List<Product>> response = restTemplate.exchange(sb.toString(), HttpMethod.GET, null, new ParameterizedTypeReference<List<Product>>() {});
        return response.getBody();
    }

    /**
     *  2.LoadBalancerClient:Ribbon的负载均衡器调用
     * @return
     */
 /*   public List<Product> getProductList(){
        //根据服务名称获取服务注册信息
        ServiceInstance serviceInstance = loadBalancerClient.choose("product-service");
        if (serviceInstance == null) {
            return null;
        }
        //获取一个服务,拼接服务地址
        StringBuffer sb = new StringBuffer();
        sb.append("http://"+serviceInstance.getHost()+":"+serviceInstance.getPort()+"/product/list");
        //发送请求
        ResponseEntity<List<Product>> response = restTemplate.exchange(sb.toString(), HttpMethod.GET, null, new ParameterizedTypeReference<List<Product>>() {});
        return response.getBody();
    }*/

    /**
     * 3.@LoadBalanced:通过注解开启Ribbon的负载均衡器
     * @return
     */
    /*public List<Product> getProductList(){
        //直接指定服务名，发送请求
        ResponseEntity<List<Product>> response = restTemplate.exchange("http://product-service/product/list", HttpMethod.GET, null, new ParameterizedTypeReference<List<Product>>() {});
        return response.getBody();
    }*/

}
