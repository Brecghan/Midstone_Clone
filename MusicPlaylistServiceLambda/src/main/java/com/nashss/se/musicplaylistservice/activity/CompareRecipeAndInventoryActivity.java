package com.nashss.se.musicplaylistservice.activity;

import com.nashss.se.musicplaylistservice.activity.requests.CompareRecipeAndInventoryRequest;
import com.nashss.se.musicplaylistservice.activity.results.CompareRecipeAndInventoryResult;
import com.nashss.se.musicplaylistservice.converters.ModelConverter;
import com.nashss.se.musicplaylistservice.dynamodb.PantryDao;
import com.nashss.se.musicplaylistservice.dynamodb.RecipeDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.Ingredient;
import com.nashss.se.musicplaylistservice.dynamodb.models.Pantry;
import com.nashss.se.musicplaylistservice.dynamodb.models.Recipe;
import com.nashss.se.musicplaylistservice.models.IngredientModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;

/**
 * Implementation of the CompareRecipeAndInventoryActivity for the DigitalPantryService's CompareRecipeAndInventory API.
 * <p>
 * This API allows the customer to get the list of missing ingredients when comparing
 * a recipe's list of ingredients with the inventory of a saved pantry.
 */
public class CompareRecipeAndInventoryActivity {
    private final Logger log = LogManager.getLogger();
    private final PantryDao pantryDao;
    private final RecipeDao recipeDao;

    /**
     * Instantiates a new GetMealPlanRecipeSetActivity object.
     *
     * @param pantryDao PantryDao to access the Pantries table.
     * @param recipeDao RecipeDao to access the Recipe table.
     */
    @Inject
    public CompareRecipeAndInventoryActivity(PantryDao pantryDao, RecipeDao recipeDao) {
        this.pantryDao = pantryDao;
        this.recipeDao = recipeDao;
    }

    /**
     * This method handles the incoming request by retrieving the pantry from the database.
     * <p>
     * It then takes the recipe IDs and retrieves the recipe from the database.
     * <p>
     * If the pantry does not exist, this should throw a PantryNotFoundException.
     * If the recipe does not exist, this should throw a RecipeNotFoundException.
     * <p>
     * It then takes the Ingredients List from each and compares them,
     * returning a new List with what is needed to make the recipe
     * <p>
     * @param compareRecipeAndInventoryRequest request object containing the
     * Pantry ID, the Recipe ID & user ID
     * @return compareRecipeAndInventoryResult result object containing the List of Ingredients
     * of API defined {@link IngredientModel}s
     */
    public CompareRecipeAndInventoryResult handleRequest(
            final CompareRecipeAndInventoryRequest compareRecipeAndInventoryRequest) {
        log.info("Received CompareRecipeAndInventoryRequest {}", compareRecipeAndInventoryRequest);

        Pantry pantry = pantryDao.getPantry(compareRecipeAndInventoryRequest.getUserId(),
                compareRecipeAndInventoryRequest.getPantryId());
        Recipe recipe = recipeDao.getRecipe(compareRecipeAndInventoryRequest.getRecipeId());
        Set<Ingredient> recipeToMake = new HashSet<>(recipe.getNeededIngredients());
        Set<Ingredient> inventoryToCheck = new HashSet<>(pantry.getInventory());
        Map<Ingredient, Double> inventoryMap = new HashMap<>();
        for (Ingredient ingredient : inventoryToCheck) {
            inventoryMap.put(ingredient, ingredient.getQuantity());
        }
        Set<Ingredient> missingIngredients = new HashSet<>();
        for (Ingredient neededIngredient : recipeToMake) {
            if (!inventoryToCheck.contains(neededIngredient)) {
                missingIngredients.add(neededIngredient);
            } else {
                Double qtyOnHand = inventoryMap.get(neededIngredient);
                if (qtyOnHand < neededIngredient.getQuantity()) {
                    Ingredient newIngredient = new Ingredient();
                    newIngredient.setIngredientName(neededIngredient.getIngredientName());
                    newIngredient.setQuantity(neededIngredient.getQuantity() - qtyOnHand);
                    newIngredient.setUnitOfMeasure(neededIngredient.getUnitOfMeasure());
                    missingIngredients.add(newIngredient);
                }
            }

        }
        List<IngredientModel> ingredientModels =
                new ModelConverter().toIngredientModelList(missingIngredients);

        return CompareRecipeAndInventoryResult.builder()
                .withMissingIngredients(ingredientModels)
                .build();
    }
}
