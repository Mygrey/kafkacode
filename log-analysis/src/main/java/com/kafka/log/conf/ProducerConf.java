package com.kafka.log.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

/**
 * 生产者参数配置
 * 访问官网API查看各参数的意义
 */

@Repository("producerconf")
public class ProducerConf {



    @Value("${acks_conf}")
    public String acks_conf;

    @Value("${retries}")
    public String retries;

    @Value("${batch_size}")
    public String batch_size;

    @Value("${linger_ms}")
    public String linger_ms;

    @Value("${buffer_memory}")
    public String buffer_memory;

    @Value("${key_serializer}")
    public String key_serializer;

    @Value("${value_serializer}")
    public String value_serializer;

    //如果设置为非String类型，Properties在put完后，使用get方法得到的可能为null
}
