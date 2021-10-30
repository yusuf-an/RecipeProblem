package com.backend.recipe.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.backend.recipe.DBPopulator;
import com.backend.recipe.exceptions.RecipeNotFoundException;
import com.backend.recipe.pojo.RecipePojo;
import com.backend.recipe.repository.RecipeRepository;

@RestController
public class RecipeController {

	@Autowired
	DBPopulator dbPopulator;

	@Autowired
	RecipeRepository recipeRepository;

	@GetMapping("/")
	ResponseEntity<List<RecipePojo>> getAllRecipe() {
		return ResponseEntity.ok(recipeRepository.findAll());
	}

	@GetMapping("/{id}")
	ResponseEntity<RecipePojo> getRecipeById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(
				recipeRepository.findById(id).orElseThrow(() -> new RecipeNotFoundException("Recipe id not found")));
	}

	@GetMapping("/{id}/show")
	ResponseEntity<String> getImage(@PathVariable("id") Long id) {
		RecipePojo recipe = recipeRepository.findById(id)
				.orElseThrow(() -> new RecipeNotFoundException("Recipe id not found"));
		return ResponseEntity.ok(recipe.getImage());

	}

	@PostMapping("/")
	ResponseEntity<RecipePojo> addRecipe(@Valid @RequestBody RecipePojo recipePojo) {
		return ResponseEntity.ok(recipeRepository.save(recipePojo));
	}

	@PostMapping("/reset")
	ResponseEntity<String> resetAll() {
		recipeRepository.deleteAll();
		dbPopulator.populateDb();
		return ResponseEntity.ok("deleted");

	}

}
