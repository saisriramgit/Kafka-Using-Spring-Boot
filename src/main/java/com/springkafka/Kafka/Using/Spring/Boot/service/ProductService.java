package com.springkafka.Kafka.Using.Spring.Boot.service;

import com.springkafka.Kafka.Using.Spring.Boot.controller.CreateProductRestModel;

public interface ProductService {
    String createProduct(CreateProductRestModel productRestModel);
}
