package com.backend.recipe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.recipe.pojo.RecipePojo;

public interface RecipeRepository extends JpaRepository<RecipePojo, Long>{

}
