package com.tacos.tacocloud.restclient.rest;

import com.tacos.tacocloud.domain.Taco;
import com.tacos.tacocloud.domain.TacoIngredient;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.URL;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.client.Traverson;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 使用RestTemplate消费Rest端点
 */
@Slf4j
@Service
public class RestClient {

    private RestTemplate restTemplate;
    private String restUrl;
    private Traverson traverson;

    @Autowired
    public RestClient(RestTemplate restTemplate, RestConfig config, Traverson traverson) {
        this.restTemplate = restTemplate;
        this.restUrl = config.restUrl;
        this.traverson = traverson;
    }

    /*public TacoIngredient getIngredientById(String ingredientId) {
        *//*可以通过HashMap或者UriComponentBuilder的方式传递参数*//*
        *//*Map<String, String> urlVariables = new HashMap<>();
        urlVariables.put("id", ingredientId);
        URI url = UriComponentsBuilder.fromHttpUrl(restUrl + "/ingredients/{id}").build(urlVariables);
        return restTemplate.getForObject(url, TacoIngredient.class);
        return restTemplate.getForObject(
                restUrl + "/ingredients/{id}",
                TacoIngredient.class,
                urlVariables
        );*//*
        return restTemplate.getForObject(
                    restUrl + "/ingredients/{id}",
                    TacoIngredient.class,
                    ingredientId
                );
    }*/

    /*使用getForEntity返回一个包裹领域对象的ResponseEntity对象*/
    public TacoIngredient getIngredientById(String ingredientId) {
        ResponseEntity<TacoIngredient> responseEntity =
                restTemplate.getForEntity(
                restUrl + "/ingredients/{id}",
                    TacoIngredient.class,
                    ingredientId
                );
        log.info("Fetched time: " + responseEntity.getHeaders().getDate());
        return responseEntity.getBody();
    }

    /**
     * 获取全部TacoIngredient
     * @return 配料集合
     */
    public List<TacoIngredient> getIngredients() {
        return restTemplate.exchange(
                restUrl + "/ingredients",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<TacoIngredient>>() {}
        ).getBody();
    }

    public void updateIngredient(TacoIngredient ingredient) {
        restTemplate.put(restUrl + "/ingredients/{id}", ingredient, ingredient.getId());
    }

    public void deleteIngredient(TacoIngredient ingredient) {
        restTemplate.delete(restUrl + "/ingredients/{id}", ingredient.getId());
    }

    //使用postForObject获取post请求之后新创建的资源
    public TacoIngredient createIngredient(TacoIngredient ingredient) {
        return restTemplate.postForObject(restUrl + "/ingredients", ingredient, TacoIngredient.class);
    }

    //使用postForLocation获取post请求之后新创建资源的位置
    public URI createIngredientForLocation(TacoIngredient ingredient) {
        return restTemplate.postForLocation(restUrl + "/ingredients", ingredient);
    }

    //使用postForEntity获取post请求之后返回的payload
    public TacoIngredient createIngredientForEntity(TacoIngredient ingredient) {
        ResponseEntity<TacoIngredient> responseEntity = restTemplate.postForEntity(
                restUrl + "/ingredients",
                ingredient,
                TacoIngredient.class
        );

        log.info("New response created at " + responseEntity.getHeaders().getLocation());
        return responseEntity.getBody();
    }

    public Collection<TacoIngredient> getIngredientsByTraverson() {
        ParameterizedTypeReference<CollectionModel<TacoIngredient>> ingredientType
                = new ParameterizedTypeReference<CollectionModel<TacoIngredient>>() {
        };
        return traverson.follow("ingredients")
                .toObject(ingredientType)
                .getContent();
    }

    public List<Taco> getRecentTacosByTraverson() {
        ParameterizedTypeReference<List<Taco>> tacoType
                = new ParameterizedTypeReference<List<Taco>>() {
        };
        return traverson.follow("tacos", "recents")
                .toObject(tacoType);
    }


}
