package in.solinas.custom_actuator.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static in.solinas.custom_actuator.constants.SwaggerConstants.*;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI solinasActuatorOPenAPI() {
        return new OpenAPI()
                .info(new Info().title(TITLE)
                                .version(VERSION)
                                .description(DESCRIPTION)
                                .license(new License().name(LICENSE).url(WEBURL))
                );
    }
}
