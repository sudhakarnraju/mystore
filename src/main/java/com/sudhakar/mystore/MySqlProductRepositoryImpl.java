package com.sudhakar.mystore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/***
 * Class name should be the JPARepository interface name + Impl.
 * Runtime merges this implementation with automated JPARepository methods
 */
@Component
public class MySqlProductRepositoryImpl implements ProductRepository{


    private final Logger logger = LoggerFactory.getLogger(MySqlProductRepositoryImpl.class);


    static ArrayList<Product> products = new ArrayList<>();


    String getCurrentLocalDateTimeStamp() {
        return LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("HH:mm:ss.SSS"));
    }

    //TODO: Implement the list method
    @Override
    public List<Product> list() {

        logger.info("MySqlProductRepositoryImpl - List method");
        return null;
    }
}
