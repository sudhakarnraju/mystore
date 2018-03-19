package com.sudhakar.mystore.services;

import com.sudhakar.mystore.InMemoryProductRepository;
import com.sudhakar.mystore.MySqlProductRepository;
import com.sudhakar.mystore.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import java.util.Optional;

@RestController
public class CatalogController {
    private final Logger logger = LoggerFactory.getLogger(InMemoryProductRepository.class);

    MySqlProductRepository productRepository ;//= new InMemoryProductRepository();

    //Injected Spring IOC
    public  CatalogController(MySqlProductRepository productRepository) {
        this.productRepository=productRepository;

    }

    @Autowired
    Jedis jedis;

    @GetMapping("/products")
    //@Cacheable("products" )
    public Iterable<Product> list() {

        logger.info("Products List invoked");
        return productRepository.findAll();

    }

    @GetMapping("/products/{id}")
    @Cacheable(value="products", key="#id")
    public Product findById(@PathVariable(value="id") int id){
        logger.info("findById:" + id);

        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()) return  product.get();
        return null;
    }




    @PostMapping("/products")
    @CachePut(value="products", key="#result.id")
    public Product add(@RequestBody String name){
        logger.info("Products List - POST/ADD invoked: " + name);
        Product product = new Product();
        product.setName(name);
        productRepository.save(product);
        return product;
    }

    @PutMapping("/products")
    public Product update(Product product){
        productRepository.save(product);
        return product;
    }
}
