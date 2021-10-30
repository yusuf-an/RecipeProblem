package com.backend.recipe.exceptions;

public class RecipeNotFoundException extends RuntimeException{

	public RecipeNotFoundException(String string) {
		super(string);
	}

}
