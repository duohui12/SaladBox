package com.chunjae.saladbox;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class SaladboxApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaladboxApplication.class, args);
	}

	@Bean
	public Mapper mapper() {
		return DozerBeanMapperBuilder.buildDefault();
	}

}
