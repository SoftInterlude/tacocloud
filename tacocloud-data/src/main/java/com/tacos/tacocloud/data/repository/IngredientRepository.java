package com.tacos.tacocloud.data.repository;

import com.tacos.tacocloud.domain.TacoIngredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "ingredients", itemResourceRel = "ingredient",
        collectionResourceRel = "ingredients")
public interface IngredientRepository 
         extends CrudRepository<TacoIngredient, String> {

}
