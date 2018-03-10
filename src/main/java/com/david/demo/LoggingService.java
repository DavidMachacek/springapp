package com.david.demo;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.monitor.LocalMapStats;

@Aspect
@Component
public class LoggingService {
    private static final Logger log = LoggerFactory.getLogger(LoggingService.class);
    private final HazelcastInstance hazelcastInstance;

    public LoggingService(@Qualifier("hazelcastInstance") HazelcastInstance hazelcastInstance) {

        this.hazelcastInstance = hazelcastInstance;
    }

    @Before("@annotation(LogThis)")
    public void logCacheBefore() {
        logCache("=== BEFORE ASPECT ===");
    }

    @After("@annotation(LogThis)")
    public void logCacheAfter() {
        logCache("=== AFTER ASPECT ===");
    }

    private void logCache(String description) {
        LocalMapStats localMapStats = hazelcastInstance.getMap("customer-cache").getLocalMapStats();
        System.out.println(description);
        System.out.println("Backup count: " + localMapStats.getBackupCount());
        System.out.println("Hits count: " + localMapStats.getHits());
    }
}
