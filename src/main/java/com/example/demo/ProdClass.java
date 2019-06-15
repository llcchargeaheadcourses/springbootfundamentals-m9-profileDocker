package com.example.demo;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"prod","default"})
public class ProdClass implements ProfileSpecificInterface{

	@Override
	public String getEnv() {
		return "Prod";
	}

}
