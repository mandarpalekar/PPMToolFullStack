package com.reactspring.ppmtool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {

    @Bean
    public Docket swaggerConfiguration() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.ant("/api/project/**/*"))
                .apis(RequestHandlerSelectors.basePackage("com.reactspring.ppmtool"))
                .build()
                .apiInfo(apiDetails());
    }

    private ApiInfo apiDetails() {
        return new ApiInfo( "PPM Tool REST Web Service documentation",
                "This pages documents Photo app RESTful Web Service endpoints",
                "1.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                 new Contact("Mandar Palekar", "https://www.linkedin.com/in/mandar-palekar-92401523/","mandarpalekar@gmail.com"),
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                Collections.emptyList());
    }
}
