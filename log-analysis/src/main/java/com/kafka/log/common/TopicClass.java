package com.kafka.log.common;


import com.kafka.log.conf.TopicConf;
import org.apache.kafka.clients.admin.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class TopicClass {
    private TopicConf conf;
    private String server;

    public TopicClass(String brokerServer,TopicConf tc){
        this.conf=tc;
        this.server=brokerServer;
    }


    /**
     * 单个创建topic
     * @param name
     * @param partition
     * @param relicas
     */
    public void createTopic(String name,int partition,short relicas){

        InitProperties init = new InitProperties();
        Properties prop = init.getTopicProp(this.conf);
        prop.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,this.server);

        NewTopic nt=new NewTopic(name,partition,relicas);
        Collection<NewTopic> topicArr = new ArrayList<NewTopic>();
        ((ArrayList<NewTopic>) topicArr).add(nt);

        CreateTopicsResult result = AdminClient.create(prop).createTopics(topicArr);

        try {
            result.all().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }



    }
}
