package com.tacos.tacocloud.api.controller;

import com.tacos.tacocloud.data.repository.IngredientRepository;
import com.tacos.tacocloud.domain.TacoIngredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

/**
 * 
 * 
 * @author insight 
 * @since 2021/7/17
 */
@RestController
@RequestMapping(path="/ingredients", produces="application/json")
@CrossOrigin
public class IngredientController {

    private IngredientRepository repo;

    @Autowired
    public IngredientController(IngredientRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public Iterable<TacoIngredient> allIngredients() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<TacoIngredient> byId(@PathVariable String id) {
        return repo.findById(id);
    }

    @PutMapping("/{id}")
    public void updateIngredient(@PathVariable String id, @RequestBody TacoIngredient ingredient) {
        if (!ingredient.getId().equals(id)) {
            throw new IllegalStateException("Given ingredient's ID doesn't match the ID in the path.");
        }
        repo.save(ingredient);
    }

    @PostMapping
    public ResponseEntity<TacoIngredient> postIngredient(@RequestBody TacoIngredient ingredient) {
        TacoIngredient saved = repo.save(ingredient);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("http://localhost:8080/ingredients/" + ingredient.getId()));
        return new ResponseEntity<>(saved, headers, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteIngredient(@PathVariable String id) {
        repo.deleteById(id);
    }
}
