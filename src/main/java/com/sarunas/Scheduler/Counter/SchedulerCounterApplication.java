package com.sarunas.Scheduler.Counter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication
public class SchedulerCounterApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchedulerCounterApplication.class, args);
	}
}
