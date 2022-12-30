package com.binar.bejticketing.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@SecurityScheme(name = "Authorize", scheme = "bearer", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER )
public class SwaggerConfig {

    @Bean
    public OpenAPI bejTicketing() {
        return new OpenAPI().info(new Info()
                .title("7 Airways")
                .description("REST API untuk Aplikasi 7 Airways - Kelompok 7")
                .version("0.1")


        ).servers(List.of(new Server().url("https://bej-ticketing-production.up.railway.app/")));
    }




}
