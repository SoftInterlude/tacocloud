package com.tacos.tacocloud.api.hateoas;

import com.tacos.tacocloud.domain.TacoIngredient;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@Getter
public class IngredientResource extends RepresentationModel<IngredientResource> {

  private String name;

  private TacoIngredient.Type type;

  public IngredientResource(TacoIngredient ingredient) {
    this.name = ingredient.getName();
    this.type = ingredient.getType();
  }
}
