package com.david.demo.cache;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.GroupConfig;
import com.hazelcast.config.MapConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

@Configuration
public class HazelcastConfiguration {

    @Bean
    public Config hazelcastInstanceConfig() {
        return new Config().setInstanceName("demo-hazelcast-instance")
                .setGroupConfig(new GroupConfig("demo-hazelcast-group", "s3cr3t"))
                .addMapConfig(new MapConfig().setName("customer-cache")
                        .setEvictionPolicy(EvictionPolicy.LRU).setTimeToLiveSeconds(2000));
    }

    @Bean
    public HazelcastInstance hazelcastInstance(Config hazelcastInstanceConfig) {
        return Hazelcast.newHazelcastInstance(hazelcastInstanceConfig);
    }



}
