package com.kafka.log;

import com.kafka.log.common.LogConsumer;
import com.kafka.log.common.LogProducer;
import com.kafka.log.conf.ConsumerConf;
import com.kafka.log.conf.ProducerConf;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class logAction {
    public static void main(String[] args) {

        String server="10.1.80.4:9092,10.1.80.8:9092,10.1.80.9:9092";

        ApplicationContext app = new ClassPathXmlApplicationContext("kafkaconf.xml");
        ConsumerConf cc= (ConsumerConf) app.getBean("consumerconf");
        ProducerConf pc= (ProducerConf) app.getBean("producerconf");

//        LogProducer prod1=new LogProducer(server,"dell-topic",pc);
//        LogProducer prod2=new LogProducer(server,"dell-topic",pc);
//        LogProducer prod3=new LogProducer(server,"one-topic",pc);
//        prod1.start();
//        prod2.start();
//        prod3.start();


        LogConsumer consu1 = new LogConsumer(server,"dell-topic","test-group",cc);
        LogConsumer consu2 = new LogConsumer(server,"dell-topic","test-group",cc);
        LogConsumer consu3 = new LogConsumer(server,"one-topic","test-group",cc);
        consu1.start();
        consu2.start();
        consu3.start();


    }
}
