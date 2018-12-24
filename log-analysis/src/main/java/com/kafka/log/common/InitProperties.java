package com.kafka.log.common;

import com.kafka.log.conf.ConsumerConf;
import com.kafka.log.conf.ProducerConf;
import com.kafka.log.conf.TopicConf;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.TopicConfig;

import java.util.Properties;

public class InitProperties {

    /**
     * bootstrap.server的配置不放在通用的配置当中，方便创建时指定对应的broker
     * @param producer_conf
     * @return
     */
    public Properties getProducerProp(ProducerConf producer_conf){
        Properties prop1=new Properties();
//        properties.put("bootstrap.servers", "10.1.80.8:9092,10.1.80.9:9092,10.1.80.4:9092");
//        properties.put("acks", "all");
//        properties.put("retries", 0);
//        properties.put("batch.size", 16384);
//        properties.put("linger.ms", 1);
//        properties.put("buffer.memory", 33554432);
//        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        prop1.put(ProducerConfig.ACKS_CONFIG,producer_conf.acks_conf);
        prop1.put(ProducerConfig.RETRIES_CONFIG,producer_conf.retries);
        prop1.put(ProducerConfig.BATCH_SIZE_CONFIG,producer_conf.batch_size);
        prop1.put(ProducerConfig.LINGER_MS_CONFIG,producer_conf.linger_ms);
        prop1.put(ProducerConfig.BUFFER_MEMORY_CONFIG,producer_conf.buffer_memory);
        prop1.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,producer_conf.key_serializer);
        prop1.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,producer_conf.value_serializer);

        /**
         *  下面的两个get方法，前者主要分为两步，先执行get方法，再进行类型转化，如果put进去的是int类型，第二步异常，取null
         *  Object oval = super.get(key);
         *  String sval = (oval instanceof String) ? (String)oval : null;
         */
        //System.out.println(producer_conf.retries+"******"+prop1.getProperty(ProducerConfig.RETRIES_CONFIG));
        //输出:0******null
        //System.out.println(producer_conf.retries+"******"+prop1.get(ProducerConfig.RETRIES_CONFIG));
        //输出:0******0
        return prop1;

    }


    public Properties getConsumerProp(ConsumerConf consumer_conf){
        Properties prop=new Properties();
        prop.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,consumer_conf.enable_auto_commit);
        prop.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,consumer_conf.auto_commit_interval_ms);
        prop.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,consumer_conf.auto_offset_reset);
        prop.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG,consumer_conf.session_timeout_ms);
        prop.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,consumer_conf.key_deserializer);
        prop.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,consumer_conf.value_deserializer);
        return prop;
    }


    /**
     *
     * AdminClient初始化中，配置传入为new AdminClientConfig(props)
     * @param topic_conf
     * @return
     */
    public Properties getTopicProp(TopicConf topic_conf){
        Properties prop = new Properties();
        prop.put(AdminClientConfig.RETRIES_CONFIG,topic_conf.topicRetry);
        return prop;
    }

}
