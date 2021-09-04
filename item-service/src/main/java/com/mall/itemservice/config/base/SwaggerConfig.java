package com.mall.itemservice.config.base;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toSet;

/**
 * swagger 3.0 부터 ui 접근 url이 바뀜
 * http://localhost:port/swagger-ui/index.html
 */
@Configuration
@Profile({"!live"})
public class SwaggerConfig {

    private final Class[] ignoredParameterTypes = {
            ServerWebExchange.class,
            Resource.class,
            ResponseEntity.class,
            Mono.class,
            Flux.class
    };

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30) // open api spec 3.0
                .select()
                //.apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.mall.itemservice.process.item.controller"))
                //.paths(PathSelectors.ant("/api/**"))
                .paths(PathSelectors.any())
                .build()
                .genericModelSubstitutes(Mono.class, Flux.class)
                .useDefaultResponseMessages(false)
                .produces(Arrays.asList("application/json;charset=UTF-8").stream().collect(toSet()))
                .ignoredParameterTypes(ignoredParameterTypes)
                //.directModelSubstitute(PaginationRequest.class, PaginationRequest.SwaggerModel.class)
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        final String title = "item-service";
        final String description = "item-service guide book";
        final String version = "1.0";
        final String termOfServiceUrl = null;
        final String contactName = "kyo";
        final String contactUrl = "https://github.com/kyoDaddy";
        final String contactEmail = "rlarbghrbgh@gmail.com";
        final String license = null;
        final String licenseUrl = null;
        return new ApiInfo(
                title,
                description,
                version,
                termOfServiceUrl,
                new Contact(contactName, contactUrl, contactEmail),
                license,
                licenseUrl,
                Collections.emptyList()
        );
    }


    @Bean
    public UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder() //<20>
                .deepLinking(true)
                .displayOperationId(false)
                .defaultModelsExpandDepth(1)
                .defaultModelExpandDepth(1)
                .defaultModelRendering(ModelRendering.MODEL)
                .displayRequestDuration(false)
                .docExpansion(DocExpansion.LIST)
                .filter(false)
                .maxDisplayedTags(null)
                .operationsSorter(OperationsSorter.ALPHA)
                .showExtensions(false)
                .showCommonExtensions(false)
                .tagsSorter(TagsSorter.ALPHA)
                .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
                .validatorUrl(null)
                .build();
    }

}
