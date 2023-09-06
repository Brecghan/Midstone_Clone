package com.nashss.se.musicplaylistservice.activity;

import com.nashss.se.musicplaylistservice.activity.requests.CreatePantryRequest;
import com.nashss.se.musicplaylistservice.activity.results.CreatePantryResult;
import com.nashss.se.musicplaylistservice.converters.ModelConverter;
import com.nashss.se.musicplaylistservice.dynamodb.PantryDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.Pantry;
import com.nashss.se.musicplaylistservice.models.PantryModel;
import com.nashss.se.musicplaylistservice.utils.DigitalPantryServiceUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import javax.inject.Inject;

/**
 * Implementation of the CreatePantryActivity for the DigitalPantryService's CreatePantry API.
 * <p>
 * This API allows the customer to create a new pantry with no data.
 */
public class CreatePantryActivity {
    private final Logger log = LogManager.getLogger();
    private final PantryDao pantryDao;

    /**
     * Instantiates a new CreatePantryActivity object.
     *
     * @param pantryDao PantryDao to access the pantries table.
     */
    @Inject
    public CreatePantryActivity(PantryDao pantryDao) {
        this.pantryDao = pantryDao;
    }

    /**
     * This method handles the incoming request by persisting a new pantry
     * with the provided pantry name and customer ID from the request.
     * <p>
     * It then returns the newly created pantry.
     * <p>
     * If the provided pantry name or customer ID has invalid characters, throws an
     * InvalidAttributeValueException
     *
     * @param createPantryRequest request object containing the pantry name and customer ID
     *                              associated with it
     * @return createPantryResult result object containing the API defined {@link PantryModel}
     */
    public CreatePantryResult handleRequest(final CreatePantryRequest createPantryRequest) {
        log.info("Received CreatePantryRequest {}", createPantryRequest);

        Pantry newPantry = new Pantry();
        newPantry.setPantryId(DigitalPantryServiceUtils.generatePantryId());
        newPantry.setPantryName(createPantryRequest.getPantryName());
        newPantry.setUserId(createPantryRequest.getUserId());
        newPantry.setInventory(new HashSet<>());

        pantryDao.savePantry(newPantry);

        PantryModel pantryModel = new ModelConverter().toPantryModel(newPantry);
        return CreatePantryResult.builder()
                .withPantry(pantryModel)
                .build();
    }
}
