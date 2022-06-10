package com.webshop.webshop.configs.swagger;

import com.webshop.webshop.configs.ApplicationProperties;
import com.webshop.webshop.configs.security.annotations.AuthenticatedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import com.google.common.collect.Lists;
import springfox.documentation.spi.service.contexts.SecurityContext;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Autowired
    private ApplicationProperties applicationProperties;

    private ApiInfo apiInfo() {
        return new ApiInfo(this.applicationProperties.getSwagger().getTitle(),
                this.applicationProperties.getSwagger().getDescription(),
                this.applicationProperties.getSwagger().getVersion(),
                this.applicationProperties.getSwagger().getTermsOfServiceUrl(),
                new Contact(
                        this.applicationProperties.getSwagger().getContactName(),
                        this.applicationProperties.getSwagger().getContactUrl(),
                        this.applicationProperties.getSwagger().getContactEmail()
                ),
                this.applicationProperties.getSwagger().getLicence(),
                this.applicationProperties.getSwagger().getLicenceUrl(),
                Collections.emptyList());
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(new SecurityReference("JWT", authorizationScopes));
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("/api/.*"))
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.webshop.webshop.web.rest"))
                .paths(PathSelectors.ant("/api/**")).build().apiInfo(apiInfo())
                .securityContexts(Lists.newArrayList(securityContext()))
                .ignoredParameterTypes(AuthenticatedUser.class)
                .securitySchemes(Lists.newArrayList(apiKey()));
    }
}
