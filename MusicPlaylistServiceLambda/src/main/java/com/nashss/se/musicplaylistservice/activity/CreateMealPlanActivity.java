package com.nashss.se.musicplaylistservice.activity;

import com.nashss.se.musicplaylistservice.activity.requests.CreateMealPlanRequest;
import com.nashss.se.musicplaylistservice.activity.results.CreateMealPlanResult;
import com.nashss.se.musicplaylistservice.converters.ModelConverter;
import com.nashss.se.musicplaylistservice.dynamodb.MealPlanDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.MealPlan;
import com.nashss.se.musicplaylistservice.models.MealPlanModel;
import com.nashss.se.musicplaylistservice.utils.DigitalPantryServiceUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import javax.inject.Inject;

/**
 * Implementation of the CreateMealPlanActivity for the DigitalPantryService's CreateMealPlan API.
 * <p>
 * This API allows the customer to create a new Meal Plan with no data.
 */
public class CreateMealPlanActivity {
    private final Logger log = LogManager.getLogger();
    private final MealPlanDao mealPlanDao;

    /**
     * Instantiates a new CreateMealPlanActivity object.
     *
     * @param mealPlanDao MealPlanDao to access the Meal Plans table.
     */
    @Inject
    public CreateMealPlanActivity(MealPlanDao mealPlanDao) {
        this.mealPlanDao = mealPlanDao;
    }

    /**
     * This method handles the incoming request by persisting a new meal plan
     * with the provided meal plan name and user ID from the request.
     * <p>
     * It then returns the newly created meal plan.
     * <p>
     *
     * @param createMealPlanRequest request object containing the meal plan name and user ID
     *                              associated with it
     * @return createMealPlanResult result object containing the API defined {@link MealPlanModel}
     */
    public CreateMealPlanResult handleRequest(final CreateMealPlanRequest createMealPlanRequest) {
        log.info("Received CreateMealPlanRequest {}", createMealPlanRequest);

        MealPlan newMealPlan = new MealPlan();
        newMealPlan.setMealPlanId(DigitalPantryServiceUtils.generateMealPlanId());
        newMealPlan.setMealPlanName(createMealPlanRequest.getMealPlanName());
        newMealPlan.setUserId(createMealPlanRequest.getUserId());
        newMealPlan.setRecipeSet(new HashSet<>());

        mealPlanDao.saveMealPlan(newMealPlan);

        MealPlanModel mealPlanModel = new ModelConverter().toMealPlanModel(newMealPlan);
        return CreateMealPlanResult.builder()
                .withMealPlan(mealPlanModel)
                .build();
    }
}
