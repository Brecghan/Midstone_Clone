package com.nashss.se.musicplaylistservice.activity;

import com.nashss.se.musicplaylistservice.activity.requests.GetRecipeDetailsRequest;
import com.nashss.se.musicplaylistservice.activity.results.GetRecipeDetailsResult;
import com.nashss.se.musicplaylistservice.converters.ModelConverter;
import com.nashss.se.musicplaylistservice.dynamodb.RecipeDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.Recipe;
import com.nashss.se.musicplaylistservice.models.RecipeModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.List;

/**
 * Implementation of the GetRecipeDetailsActivity for the DigitalPantryService's GetRecipeDetails API.
 * <p>
 * This API allows the customer to get the specific information of a saved recipe.
 */
public class GetRecipeDetailsActivity {
    private final Logger log = LogManager.getLogger();
    private final RecipeDao recipeDao;

    /**
     * Instantiates a new GetRecipeDetailsActivity object.
     *
     * @param recipeDao RecipeDao to access the pantry table.
     */
    @Inject
    public GetRecipeDetailsActivity(RecipeDao recipeDao) {
        this.recipeDao = recipeDao;
    }

    /**
     * This method handles the incoming request by retrieving the Recipe from the database.
     * <p>
     * If the Recipe does not exist, this should throw a RecipeNotFoundException.
     *
     * @param getRecipeDetailsRequest request object containing the recipe Id
     * @return getRecipeDetailsResult result object containing recipe
     * of API defined {@link RecipeModel}s
     */
    public GetRecipeDetailsResult handleRequest(final GetRecipeDetailsRequest getRecipeDetailsRequest) {
        log.info("Received GetRecipeDetailsRequest {}", getRecipeDetailsRequest);

        Recipe recipe = recipeDao.getRecipe(getRecipeDetailsRequest.getRecipeId());
        RecipeModel recipeModel = new ModelConverter().toRecipeModel(recipe);

        return GetRecipeDetailsResult.builder()
                .withRecipe(recipeModel)
                .build();
    }

}
