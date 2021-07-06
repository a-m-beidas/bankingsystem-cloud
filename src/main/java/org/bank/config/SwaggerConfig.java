package org.bank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2()
public class SwaggerConfig {

    @Bean
    public Docket d1() {

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Root")
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/*"))
                .build();
    }

    @Bean
    public Docket d2() {

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Transactions")
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/transactions/**"))
                .build();
    }

    @Bean
    public Docket d3() {

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Admin")
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/admin/**"))
                .build();
    }
}
