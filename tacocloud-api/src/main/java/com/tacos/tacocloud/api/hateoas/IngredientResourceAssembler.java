package com.tacos.tacocloud.api.hateoas;

import com.tacos.tacocloud.api.controller.IngredientController;
import com.tacos.tacocloud.domain.TacoIngredient;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

/**
 * 装饰器、工具类是通过继承RepresentationModelAssemblerSupport工具类来实现的
 * 类型参数应该分别传领域类的class和Resource类
 */
public class IngredientResourceAssembler extends RepresentationModelAssemblerSupport<TacoIngredient, IngredientResource> {
  public IngredientResourceAssembler() {
    super(IngredientController.class, IngredientResource.class);
  }

  @Override
  public IngredientResource toModel(TacoIngredient entity) {
    return createModelWithId(entity.getId(), entity);
  }

  @Override
  protected IngredientResource instantiateModel(TacoIngredient entity) {
    return new IngredientResource(entity);
  }
}
