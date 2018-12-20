package com.kafka.log.common;

import com.kafka.log.conf.ConsumerConf;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

public class LogConsumer extends  Thread {
    private String broker_server="";
    private ConsumerConf consumer_conf;
    private String topic;
    private String groupId;

    public LogConsumer(String server,String topic,String group,ConsumerConf cc){
        this.broker_server=server;
        this.topic=topic;
        this.consumer_conf=cc;
        this.groupId=group;
    }


    public void logconsumer(String flag){

        Properties properties = new Properties();
        properties.put("bootstrap.servers", "10.1.80.8:9092,10.1.80.9:9092,10.1.80.4:9092");
        properties.put("group.id", "test-group");
        properties.put("enable.auto.commit", "true");
        properties.put("auto.commit.interval.ms", "1000");
        properties.put("auto.offset.reset", "earliest");
        properties.put("session.timeout.ms", "30000");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);
        kafkaConsumer.subscribe(Arrays.asList("dell-topic"));
        while (true) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("======="+flag+"======");
                System.out.printf("offset = %d, value = %s", record.offset(), record.value());
                System.out.println();
            }
        }
    }


    @Override
    public void run(){

        InitProperties init = new InitProperties();
        Properties prop = init.getConsumerProp(this.consumer_conf);
        prop.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,this.broker_server);
        prop.put(ConsumerConfig.GROUP_ID_CONFIG,this.groupId);
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(prop);
        kafkaConsumer.subscribe(Arrays.asList(this.topic));
        while(true){
            ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("offset = %d, value = %s, partition=%s, topic=%s", record.offset(), record.value(),record.partition(),record.topic());
                System.out.println(Thread.currentThread());
            }
        }




    }

}
