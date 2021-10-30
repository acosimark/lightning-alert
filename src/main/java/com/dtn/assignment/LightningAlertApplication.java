package com.dtn.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LightningAlertApplication {

	public static void main(String[] args) {
		SpringApplication.run(LightningAlertApplication.class, args);
	}
	//
	// @Bean
	// public PromptProvider myPromptProvider() {
	// return () -> new AttributedString("my-shell:>",
	// AttributedStyle.DEFAULT.foreground(AttributedStyle.YELLOW));
	// }

}
