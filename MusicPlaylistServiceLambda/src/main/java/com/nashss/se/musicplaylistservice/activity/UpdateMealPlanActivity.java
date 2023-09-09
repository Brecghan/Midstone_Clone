package com.nashss.se.musicplaylistservice.activity;

import com.nashss.se.musicplaylistservice.activity.requests.UpdateMealPlanRequest;
import com.nashss.se.musicplaylistservice.activity.results.UpdateMealPlanResult;
import com.nashss.se.musicplaylistservice.converters.ModelConverter;
import com.nashss.se.musicplaylistservice.dynamodb.MealPlanDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.MealPlan;
import com.nashss.se.musicplaylistservice.metrics.MetricsPublisher;
import com.nashss.se.musicplaylistservice.models.PantryModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

/**
 * Implementation of the UpdateMealPlanActivity for the MusicPlaylistService's UpdatePlaylist API.
 *
 * This API allows the customer to update their saved playlist's information.
 */
public class UpdateMealPlanActivity {
    private final Logger log = LogManager.getLogger();
    private final MealPlanDao mealPlanDao;
    private final MetricsPublisher metricsPublisher;

    /**
     * Instantiates a new UpdateMealPlanActivity object.
     *
     * @param mealPlanDao MealPlanDao to access the meal plan table.
     * @param metricsPublisher MetricsPublisher to publish metrics.
     */
    @Inject
    public UpdateMealPlanActivity(MealPlanDao mealPlanDao, MetricsPublisher metricsPublisher) {
        this.mealPlanDao = mealPlanDao;
        this.metricsPublisher = metricsPublisher;
    }

    /**
     * This method handles the incoming request by retrieving the meal plan, updating it,
     * and persisting the meal plan.
     * <p>
     * It then returns the updated meal plan.
     * <p>
     * If the meal plan does not exist, this should throw a PantryNotFoundException.
     * <p>
     *
     * @param updatePantryRequest request object containing the meal plan ID, meal plan name, and user ID
     *                              associated with it
     * @return updatePantryResult result object containing the API defined {@link PantryModel}
     */
    public UpdateMealPlanResult handleRequest(final UpdateMealPlanRequest updatePantryRequest) {
        log.info("Received UpdateMealPlanRequest {}", updatePantryRequest);


        MealPlan mealPlan = mealPlanDao.getMealPlan(updatePantryRequest.getUserId(), updatePantryRequest.getMealPlanId());

        mealPlan.setMealPlanName(updatePantryRequest.getMealPlanName());
        mealPlan = mealPlanDao.saveMealPlan(mealPlan);

        return UpdateMealPlanResult.builder()
                .withMealPlan(new ModelConverter().toMealPlanModel(mealPlan))
                .build();
    }
}
