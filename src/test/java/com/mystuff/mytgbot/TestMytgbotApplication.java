package com.mystuff.mytgbot;

import org.springframework.boot.SpringApplication;

public class TestMytgbotApplication {

	public static void main(String[] args) {
		SpringApplication.from(MytgbotApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
