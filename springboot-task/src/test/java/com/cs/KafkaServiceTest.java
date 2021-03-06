package com.cs;





import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;


/**
 * @author: szh
 * @since:
 */

@SpringBootTest
class KafkaServiceTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private KafkaTemplate<String,String> kafkaTemplate;


    @Test
    @Scheduled(cron = "0/2 * * * * ?")
    void send() {
        kafkaTemplate.send("topic1","testsend1/11");
    }

//    bin/kafka-console-producer.sh --broker-list 192.168.60.128:9092 --topic mq
//    bin/kafka-console-consumer.sh --bootstrap-server 192.168.60.128:9092 --topic mq --from-beginning

//    @Test
//    @KafkaListener(topics = {"mq"})
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