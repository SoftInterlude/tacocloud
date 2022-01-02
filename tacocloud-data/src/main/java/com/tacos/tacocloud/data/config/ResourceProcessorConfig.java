package com.tacos.tacocloud.data.config;

import com.tacos.tacocloud.data.controller.RecentTacosController;
import com.tacos.tacocloud.domain.Taco;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * 添加自定义超链接
 * 书上的过时了 正确写法参考官网
 * https://docs.spring.io/spring-hateoas/docs/current/reference/html/#server.processors
 * 
 * @author insight 
 * @since 2021/7/19
 */
@Configuration
public class ResourceProcessorConfig {
    private static class ResourceProcessor implements RepresentationModelProcessor<CollectionModel<EntityModel<Taco>>> {

        @Override
        public CollectionModel<EntityModel<Taco>> process(CollectionModel<EntityModel<Taco>> model) {
            model.add(
                    linkTo(methodOn(RecentTacosController.class).recentTacos())
                            .withRel("recents")
            );
            return model;
        }
    }

    @Bean
    public ResourceProcessor processor() {
        return new ResourceProcessor();
    }
}