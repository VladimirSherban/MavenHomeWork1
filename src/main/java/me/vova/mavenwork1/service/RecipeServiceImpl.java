package me.vova.mavenwork1.service;

import me.vova.mavenwork1.exception.ValidationException;
import me.vova.mavenwork1.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements RecipeService {

    private static long idCounter = 1;
    private final Map<Long, Recipe> recipes = new HashMap<>();
    private final ValidationService validationService;


    public RecipeServiceImpl(ValidationService validationService) {
        this.validationService = validationService;
    }

    @Override
    public Recipe save(Recipe recipe) {
        if (!validationService.validate(recipe)) {
            throw new ValidationException(recipe.toString());
        }
        return recipes.put(idCounter++, recipe);
    }

    @Override
    public Optional<Recipe> getById(Long id) {
        return Optional.ofNullable(recipes.get(id));
    }
}
