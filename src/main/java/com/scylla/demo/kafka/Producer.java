package com.scylla.demo.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class Producer {

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);

    @Autowired
    private Environment env;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        logger.info(String.format("#### -> Producing message -> %s", message));

        String key = message;
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate
                .send(env.getProperty("kafka.topic"),key,message);

        future.addCallback(
                new ListenableFutureCallback<SendResult<String, String>>() {

                    @Override
                    public void onFailure(Throwable ex) {
                        logger.info(String.format("Message key: %s FAILED", key));

                    }

                    @Override
                    public void onSuccess(SendResult<String, String> result) {
                        logger.info(String.format("Message key: %s SUCCEED", key));

                    }
                });
    }

    public void transaction(String message) {
        kafkaTemplate.executeInTransaction(kafkaTemplate -> {
            StringUtils.commaDelimitedListToSet(message).stream()
                    .forEach(foo -> kafkaTemplate.send(env.getProperty("kafka.topic"), foo));
            return null;
        });
    }
}
