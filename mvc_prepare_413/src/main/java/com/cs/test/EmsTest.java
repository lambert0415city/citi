package com.cs.test;

import com.cs.ems.SendEmsQueueMessage;
import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import javax.annotation.Resource;
import javax.jms.Destination;

/**
 * @author: szh
 * @since:
 */
public class EmsTest {

    @Resource
    private SendEmsQueueMessage sendEmsQueueMessage1;

    @Test
    public void testEmsQueue() {
        ApplicationContext ac = new ClassPathXmlApplicationContext(
                "spring-config.xml");
        SendEmsQueueMessage sendEmsQueueMessage = (SendEmsQueueMessage) ac.getBean("sendEmsQueueMessage");
        Destination destination = (Destination) ac.getBean("queueDestination");

        String str = "<?xmlversion=\"1.0\"encoding=\"UTF-8>";
//		sendEmsQueueMessage.sendTextMessage(destination,"Text message");
        sendEmsQueueMessage.sendStreamMessage(destination, str.getBytes());

        sendEmsQueueMessage1.sendStreamMessage(destination, str.getBytes());
    }
}
