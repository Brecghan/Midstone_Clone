package com.nashss.se.musicplaylistservice.activity;

import com.nashss.se.musicplaylistservice.activity.requests.GetMealPlanListRequest;
import com.nashss.se.musicplaylistservice.activity.results.GetMealPlanListResult;
import com.nashss.se.musicplaylistservice.converters.ModelConverter;
import com.nashss.se.musicplaylistservice.dynamodb.MealPlanDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.MealPlan;
import com.nashss.se.musicplaylistservice.models.MealPlanModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.List;

/**
 * Implementation of the GetMealPlanListActivity for the DigitalPantryService's GetMealPlan API.
 * <p>
 * This API allows the customer to see all of their pantries.
 */
public class GetMealPlanListActivity {
    private final Logger log = LogManager.getLogger();
    private final MealPlanDao mealPlanDao;

    /**
     * Instantiates a new SearchPlaylistsActivity object.
     *
     * @param mealPlanDao MealPlanDao to access the playlist table.
     */
    @Inject
    public GetMealPlanListActivity(MealPlanDao mealPlanDao) {
        this.mealPlanDao = mealPlanDao;
    }

    /**
     * This method handles the incoming request by retrieving List of a specified user's MealPlans from the database.
     * <p>
     * It then returns the matching pantries, or an empty result list if none are found.
     *
     * @param getMealPlanListRequest request object containing the User ID
     * @return getMealPlanListResult result object containing the pantries that were created by that User ID
     */
    public GetMealPlanListResult handleRequest(final GetMealPlanListRequest getMealPlanListRequest) {
        log.info("Received GetMealPlanListRequest {}", getMealPlanListRequest);

        List<MealPlan> results = mealPlanDao.getUserMealPlans(getMealPlanListRequest.getUserId());
        List<MealPlanModel> pantryModels = new ModelConverter().toMealPlanModelList(results);

        return GetMealPlanListResult.builder()
                .withMealPlans(pantryModels)
                .build();
    }
}
