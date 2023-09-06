package com.nashss.se.musicplaylistservice.activity;

import com.nashss.se.musicplaylistservice.activity.requests.GetPantryInventoryRequest;
import com.nashss.se.musicplaylistservice.activity.results.GetPantryInventoryResult;
import com.nashss.se.musicplaylistservice.converters.ModelConverter;
import com.nashss.se.musicplaylistservice.dynamodb.PantryDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.Pantry;
import com.nashss.se.musicplaylistservice.models.IngredientModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Implementation of the GetPantryInventoryActivity for the DigitalPantryService's GetPantryInventory API.
 * <p>
 * This API allows the customer to get the list of pantry ingredient of a saved pantry.
 */
public class GetPantryInventoryActivity {
    private final Logger log = LogManager.getLogger();
    private final PantryDao pantryDao;

    /**
     * Instantiates a new GetPantryInventoryActivity object.
     *
     * @param pantryDao PantryDao to access the pantry table.
     */
    @Inject
    public GetPantryInventoryActivity(PantryDao pantryDao) {
        this.pantryDao = pantryDao;
    }

    /**
     * This method handles the incoming request by retrieving the pantry from the database.
     * <p>
     * It then returns the pantry's inventory list.
     * <p>
     * If the pantry does not exist, this should throw a PantryNotFoundException.
     *
     * @param getPantryInventoryRequest request object containing the
     * pantry ID & user ID
     * @return getPantryInventoryResult result object containing the pantry's list
     * of API defined {@link IngredientModel}s
     */
    public GetPantryInventoryResult handleRequest(final GetPantryInventoryRequest getPantryInventoryRequest) {
        log.info("Received GetPantryInventoryRequest {}", getPantryInventoryRequest);

        Pantry pantry = pantryDao.getPantry(getPantryInventoryRequest.getUserId(),
                getPantryInventoryRequest.getPantryId());
        List<IngredientModel> ingredientModels =
                new ModelConverter().toIngredientModelList(new ArrayList<>(pantry.getInventory()));

        return GetPantryInventoryResult.builder()
                .withInventory(ingredientModels)
                .build();
    }

}
