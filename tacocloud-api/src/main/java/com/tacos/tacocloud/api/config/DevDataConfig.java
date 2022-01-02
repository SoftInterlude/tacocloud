package com.tacos.tacocloud.api.config;

import com.tacos.tacocloud.data.repository.IngredientRepository;
import com.tacos.tacocloud.data.repository.TacoRepository;
import com.tacos.tacocloud.data.repository.UserRepository;
import com.tacos.tacocloud.domain.Taco;
import com.tacos.tacocloud.domain.TacoIngredient;
import com.tacos.tacocloud.domain.TacoIngredient.Type;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

/**
 * 开发环境下自定义初始数据
 *
 * @author insight
 * @since 2021/7/17
 */
@Profile("!prod")
@Configuration
public class DevDataConfig {
    @Bean
    public CommandLineRunner dataLoader(IngredientRepository repo,
                                        UserRepository userRepo,
                                        TacoRepository tacoRepo) {
        return args -> {
            TacoIngredient flourTortilla = new TacoIngredient("FLTO", "Flour Tortilla", Type.WRAP);
            TacoIngredient cornTortilla = new TacoIngredient("COTO", "Corn Tortilla", Type.WRAP);
            TacoIngredient groundBeef = new TacoIngredient("GRBF", "Ground Beef", Type.PROTEIN);
            TacoIngredient carnitas = new TacoIngredient("CARN", "Carnitas", Type.PROTEIN);
            TacoIngredient tomatoes = new TacoIngredient("TMTO", "Diced Tomatoes", Type.VEGGIES);
            TacoIngredient lettuce = new TacoIngredient("LETC", "Lettuce", Type.VEGGIES);
            TacoIngredient cheddar = new TacoIngredient("CHED", "Cheddar", Type.CHEESE);
            TacoIngredient jack = new TacoIngredient("JACK", "Monterrey Jack", Type.CHEESE);
            TacoIngredient salsa = new TacoIngredient("SLSA", "Salsa", Type.SAUCE);
            TacoIngredient sourCream = new TacoIngredient("SRCR", "Sour Cream", Type.SAUCE);
            repo.save(flourTortilla);
            repo.save(cornTortilla);
            repo.save(groundBeef);
            repo.save(carnitas);
            repo.save(tomatoes);
            repo.save(lettuce);
            repo.save(cheddar);
            repo.save(jack);
            repo.save(salsa);
            repo.save(sourCream);

            Taco taco1 = new Taco();
            taco1.setName("Carnivore");
            taco1.setIngredients(Arrays.asList(flourTortilla, groundBeef, carnitas, sourCream, salsa, cheddar));
            tacoRepo.save(taco1);

            Taco taco2 = new Taco();
            taco2.setName("Bovine Bounty");
            taco2.setIngredients(Arrays.asList(cornTortilla, groundBeef, cheddar, jack, sourCream));
            tacoRepo.save(taco2);

            Taco taco3 = new Taco();
            taco3.setName("Veg-Out");
            taco3.setIngredients(Arrays.asList(flourTortilla, cornTortilla, tomatoes, lettuce, salsa));
            tacoRepo.save(taco3);
        };
    }
}