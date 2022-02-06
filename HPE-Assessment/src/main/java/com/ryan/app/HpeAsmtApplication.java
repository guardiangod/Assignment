package com.ryan.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication(scanBasePackages = "com.ryan.app")
@PropertySources({
    @PropertySource("classpath:application.properties"),
    @PropertySource("classpath:h2-query.properties")
})
public class HpeAsmtApplication {

	public static void main(String[] args) {
		SpringApplication.run(HpeAsmtApplication.class, args);
	}
}
