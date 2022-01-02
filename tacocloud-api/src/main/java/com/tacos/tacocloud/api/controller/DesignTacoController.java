package com.tacos.tacocloud.api.controller;

import com.tacos.tacocloud.api.hateoas.TacoResource;
import com.tacos.tacocloud.api.hateoas.TacoResourceAssembler;
import com.tacos.tacocloud.data.repository.TacoRepository;
import com.tacos.tacocloud.domain.Taco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "design",
                produces = "application/json")
@CrossOrigin(origins = "*")
public class DesignTacoController {

  private TacoRepository tacoRepository;

  @Autowired
  EntityLinks entityLinks;

  public DesignTacoController(TacoRepository tacoRepository) {
    this.tacoRepository = tacoRepository;
  }

  /*@GetMapping("/recent")
  public Iterable<Taco> recentTacos() {
    PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
    return tacoRepository.findAll(page);
  }*/

  /*使用HATEOAS为资源添加超链接*/
  /*将领域模型转换为携带资源链接的结合, 并且添加Link的硬编码连接*/
  /*@GetMapping("/recent")
  public CollectionModel<EntityModel<Taco>> recentTacos() {
    PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
    List<Taco> tacos = tacoRepository.findAll(page).getContent();
    CollectionModel<EntityModel<Taco>> recentTacos = CollectionModel.wrap(tacos);
    *//*recentTacos.add(Link.of("http://localhost:8080/app/design/recent", "recents"));*//*

    *//*使用WebMvcLinkBuilder取消部分硬编码*//*
    *//*recentTacos.add(WebMvcLinkBuilder.linkTo(DesignTacoController.class).slash("recent").withRel("recents"));*//*

    *//*使用WebMvcLinkBuilder的linkTo和methodOn方法完全取消硬编码*//*
    *//*recentTacos.add(
        WebMvcLinkBuilder.linkTo(
            WebMvcLinkBuilder.methodOn(
                DesignTacoController.class).recentTacos()).
            withRel("recents"));*//*


    return recentTacos;
  }*/

  /*使用TacoResourceAssembler为集合中的每个资源都添加链接，是将Taco对象转换为TacoResource的工具类*/
  @GetMapping("/recent")
  public CollectionModel<TacoResource> recentTacos() {
    PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
    List<Taco> tacos = tacoRepository.findAll(page).getContent();
    CollectionModel<TacoResource> tacoResourceCollectionModel = new TacoResourceAssembler().toCollectionModel(tacos);
    tacoResourceCollectionModel.add(
        WebMvcLinkBuilder.linkTo(
            WebMvcLinkBuilder.methodOn(
                DesignTacoController.class).recentTacos()).withRel("recents"));
    return tacoResourceCollectionModel;
  }

  @GetMapping("/{id}")
  public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
    Optional<Taco> optionalTaco = tacoRepository.findById(id);
    return optionalTaco.map(taco -> new ResponseEntity<>(taco, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
  }

  @PostMapping(consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public Taco postTaco(@RequestBody Taco taco) {
    return tacoRepository.save(taco);
  }
}
