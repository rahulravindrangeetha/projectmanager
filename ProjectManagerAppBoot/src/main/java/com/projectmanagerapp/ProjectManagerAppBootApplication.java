package com.projectmanagerapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

@EnableCaching
@SpringBootApplication
public class ProjectManagerAppBootApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(ProjectManagerAppBootApplication.class, args);
	}

}

