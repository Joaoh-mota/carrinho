package com.example.atv10Web2;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Configuration indica ao Spring que essa Ã© uma classe de configuraÃ§Ã£o. Em
 * seguida, Ã© preciso estender a classe WebMvcConfigurer.
 * @author fagno
 */
@Configuration
public class ConfiguracaoSpringMvc implements WebMvcConfigurer {

    /**
     * Com a chamada a registry.addViewController(), estamos registrando um
     * controller automÃ¡tico. Com o mÃ©todo setViewName(), indicamos a view
     * para atender a requisiÃ§Ãµes direcionadas Ã  URL / e /home.
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home/index");
        registry.addViewController("/home").setViewName("home/index");

    }

}
