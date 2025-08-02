package org.yisus.spring.users.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfiguration {
    @Autowired
    private Environment env;

    public Map<String,Object> consumerProps(){
        Map<String,Object> props= new HashMap<>();

        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,env.getProperty("kafka.config.consumer.bootstrap.servers"));
        props.put(ConsumerConfig.GROUP_ID_CONFIG,env.getProperty("kafka.config.consumer.group.id"));
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,env.getProperty("kafka.config.consumer.enable.auto.commit"));
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,env.getProperty("kafka.config.consumer.auto.commit.interval.ms"));
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG,env.getProperty("kafka.config.consumer.session.timeout.ms"));
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return props;
    }

    @Bean
    public ConsumerFactory<Integer,String> consumerFactory(){
        return new DefaultKafkaConsumerFactory<>(consumerProps());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<Integer,String> kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<Integer, String> listener= new ConcurrentKafkaListenerContainerFactory<Integer,String>();
        listener.setConsumerFactory(consumerFactory());
        return listener;
    }

    public Map<String,Object> producerProps(){
        Map<String,Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, env.getProperty("kafka.config.consumer.bootstrap.servers"));
        props.put(ProducerConfig.RETRIES_CONFIG, env.getProperty("kafka.config.producer.retries"));
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, env.getProperty("kafka.config.producer.batch.size"));
        props.put(ProducerConfig.LINGER_MS_CONFIG, env.getProperty("kafka.config.producer.linger.ms"));
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, env.getProperty("kafka.config.producer.buffer.memory"));
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return props;
    }

    @Bean
    public KafkaTemplate<Integer,String> kafkaTemplate(){
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(producerProps()));
    }
}
