package com.nashss.se.musicplaylistservice.activity;

import com.nashss.se.musicplaylistservice.activity.requests.GetPantryListRequest;
import com.nashss.se.musicplaylistservice.activity.results.GetPantryListResult;
import com.nashss.se.musicplaylistservice.converters.ModelConverter;
import com.nashss.se.musicplaylistservice.dynamodb.PantryDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.Pantry;
import com.nashss.se.musicplaylistservice.models.PantryModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.List;

/**
 * Implementation of the GetPantryListActivity for the DigitalPantryService's GetPantry API.
 * <p>
 * This API allows the customer to see all of their pantries.
 */
public class GetPantryListActivity {
    private final Logger log = LogManager.getLogger();
    private final PantryDao pantryDao;

    /**
     * Instantiates a new SearchPlaylistsActivity object.
     *
     * @param pantryDao PantryDao to access the playlist table.
     */
    @Inject
    public GetPantryListActivity(PantryDao pantryDao) {
        this.pantryDao = pantryDao;
    }

    /**
     * This method handles the incoming request by retrieving List of a specified user's Pantries from the database.
     * <p>
     * It then returns the matching pantries, or an empty result list if none are found.
     *
     * @param getPantryListRequest request object containing the User ID
     * @return getPantryListResult result object containing the pantries that were created by that User ID
     */
    public GetPantryListResult handleRequest(final GetPantryListRequest getPantryListRequest) {
        log.info("Received GetPantryListRequest {}", getPantryListRequest);

        List<Pantry> results = pantryDao.getUserPantries(getPantryListRequest.getUserId());
        List<PantryModel> pantryModels = new ModelConverter().toPantryModelList(results);

        return GetPantryListResult.builder()
                .withPantries(pantryModels)
                .build();
    }
}
