package com.nashss.se.musicplaylistservice.activity;

import com.nashss.se.musicplaylistservice.activity.requests.GetMealPlanRecipeSetRequest;
import com.nashss.se.musicplaylistservice.activity.results.GetMealPlanRecipeSetResult;
import com.nashss.se.musicplaylistservice.converters.ModelConverter;
import com.nashss.se.musicplaylistservice.dynamodb.MealPlanDao;
import com.nashss.se.musicplaylistservice.dynamodb.RecipeDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.MealPlan;
import com.nashss.se.musicplaylistservice.dynamodb.models.Recipe;
import com.nashss.se.musicplaylistservice.models.RecipeModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;

/**
 * Implementation of the GetPantryInventoryActivity for the DigitalPantryService's GetPantryInventory API.
 * <p>
 * This API allows the customer to get the list of pantry ingredient of a saved pantry.
 */
public class GetMealPlanRecipeSetActivity {
    private final Logger log = LogManager.getLogger();
    private final MealPlanDao mealPlanDao;
    private final RecipeDao recipeDao;

    /**
     * Instantiates a new GetMealPlanRecipeSetActivity object.
     *
     * @param mealPlanDao MealPlanDao to access the Meal Plan table.
     * @param recipeDao RecipeDao to access the Recipe table.
     */
    @Inject
    public GetMealPlanRecipeSetActivity(MealPlanDao mealPlanDao, RecipeDao recipeDao) {
        this.mealPlanDao = mealPlanDao;
        this.recipeDao = recipeDao;
    }

    /**
     * This method handles the incoming request by retrieving the meal plan from the database.
     * <p>
     * It then returns the meal plan's recipe ID list.
     * <p>
     * If the meal Plan does not exist, this should throw a MealPlanNotFoundException.
     * <p>
     * It then takes the recipe IDs and retrieves the recipe from the database.
     * <p>
     * @param getMealPlanRecipeSetRequest request object containing the
     * Meal plan ID & user ID
     * @return getMealPlanRecipeSetResult result object containing the List of Recipes
     * of API defined {@link RecipeModel}s
     */
    public GetMealPlanRecipeSetResult handleRequest(final GetMealPlanRecipeSetRequest getMealPlanRecipeSetRequest) {
        log.info("Received GetMealPlanRecipeSetRequest {}", getMealPlanRecipeSetRequest);

        MealPlan mealPlan = mealPlanDao.getMealPlan(getMealPlanRecipeSetRequest.getUserId(),
                getMealPlanRecipeSetRequest.getMealPlanId());
        List<Recipe> recipeList = new ArrayList<>();
        Set<String> recipeIdList = mealPlan.getRecipeSet();

        for (String recipeId : recipeIdList) {
            Recipe recipeToAdd = recipeDao.getRecipe(recipeId);
            if (recipeToAdd != null) {
                recipeList.add(recipeToAdd);
            }
        }
        List<RecipeModel> recipeModels =
                new ModelConverter().toRecipeModelList(new ArrayList<>(recipeList));

        return GetMealPlanRecipeSetResult.builder()
                .withRecipeSet(recipeModels)
                .build();
    }
}
