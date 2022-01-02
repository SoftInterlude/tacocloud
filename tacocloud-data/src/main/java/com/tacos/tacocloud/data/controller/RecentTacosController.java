package com.tacos.tacocloud.data.controller;

import com.tacos.tacocloud.data.repository.TacoRepository;
import com.tacos.tacocloud.domain.Taco;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 1. RepositoryRestController注解能将自定义控制器映射到Spring Data Rest的基础路径中
 * 2. 尽管RepositoryRestController与RestController语义相近，但是前者不能将控制器的方法返回值自动装在ResponseBody
 *    需要手动手动包装返回值到ResponseEntity或者使用ResponseBody注解
 */
@RepositoryRestController
@RequestMapping("/api/tacos")
public class RecentTacosController {

    private TacoRepository tacoRepository;

    public RecentTacosController(TacoRepository tacoRepository) {
        this.tacoRepository = tacoRepository;
    }

    @GetMapping(path = "/recent", produces = "application/hal+json")
    public ResponseEntity<List<Taco>> recentTacos() {
        PageRequest pageRequest = PageRequest.of(0, 12, Sort.by("createdAt").descending());
        List<Taco> tacos = tacoRepository.findAll(pageRequest).getContent();
        /*CollectionModel<EntityModel<Taco>> recentTacos = CollectionModel.wrap(tacos);
        recentTacos.add(
            WebMvcLinkBuilder.linkTo(
                            WebMvcLinkBuilder.methodOn(
                                    RecentTacosController.class).recentTacos()).
                    withRel("recents"));*/

        return new ResponseEntity<>(tacos, HttpStatus.OK);
    }

}
