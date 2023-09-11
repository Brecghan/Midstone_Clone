package com.nashss.se.musicplaylistservice.activity;

import com.nashss.se.musicplaylistservice.activity.requests.GetPantryRequest;
import com.nashss.se.musicplaylistservice.activity.results.GetPantryResult;
import com.nashss.se.musicplaylistservice.converters.ModelConverter;
import com.nashss.se.musicplaylistservice.dynamodb.PantryDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.Pantry;
import com.nashss.se.musicplaylistservice.models.PantryModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.List;

/**
 * Implementation of the GetPantryActivity for the DigitalPantryService's GetPantry API.
 * <p>
 * This API allows the customer to see the details of a specfic pantry.
 */
public class GetPantryActivity {
    private final Logger log = LogManager.getLogger();
    private final PantryDao pantryDao;

    /**
     * Instantiates a new GetPantryActivity object.
     *
     * @param pantryDao PantryDao to access the pantry table.
     */
    @Inject
    public GetPantryActivity(PantryDao pantryDao) {
        this.pantryDao = pantryDao;
    }

    /**
     * This method handles the incoming request by retrieving a specified user's Pantry from the database.
     * <p>
     * It then returns the matching pantry.
     *
     * @param getPantryRequest request object containing the User ID & the Pantry Id
     * @return getPantryResult result object containing the pantry requested that were created by that User ID
     */
    public GetPantryResult handleRequest(final GetPantryRequest getPantryRequest) {
        log.info("Received GetPantryRequest {}", getPantryRequest);

        Pantry result = pantryDao.getPantry(getPantryRequest.getUserId(), getPantryRequest.getPantryId());
        PantryModel pantryModel = new ModelConverter().toPantryModel(result);

        return GetPantryResult.builder()
                .withPantry(pantryModel)
                .build();
    }
}
