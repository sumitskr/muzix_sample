package com.niiit.muzix;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.niiit.muzix.filter.JwtFilter;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;


@SpringBootApplication
public class MuzixSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(MuzixSampleApplication.class, args);
		
	}
	@Bean
	public FilterRegistrationBean jwtFilter()
	{
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new JwtFilter());
		filterRegistrationBean.addUrlPatterns("/muzix/api/v1/authenticated/*");
		return filterRegistrationBean;

	}
	 @Bean
	    public OpenAPI openApiConfig() {
	        return new OpenAPI().info(apiInfo()).components(new Components()
	        		          .addSecuritySchemes("bearer-key",
	        		          new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
	        		.addSecurityItem(
	                        new SecurityRequirement()
	                                .addList("bearer-jwt", Arrays.asList("read", "write"))
	                                .addList("bearer-key", Collections.emptyList())
	                );
	    }

	    public Info apiInfo() {
	        Info info = new Info();
	        info
	                .title("Muzix")
	                .description("Muzix App")
	                .version("v1.0.0");
	                 
	        return info;
	    }

	
}
