package com.example.TaskManagerService;

import com.example.TaskManagerService.filter.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaServer
@EnableFeignClients
public class TaskManagerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskManagerServiceApplication.class, args);
	}
	public FilterRegistrationBean filterBeand() {
		FilterRegistrationBean filterreg = new FilterRegistrationBean();
		filterreg.setFilter(new JwtFilter());
		filterreg.addUrlPatterns("/api/v1/*");
		return filterreg;
	}

}
