package com.backend.recipe;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.backend.recipe.pojo.RecipePojo;
import com.backend.recipe.repository.RecipeRepository;

@Component
public class DBPopulator {
	@Value("${api.host.baseurl}")
	private String apiHost;
	Logger logger = LoggerFactory.getLogger(DBPopulator.class);

	@Autowired
	RecipeRepository recipeRepo;

	public void populateDb() {

		logger.info("Start fetching data from " + apiHost);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<RecipePojo[]> responseEntity = restTemplate.getForEntity(apiHost, RecipePojo[].class);
		RecipePojo[] recipes = responseEntity.getBody();
		recipeRepo.saveAll(Arrays.asList(recipes));
		logger.info("Saved objects: " + recipes.length);
	}
}
