package com.kafka.log.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository("topicsconf")
public class TopicConf {

    @Value("${topic_retry}")
    public String topicRetry;
}
