package com.scylla.demo.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    private final Logger logger = LoggerFactory.getLogger(Producer.class);

    @Bean
    public ConcurrentKafkaListenerContainerFactory kafkaListenerContainerFactory(
            ConcurrentKafkaListenerContainerFactoryConfigurer configurer,
            ConsumerFactory<Object, Object> kafkaConsumerFactory) {
        ConcurrentKafkaListenerContainerFactory<Object, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        configurer.configure(factory, kafkaConsumerFactory);
        factory.setErrorHandler(new SeekToCurrentErrorHandler()); // <<<<<<
        return factory;
    }

    @Autowired
    private final ObjectMapper mapper = new ObjectMapper();

    @KafkaListener(topics = {"${kafka.topic}"}, groupId = "group_id")
    public void consume(ConsumerRecord<String, String> data, Acknowledgment acknowledgment) {
        logger.info(String.format("#### -> Consumed message -> %s", data.value()));

        try{
            logger.info("Read Record is : " + data.value());
            logger.info("Offset is : " + data.offset());
            logger.info("Topic is : " + data.topic());
            logger.info("Partition is : " + data.partition());

        }catch (Exception e ){
            logger.info("Push the messaged to Error Stream : " + e);
        }finally{
            acknowledgment.acknowledge();
        }
    }

}
