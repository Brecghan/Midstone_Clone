package com.nashss.se.musicplaylistservice.activity;

import com.nashss.se.musicplaylistservice.activity.requests.UpdatePantryRequest;
import com.nashss.se.musicplaylistservice.activity.results.UpdatePantryResult;
import com.nashss.se.musicplaylistservice.converters.ModelConverter;
import com.nashss.se.musicplaylistservice.dynamodb.PantryDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.Pantry;
import com.nashss.se.musicplaylistservice.metrics.MetricsPublisher;
import com.nashss.se.musicplaylistservice.models.PantryModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

/**
 * Implementation of the UpdatePantryActivity for the MusicPlaylistService's UpdatePlaylist API.
 *
 * This API allows the customer to update their saved playlist's information.
 */
public class UpdateMealPlanActivity {
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
    public UpdateMealPlanActivity(PantryDao pantryDao, MetricsPublisher metricsPublisher) {
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
     * @param updatePantryRequest request object containing the pantry ID, pantry name, and user ID
     *                              associated with it
     * @return updatePantryResult result object containing the API defined {@link PantryModel}
     */
    public UpdatePantryResult handleRequest(final UpdatePantryRequest updatePantryRequest) {
        log.info("Received UpdatePantryRequest {}", updatePantryRequest);


        Pantry pantry = pantryDao.getPantry(updatePantryRequest.getUserId(), updatePantryRequest.getPantryId());

        pantry.setPantryName(updatePantryRequest.getPantryName());
        pantry = pantryDao.savePantry(pantry);

        return UpdatePantryResult.builder()
                .withPantry(new ModelConverter().toPantryModel(pantry))
                .build();
    }
}
