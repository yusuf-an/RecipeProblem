package com.backend.recipe;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RecipeInitializer {

	@Autowired
	DBPopulator dbPopulator;

	@PostConstruct
	void initialize() {
		dbPopulator.populateDb();
	}

}
