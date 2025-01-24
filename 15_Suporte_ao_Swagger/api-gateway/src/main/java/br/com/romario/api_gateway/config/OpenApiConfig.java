package br.com.romario.api_gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {
	
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
					.title("RESTFull API com java 21 e Spring Boot 3")
					.version("v1")
					.description("Alguma descrição sobre sua API")
					.termsOfService("https://www.exemplo-termos-serviços.com")
					.license(new License()
						.name("Apache 2.0")
						.url("https://www.exemplo-termos-licença.com")));
		
		//http://localhost:8080/swagger-ui/index.html
	}
}
