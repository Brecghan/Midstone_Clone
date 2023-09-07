package com.nashss.se.musicplaylistservice.activity;

import com.nashss.se.musicplaylistservice.activity.requests.UpdatePantryIngredientRequest;
import com.nashss.se.musicplaylistservice.activity.results.UpdatePantryIngredientResult;
import com.nashss.se.musicplaylistservice.converters.ModelConverter;
import com.nashss.se.musicplaylistservice.dynamodb.PantryDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.Ingredient;
import com.nashss.se.musicplaylistservice.dynamodb.models.Pantry;
import com.nashss.se.musicplaylistservice.metrics.MetricsPublisher;
import com.nashss.se.musicplaylistservice.models.PantryModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;
import javax.inject.Inject;

/**
 * Implementation of the UpdatePantryActivity for the DigitalPantryService's UpdatePantry API.
 *
 * This API allows the customer to update their saved pantry's information.
 */
public class UpdatePantryIngredientActivity {
    private final Logger log = LogManager.getLogger();
    private final PantryDao pantryDao;
    private final MetricsPublisher metricsPublisher;

    /**
     * Instantiates a new UpdatePantryActivity object.
     *
     * @param pantryDao PantryDao to access the pantry table.
     * @param metricsPublisher MetricsPublisher to publish metrics.
     */
    @Inject
    public UpdatePantryIngredientActivity(PantryDao pantryDao, MetricsPublisher metricsPublisher) {
        //super(UpdatePantryIngredientRequest.class);
        this.pantryDao = pantryDao;
        this.metricsPublisher = metricsPublisher;
    }

    /**
     * This method handles the incoming request by retrieving the pantry, updating it,
     * and persisting the pantry.
     * <p>
     * It then returns the updated pantry.
     * <p>
     * If the pantry does not exist, this should throw a PantryNotFoundException.
     * <p>
     *
     * @param updatePantryIngredientRequest request object containing the pantry ID,
     * userId, ingredient name, ingredient quantity, and unit of measure associated with it
     * @return updatePantryResult result object containing the API defined {@link PantryModel}
     */
    public UpdatePantryIngredientResult handleRequest(final
                                                      UpdatePantryIngredientRequest updatePantryIngredientRequest) {
        log.info("Received UpdatePantryIngredientRequest {}", updatePantryIngredientRequest);

        Pantry pantry = pantryDao.getPantry(updatePantryIngredientRequest.getUserId(),
                updatePantryIngredientRequest.getPantryId());

        Ingredient ingredient = new Ingredient();
        ingredient.setIngredientName(updatePantryIngredientRequest.getIngredientName());
        ingredient.setQuantity(updatePantryIngredientRequest.getIngredientQuantity());
        ingredient.setUnitOfMeasure(Ingredient.UnitOfMeasure.valueOf(
                updatePantryIngredientRequest.getUnitOfMeasure()));
        Set<Ingredient> ingredientSet = pantry.getInventory();
        ingredientSet.remove(ingredient);
        ingredientSet.add(ingredient);
        pantry.setInventory(ingredientSet);

        pantry = pantryDao.savePantry(pantry);

        return UpdatePantryIngredientResult.builder()
                .withPantry(new ModelConverter().toPantryModel(pantry))
                .build();
    }
}
