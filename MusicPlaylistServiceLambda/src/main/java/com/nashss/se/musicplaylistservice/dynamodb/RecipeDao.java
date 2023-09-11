package com.nashss.se.musicplaylistservice.dynamodb;

import com.nashss.se.musicplaylistservice.dynamodb.models.Recipe;
import com.nashss.se.musicplaylistservice.exceptions.RecipeNotFoundException;
import com.nashss.se.musicplaylistservice.metrics.MetricsConstants;
import com.nashss.se.musicplaylistservice.metrics.MetricsPublisher;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;

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

    /**
     * Saves (creates or updates) the given Recipe.
     *
     * @param recipe The recipe to save
     * @return The Recipe object that was saved
     */
    public Recipe saveRecipe(Recipe recipe) {
        this.dynamoDbMapper.save(recipe);
        return recipe;
    }
    /**
     * Perform a search (via a "scan") of the Recipe table for recipes matching the given criteria.
     * <p>
     * The only criteria is if a recipe region is specified, in which only recipes from that region will
     * be returned. If no region is specified, all recipes will be returned
     *
     * @param region a String containing the recipe region requested or null.
     * @return a List of Recipe objects that match the search criteria.
     */
    public List<Recipe> getRecipeList(String region) {
        if (region == null) {
            DynamoDBScanExpression dynamoDBScanExpression = new DynamoDBScanExpression();
            return this.dynamoDbMapper.scan(Recipe.class, dynamoDBScanExpression);
        } else {
            Map<String, AttributeValue> valueMap = new HashMap<>();
            valueMap.put(":region", new AttributeValue().withS(region));
            DynamoDBQueryExpression<Recipe> queryExpression = new DynamoDBQueryExpression<Recipe>()
                    .withIndexName(Recipe.RECIPE_REGION_INDEX)
                    .withConsistentRead(false)
                    .withKeyConditionExpression("region = :region")
                    .withExpressionAttributeValues(valueMap);

            return dynamoDbMapper.query(Recipe.class, queryExpression);
        }
    }
}
