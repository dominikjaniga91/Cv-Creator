package com.cvgenerator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@EnableSwagger2
@Configuration
public class SwaggerConfig {


        @Bean
        public Docket getDocket(){

            return new Docket(DocumentationType.SWAGGER_2)
                    .select()
                    .paths(PathSelectors.ant("/api/**"))
                    .build()
                    .apiInfo(createApiInfo());
        }

        private ApiInfo createApiInfo(){

            Contact contact = new Contact("CV builder", " ", "dominikjaniga91@gmail.com");
            return new ApiInfo("CV builder",
                                "Application build to create and manage personal resumes",
                                "1.0",
                                "http://cvbuilder.com",
                                contact,
                                " ",
                                " ",
                                Collections.emptyList());
        }
}

