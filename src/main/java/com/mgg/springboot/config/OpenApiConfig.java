package com.mgg.springboot.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.hibernate.validator.HibernateValidator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.validation.beanvalidation.SpringConstraintValidatorFactory;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("Contact Application API").description(
                        "This is a sample Spring Boot RESTful service using springdoc-openapi and OpenAPI 3."));
    }

    // @Bean
    // public Validator validator (final AutowireCapableBeanFactory autowireCapableBeanFactory) {
    //     System.out.println("contol");
    //     ValidatorFactory validatorFactory = Validation.byProvider( HibernateValidator.class )
    //         .configure().constraintValidatorFactory(new SpringConstraintValidatorFactory(autowireCapableBeanFactory))
    //         .buildValidatorFactory();
    //     Validator validator = validatorFactory.getValidator();

    //     return validator;
    // }

        @Bean
        public Validator validator() {
            System.out.println("contol1x");
            return new LocalValidatorFactoryBean();
        }
        // @Bean
        // public LocalValidatorFactoryBean validator() {
        //     System.out.println("contol1");

        //     return new LocalValidatorFactoryBean();
        // }

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor(Validator validator) {
        System.out.println("contol2");

        MethodValidationPostProcessor methodValidationPostProcessor = new MethodValidationPostProcessor();
        methodValidationPostProcessor.setValidator(validator);
        return methodValidationPostProcessor;
    }
}
