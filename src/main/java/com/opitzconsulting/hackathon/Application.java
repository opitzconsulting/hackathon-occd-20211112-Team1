package com.opitzconsulting.hackathon;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
		info = @Info(
				title = "Wallbox Consumption API",
				version = "0.1",
				description = "REST API to request Wallbox for consumption information",
				license = @License(name = "Apache 2.0", url = "https://opitz-consulting.com"),
				contact = @Contact(url = "https://opitz-consulting.com", name = "John Doe",
						email = "John,Doe@opitz-consulting.com")
		)
)
public class Application {
	
	public static void main(String[] args) {
		Micronaut.run(Application.class, args);
	}
}
