package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableBinding(Source.class)
public class HelloSourceApplication {

	private final Source source;

	public HelloSourceApplication(Source source) {
		this.source = source;
	}

	@PostMapping
	public void tweet(@RequestBody Tweet tweet) {
		source.output().send(MessageBuilder.withPayload(tweet).build());
	}

	public static void main(String[] args) {
		SpringApplication.run(HelloSourceApplication.class, args);
	}

	public static class Tweet {
		public String text;
	}
}