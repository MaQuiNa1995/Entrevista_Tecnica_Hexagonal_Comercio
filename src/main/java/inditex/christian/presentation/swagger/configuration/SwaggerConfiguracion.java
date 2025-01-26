package inditex.christian.presentation.swagger.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguracion {

	@Bean
	public Docket swaggerApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
		        .apis(RequestHandlerSelectors.basePackage("inditex.christian.presentation.product"))
		        .paths(PathSelectors.any())
		        .build()
		        .apiInfo(this.apiInfo());
	}

	private ApiInfo apiInfo() {

		final Contact contacto = new Contact("Prueba Tecnica Comercio", "https://github.com/MaQuiNa1995",
		        "maquina1995@gmail.com");

		return new ApiInfoBuilder().title("Prueba Tecnica Comercio")
		        .version("1.0.0")
		        .contact(contacto)
		        .build();
	}
}