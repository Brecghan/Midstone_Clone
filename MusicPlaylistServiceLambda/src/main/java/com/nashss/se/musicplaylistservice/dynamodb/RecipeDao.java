package com.nashss.se.musicplaylistservice.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.nashss.se.musicplaylistservice.dynamodb.models.Recipe;
import com.nashss.se.musicplaylistservice.exceptions.RecipeNotFoundException;
import com.nashss.se.musicplaylistservice.metrics.MetricsConstants;
import com.nashss.se.musicplaylistservice.metrics.MetricsPublisher;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

/**
 * Accesses data for a Recipe using {@link Recipe} to represent the model in DynamoDB.
 */
@Singleton
public class RecipeDao {
    private final DynamoDBMapper dynamoDbMapper;
    private final MetricsPublisher metricsPublisher;

    /**
     * Instantiates a RecipeDao object.
     *
     * @param dynamoDbMapper   the {@link DynamoDBMapper} used to interact with the playlists table
     * @param metricsPublisher the {@link MetricsPublisher} used to record metrics.
     */
    @Inject
    public RecipeDao(DynamoDBMapper dynamoDbMapper, MetricsPublisher metricsPublisher) {
        this.dynamoDbMapper = dynamoDbMapper;
        this.metricsPublisher = metricsPublisher;
    }

    /**
     * Returns the {@link Recipe} corresponding to the specified id.
     *
     * @param recipeId the recipe ID
     * @return the stored Recipe, or null if none was found.
     */
    public Recipe getRecipe(String recipeId) {
        Recipe recipe = this.dynamoDbMapper.load(Recipe.class, recipeId);

        if (recipe == null) {
            metricsPublisher.addCount(MetricsConstants.GETRECIPE_RECIPENOTFOUND_COUNT, 1);
            throw new RecipeNotFoundException("Could not find recipe with id " + recipeId);

        }
        metricsPublisher.addCount(MetricsConstants.GETRECIPE_RECIPENOTFOUND_COUNT, 0);
        return recipe;
    }

//    /**
//     * Saves (creates or updates) the given pantry.
//     *
//     * @param pantry The pantry to save
//     * @return The Pantry object that was saved
//     */
//    public Pantry savePantry(Pantry pantry) {
//        this.dynamoDbMapper.save(pantry);
//        return pantry;
//    }
//
//    /**
//     * Perform a search (via a "scan") of the pantry table for pantries matching the given criteria.
//
//     * @param userId a String containing the UserId.
//     * @return a List of Pantry objects that were made by the User.
//     */
//    public List<Pantry> getUserPantries(String userId) {
//        Pantry pantry = new Pantry();
//        pantry.setUserId(userId);
//
//        DynamoDBQueryExpression<Pantry> dynamoDBQueryExpression = new DynamoDBQueryExpression<Pantry>()
//                .withHashKeyValues(pantry);
//        DynamoDBMapper mapper = new DynamoDBMapper(DynamoDbClientProvider.getDynamoDBClient());
//
//        PaginatedQueryList<Pantry> pantryList = mapper.query(Pantry.class, dynamoDBQueryExpression);
//        return pantryList;
//    }
}
