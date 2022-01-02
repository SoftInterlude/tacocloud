package com.tacos.tacocloud.api.hateoas;

import com.tacos.tacocloud.domain.Taco;
import lombok.Getter;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.Date;

/**
 * 使用Relation注解能够帮助我们解耦JSON字段名和Java代码中定义的资源类名
 */
//@Relation(value = "taco", collectionRelation = "tacos")
@Getter
public class TacoResource extends RepresentationModel<TacoResource> {

  private static final IngredientResourceAssembler ingredientResourceAssembler = new IngredientResourceAssembler();

  private final String name;

  private final Date createdAt;

  private final CollectionModel<IngredientResource> ingredients;

  public TacoResource(Taco taco) {
    this.name = taco.getName();
    this.createdAt = taco.getCreatedAt();
    this.ingredients = ingredientResourceAssembler.toCollectionModel(taco.getIngredients());
  }
}
