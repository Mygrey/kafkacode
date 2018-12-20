package com.kafka.log.common;

import com.kafka.log.conf.ProducerConf;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import java.util.Properties;

public class LogProducer extends Thread {
    private String broker_server="";
    private ProducerConf producer_conf;
    private String topic;

        public LogProducer(String server,String topic,ProducerConf conf){
            this .broker_server=server;
            this.producer_conf=conf;
            this.topic=topic;

        }

       //测试方法
        public void logProducer(){
            Properties properties = new Properties();
            //相当于命令行中broker-list
            properties.put("bootstrap.servers", "10.1.80.8:9092,10.1.80.9:9092,10.1.80.4:9092");
            properties.put("acks", "all");
            properties.put("retries", 0);
            properties.put("batch.size", 16384);
            properties.put("linger.ms", 1);
            properties.put("buffer.memory", 33554432);
            properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            Producer<String, String> producer = null;
            try {
                producer = new KafkaProducer<String, String>(properties);
                for (int i = 0; i < 20; i++) {
                    String msg = "=============== " + i;
                    producer.send(new ProducerRecord<String, String>("dell-topic", msg));
                    System.out.println("Sent:" + msg);
                   // wait(10);
                }
            } catch (Exception e) {
                e.printStackTrace();

            } finally {
                producer.close();
            }
        }

        //多线程方法
        @Override
        public void run(){
         InitProperties init=new InitProperties();
         Properties prop=init.getProducerProp(this.producer_conf);
         prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,this.broker_server);
         Producer<String,String> producer=new KafkaProducer<String, String>(prop);

            try {
                for(int i=0;i<=20;i++){
                    String message="======"+Thread.currentThread()+"===="+i;
                    producer.send(new ProducerRecord<String,String>(this.topic,message));
                    System.out.println(message);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                /**
                 * 必须要有，用于关闭拦截器，否则consumer接收不到消息
                 * Kafka producer拦截器(interceptor)
                 */
                producer.close();
            }

        }




}
