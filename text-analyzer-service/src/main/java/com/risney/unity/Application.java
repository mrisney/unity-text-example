package com.risney.unity;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Configuration
	class SwaggerConfig {
		@Bean
		public Docket api() {
			return new Docket(DocumentationType.SWAGGER_2).select()
					.apis(RequestHandlerSelectors.basePackage("com.risney.unity.rest")).paths(PathSelectors.any())
					.build().apiInfo(apiInfo());
		}

		private ApiInfo apiInfo() {
			return new ApiInfo("Text Analyzer Exercise REST API",
					"REST endpoints for Unity Text Analyzer exercise, sorting, finding frequency.", "1.0.0", "Terms of service",
					new Contact("Marc Risney","", "marc.risney@gmail.com"), "License of API",
					"https://opensource.org/licenses/MIT", Collections.emptyList());
		}
	}
}