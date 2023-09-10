package com.nashss.se.musicplaylistservice.activity;

import com.nashss.se.musicplaylistservice.activity.requests.GetRecipesRequest;
import com.nashss.se.musicplaylistservice.activity.results.GetRecipesResult;
import com.nashss.se.musicplaylistservice.converters.ModelConverter;
import com.nashss.se.musicplaylistservice.dynamodb.RecipeDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.Recipe;
import com.nashss.se.musicplaylistservice.models.RecipeModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import javax.inject.Inject;

/**
 * Implementation of the GetRecipesActivity for the DigitalPantryService's GetRecipes API.
 * <p>
 * This API allows the customer to see a list of recipes.
 */
public class GetRecipesActivity {
    private final Logger log = LogManager.getLogger();
    private final RecipeDao recipeDao;

    /**
     * Instantiates a new GetRecipesActivity object.
     *
     * @param recipeDao RecipeDao to access the recipe table.
     */
    @Inject
    public GetRecipesActivity(RecipeDao recipeDao) {
        this.recipeDao = recipeDao;
    }

    /**
     * This method handles the incoming request by retrieving List of recipes from the database.
     * <p>
     * If there is a recipe region provided, then the return list will only be recipes from that region.
     * If recipe region is null, then all recipes will be returned
     *
     * @param getRecipesRequest request object possibly containing a recipe region
     * @return getRecipesResult result object containing the pantries that were created by that User ID
     */
    public GetRecipesResult handleRequest(final GetRecipesRequest getRecipesRequest) {
        log.info("Received GetRecipesRequest {}", getRecipesRequest);

        List<Recipe> results = recipeDao.getRecipeList(getRecipesRequest.getRecipeRegion());
        List<RecipeModel> recipeModels = new ModelConverter().toRecipeModelList(results);

        return GetRecipesResult.builder()
                .withRecipes(recipeModels)
                .build();
    }
}
