package com.example.demo;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class DevClass implements ProfileSpecificInterface{

	@Override
	public String getEnv() {
		return "Dev";
	}

}
