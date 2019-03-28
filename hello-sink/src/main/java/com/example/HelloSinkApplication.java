package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@SpringBootApplication
@EnableBinding(Sink.class)
public class HelloSinkApplication {

    private final Logger logger = LoggerFactory.getLogger(HelloSinkApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(HelloSinkApplication.class, args);
    }

    @StreamListener(Sink.INPUT)
    public void print(Tweet tweet) {
        logger.info("Received " + tweet.text);
    }

    public static class Tweet {

        public String text;
    }
}