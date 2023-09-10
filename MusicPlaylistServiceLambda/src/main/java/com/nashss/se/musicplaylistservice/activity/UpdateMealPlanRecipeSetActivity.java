package com.nashss.se.musicplaylistservice.activity;

import com.nashss.se.musicplaylistservice.activity.requests.UpdateMealPlanRecipeSetRequest;
import com.nashss.se.musicplaylistservice.activity.results.UpdateMealPlanRecipeSetResult;
import com.nashss.se.musicplaylistservice.converters.ModelConverter;
import com.nashss.se.musicplaylistservice.dynamodb.MealPlanDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.MealPlan;
import com.nashss.se.musicplaylistservice.metrics.MetricsPublisher;
import com.nashss.se.musicplaylistservice.models.MealPlanModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.Set;

/**
 * Implementation of the UpdatePantryActivity for the DigitalPantryService's UpdatePantry API.
 *
 * This API allows the customer to update their saved pantry's information.
 */
public class UpdateMealPlanRecipeSetActivity {
    private final Logger log = LogManager.getLogger();
    private final MealPlanDao mealPlanDao;
    private final MetricsPublisher metricsPublisher;

    /**
     * Instantiates a new UpdatePantryActivity object.
     *
     * @param mealPlanDao MealPlanDao to access the Meal Plan table.
     * @param metricsPublisher MetricsPublisher to publish metrics.
     */
    @Inject
    public UpdateMealPlanRecipeSetActivity(MealPlanDao mealPlanDao, MetricsPublisher metricsPublisher) {
        //super(UpdateMealPlanRecipeSetRequest.class);
        this.mealPlanDao = mealPlanDao;
        this.metricsPublisher = metricsPublisher;
    }

    /**
     * This method handles the incoming request by retrieving the Meal Plan, updating it,
     * and persisting the Meal Plan.
     * <p>
     * It then returns the updated Meal Plan.
     * <p>
     * If the Meal Plan does not exist, this should throw a MealPlanNotFoundException.
     * <p>
     *
     * @param updateMealPlanRecipeSetRequest request object containing the Meal Plan ID,
     * userId, and Recipe ID
     * @return updateMealPlanRecipeSetResult result object containing the API defined {@link MealPlanModel}
     */
    public UpdateMealPlanRecipeSetResult handleRequest(final
                                                      UpdateMealPlanRecipeSetRequest updateMealPlanRecipeSetRequest) {
        log.info("Received UpdateMealPlanRecipeSetRequest {}", updateMealPlanRecipeSetRequest);

        MealPlan mealPlan = mealPlanDao.getMealPlan(updateMealPlanRecipeSetRequest.getUserId(),
                updateMealPlanRecipeSetRequest.getMealPlanId());

        Set<String> recipeIdSet = mealPlan.getRecipeSet();
        recipeIdSet.add(updateMealPlanRecipeSetRequest.getRecipeId());
        mealPlan.setRecipeSet(recipeIdSet);

        mealPlan = mealPlanDao.saveMealPlan(mealPlan);

        return UpdateMealPlanRecipeSetResult.builder()
                .withMealPlan(new ModelConverter().toMealPlanModel(mealPlan))
                .build();
    }
}
