package com.tacos.tacocloud.restclient.rest;

import com.tacos.tacocloud.domain.Taco;
import com.tacos.tacocloud.domain.TacoIngredient;
import com.tacos.tacocloud.domain.TacoIngredient.Type;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.client.Traverson;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class ConsumeRest{

    private RestClient restClient;
    private Traverson traverson;

    @Autowired
    public ConsumeRest(RestClient restClient, Traverson traverson) {
        this.restClient = restClient;
        this.traverson = traverson;
    }

    @Bean
    public CommandLineRunner fetchIngredients() {
        return args -> {
            log.info("----------------------- GET -------------------------");
            log.info("GETTING INGREDIENT BY ID");
            log.info("TacoIngredient:  " + restClient.getIngredientById("CHED"));
            log.info("GETTING ALL INGREDIENTS");
            List<TacoIngredient> tacoIngredients = restClient.getIngredients();
            for (TacoIngredient ingredient:
                 tacoIngredients) {
                log.info(" -" + ingredient);
            }

        };
    }

    @Bean
    public CommandLineRunner updateIngredient() {
        return args -> {
            log.info("----------------------- PUT -------------------------");
            TacoIngredient before = restClient.getIngredientById("LETC");
            log.info("Before: " + before);
            restClient.updateIngredient(
                    new TacoIngredient(
                            "LETC",
                            "Shredded Lettuce",
                            TacoIngredient.Type.VEGGIES)
            );
            TacoIngredient after = restClient.getIngredientById("LETC");
            log.info("After: " + after);
        };
    }


    @Bean
    public CommandLineRunner createIngredient() {
        return args -> {
            log.info("----------------------- CREATE -------------------------");
            TacoIngredient ingredient = new TacoIngredient("LAIZ", "La Bonita", Type.PROTEIN);
            TacoIngredient createdIngredient = restClient.createIngredient(ingredient);
            log.info("Created Ingredient: " + createdIngredient);
        };
    }

    @Bean
    public CommandLineRunner deleteIngredient() {
        return args -> {
            log.info("----------------------- DELETE -------------------------");
            TacoIngredient before = restClient.getIngredientById("LAIZ");
            log.info("Before: " + before);
            restClient.deleteIngredient(
                    before
            );
            log.info("GETTING ALL INGREDIENTS");
            List<TacoIngredient> tacoIngredients = restClient.getIngredients();
            for (TacoIngredient ingredient:
                    tacoIngredients) {
                log.info(" -" + ingredient);
            }
        };
    }

    @Bean
    public CommandLineRunner traversonIngredients() {
        return args -> {
            log.info("----------------------- TRAVERSON INGREDIENTS -------------------------");
            Iterable<TacoIngredient> ingredients = restClient.getIngredientsByTraverson();
            for (TacoIngredient ingredient:
                    ingredients) {
                log.info(" -" + ingredient);
            }
        };
    }

    @Bean
    public CommandLineRunner traversonRecentTacos() {
        return args -> {
            log.info("----------------------- TRAVERSON TACOS -------------------------");
            Iterable<Taco> tacos = restClient.getRecentTacosByTraverson();
            for (Taco taco:
                    tacos) {
                log.info(" -" + taco);
            }
        };
    }

}
