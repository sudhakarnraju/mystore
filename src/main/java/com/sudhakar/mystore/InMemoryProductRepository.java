package com.sudhakar.mystore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class InMemoryProductRepository implements ProductRepository{
    private final Logger logger = LoggerFactory.getLogger(InMemoryProductRepository.class);


    static ArrayList<Product> products = new ArrayList<>();


    String getCurrentLocalDateTimeStamp() {
        return LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("HH:mm:ss.SSS"));
    }

    public InMemoryProductRepository(){
        //Initialize repository with products
        logger.info("Product Repository init called");

        String id;
        String name;
        for ( int i=0;i<10;i++){
            Product product = new Product();
            product.id = i;
            product.name="Product-" + getCurrentLocalDateTimeStamp() + "-" + product.id;
            products.add(product);
        }

        logger.info("Product Repository initialized");

    }

    @Override
    public List<Product> list() {
        return products;
    }
}
