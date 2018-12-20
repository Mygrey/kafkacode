package com.kafka.log.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;


@Repository("consumerconf")
public class ConsumerConf {
    /**
     * 组名 不同组名可以重复消费。例如你先使用了组名A消费了kafka的1000条数据，
     * 但是你还想再次进行消费这1000条数据，并且不想重新去产生，那么这里你只需要更改组名就可以重复消费了
     */
//    public String groupId;

    /**
     * 是否自动提交，默认为true。
     */
    @Value("${enable_auto_commit}")
    public String enable_auto_commit;
    /**
     * 超时时间
     */
    @Value("${session_timeout_ms}")
    public String session_timeout_ms;
    /**
     * 从poll(拉)的回话处理时长
     */
    @Value("${auto_commit_interval_ms}")
    public String auto_commit_interval_ms;
    /**
     * 一次最大拉取的条数
     */
    @Value("${max_poll_records}")
    public String max_poll_records;
    /**
     * 消费规则，默认earliest
     * <p>
     * desc:
     * <p>
     * earliest: 当各分区下有已提交的offset时，从提交的offset开始消费；无提交的offset时，从头开始消费 。
     * latest: 当各分区下有已提交的offset时，从提交的offset开始消费；无提交的offset时，消费新产生的该分区下的数据 。
     * none: topic各分区都存在已提交的offset时，从offset后开始消费；只要有一个分区不存在已提交的offset，则抛出异常。
     */
    @Value("${auto_offset_reset}")
    public String auto_offset_reset;
    /**
     *
     */
    @Value("${offset}")
    public int offset;

    /**
     * zk集群
     * <p>
     * desc:
     * <p>
     * m1:2181,m2:2181,m3:2181
     */
    @Value("${zookeeper_connect}")
    public String zookeeper_connect;
    /**
     * 键序反列化，默认org.apache.kafka.common.serialization.StringDeserializer
     */
    @Value("${key_deserializer}")
    public String key_deserializer;
    /**
     * 数据反列化，默认org.apache.kafka.common.serialization.StringDeserializer
     */
    @Value("${value_deserializer}")
    public String value_deserializer;

}
