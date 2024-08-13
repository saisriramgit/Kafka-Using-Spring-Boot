package com.springkafka.Kafka.Using.Spring.Boot.service;

import com.springkafka.Kafka.Using.Spring.Boot.controller.CreateProductRestModel;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService{

    KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;

    public ProductServiceImpl(KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public String createProduct(CreateProductRestModel productRestModel) {
        String productId = UUID.randomUUID().toString();

        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent(productId,
                productRestModel.getTitle(), productRestModel.getPrice(), productRestModel.getQuantity());
        kafkaTemplate.send("product-created-event-topic", productId, productCreatedEvent);
        return productId;
    }
}
