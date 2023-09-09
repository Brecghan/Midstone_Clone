package com.nashss.se.musicplaylistservice.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.nashss.se.musicplaylistservice.dynamodb.models.MealPlan;
import com.nashss.se.musicplaylistservice.exceptions.PantryNotFoundException;
import com.nashss.se.musicplaylistservice.metrics.MetricsConstants;
import com.nashss.se.musicplaylistservice.metrics.MetricsPublisher;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

/**
 * Accesses data for a MealPlan using {@link MealPlan} to represent the model in DynamoDB.
 */
@Singleton
public class MealPlanDao {
    private final DynamoDBMapper dynamoDbMapper;
    private final MetricsPublisher metricsPublisher;

    /**
     * Instantiates a PantryDao object.
     *
     * @param dynamoDbMapper   the {@link DynamoDBMapper} used to interact with the playlists table
     * @param metricsPublisher the {@link MetricsPublisher} used to record metrics.
     */
    @Inject
    public MealPlanDao(DynamoDBMapper dynamoDbMapper, MetricsPublisher metricsPublisher) {
        this.dynamoDbMapper = dynamoDbMapper;
        this.metricsPublisher = metricsPublisher;
    }

    /**
     * Returns the {@link MealPlan} corresponding to the specified id.
     *
     * @param userId the User ID
     * @param mealPlanId the MealPlan ID
     * @return the stored Pantry, or null if none was found.
     */
    public MealPlan getMealPlan(String userId, String mealPlanId) {
        MealPlan mealPlan = this.dynamoDbMapper.load(MealPlan.class, userId, mealPlanId);

        if (mealPlan == null) {
            metricsPublisher.addCount(MetricsConstants.GETPANTRY_PANTRYNOTFOUND_COUNT, 1);
            throw new PantryNotFoundException("Could not find pantry with id " + mealPlanId);

        }
        metricsPublisher.addCount(MetricsConstants.GETPANTRY_PANTRYNOTFOUND_COUNT, 0);
        return mealPlan;
    }

    /**
     * Saves (creates or updates) the given mealPlan.
     *
     * @param mealPlan The mealPlan to save
     * @return The MealPlan object that was saved
     */
    public MealPlan saveMealPlan(MealPlan mealPlan) {
        this.dynamoDbMapper.save(mealPlan);
        return mealPlan;
    }

    /**
     * Perform a search (via a "scan") of the MealPlan table for mealPlans matching the given criteria.

     * @param userId a String containing the UserId.
     * @return a List of MealPlan objects that were made by the User.
     */
    public List<MealPlan> getUserMealPlans(String userId) {
        MealPlan mealPlan = new MealPlan();
        mealPlan.setUserId(userId);

        DynamoDBQueryExpression<MealPlan> dynamoDBQueryExpression = new DynamoDBQueryExpression<MealPlan>()
                .withHashKeyValues(mealPlan);
        DynamoDBMapper mapper = new DynamoDBMapper(DynamoDbClientProvider.getDynamoDBClient());

        PaginatedQueryList<MealPlan> mealPlanList = mapper.query(MealPlan.class, dynamoDBQueryExpression);
        return mealPlanList;
    }
}
