package com.cs.ems;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.StreamMessage;
import javax.jms.TextMessage;
/**
 * @author: szh
 * @since:
 */
public class ConsumerMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        if (message instanceof javax.jms.TextMessage) {
            TextMessage textMsg = (TextMessage) message;
            try {
                System.out.println("The receiver received a message:" + textMsg.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
        else if(message instanceof javax.jms.StreamMessage){
            StreamMessage streamMsg = (StreamMessage)message;
            try {
                int length = message.getIntProperty("length");
                byte[] content = new byte[length];
                if(streamMsg.readBytes(content)>0)
                {
                    String str = new String(content);
                    System.out.println("The message length is:" + length);
                    System.out.println("The receiver received a message:" + str);
                }
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
