package com.tacos.tacocloud.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

/**
 * 
 * 
 * @author insight 
 * @since 2021/7/17
 */
@Data
@RequiredArgsConstructor
@NoArgsConstructor(force=true)
@Entity
@Table(name = "Ingredient")
public class TacoIngredient {

    @Id
    private final String id;
    private final String name;

    @Enumerated(EnumType.STRING)
    private final Type type;

    public enum Type {
        /**
         * 配料
         */
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }

}