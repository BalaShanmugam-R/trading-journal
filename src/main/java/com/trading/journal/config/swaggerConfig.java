package com.trading.journal.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class swaggerConfig {

    @Bean
    public OpenAPI app() {
        final Contact coreEngineeringContact = new Contact();
        coreEngineeringContact.setEmail("balashan.1999@gmail.com");
        coreEngineeringContact.setName("Balashanmugam Ravichelvan");

        return new OpenAPI()
                .info(new Info()
                        .title("Trading Journal")
                        .version("1.0.0")
                        .description("This Application tracks records of trading.")
                        .contact(coreEngineeringContact));
    }

}
