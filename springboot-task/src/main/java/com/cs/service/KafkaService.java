package com.cs.service;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;

@Service
public class KafkaService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private KafkaTemplate<String,String> kafkaTemplate;

    //@Scheduled(cron = "0/5 * * * * ?")
    public void send(){
        kafkaTemplate.send("mq","haha");
        logger.info("sent messages to kafka");
    }

    @KafkaListener(topics = {"mq"},groupId = "0")
    public void acceptMQ(String message){
        System.out.println("message:" + message);
    }

//    @KafkaListener(topics = {"${log.df-kafkaconsumer.topic}"})
//    public void listen (ConsumerRecord<?, ?> record, Acknowledgment acknowledgment, Consumer<?, ?> consumer){
//        try {
//            String logStr = (String) record.value();
//            acknowledgment.acknowledge();
//        } catch (Exception e) {
//            e.printStackTrace();
//            logger.error("kafka消息消费失败", e);
//        }
//    }
}
