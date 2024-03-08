//package com.niiit.muzix.swaggar.config;
//
//import java.util.Collections;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Import;
//
//
//
//@EnableSwagger2
//@Import(SpringDataRestConfiguration.class)
//
//public class SwaggarConfig {
//	@Bean
//	public Docket api() {                
//	    return new Docket(DocumentationType.SWAGGER_2)          
//	      .select()
//	      .apis(RequestHandlerSelectors.basePackage("com.niiit.muzix.controller"))
//	      .paths(PathSelectors.ant("/"))
//	      .build()
//	      .apiInfo(apiInfo());
//	}
//
//	private ApiInfo apiInfo() {
//	    return new ApiInfo(
//	      "My REST API", 
//	      "Some custom description of API.", 
//	      "API TOS", 
//	      "Terms of service", 
//	      new Contact("John Doe", "www.example.com", "myeaddress@company.com"), 
//	      "License of API", "API license URL", Collections.emptyList());
//	}
//}
