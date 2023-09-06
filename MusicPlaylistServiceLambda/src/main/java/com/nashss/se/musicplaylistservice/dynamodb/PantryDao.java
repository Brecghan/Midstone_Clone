package com.nashss.se.musicplaylistservice.dynamodb;

import com.nashss.se.musicplaylistservice.dynamodb.models.Pantry;
import com.nashss.se.musicplaylistservice.exceptions.PantryNotFoundException;
import com.nashss.se.musicplaylistservice.metrics.MetricsConstants;
import com.nashss.se.musicplaylistservice.metrics.MetricsPublisher;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Accesses data for a pantry using {@link Pantry} to represent the model in DynamoDB.
 */
@Singleton
public class PantryDao {
    private final DynamoDBMapper dynamoDbMapper;
    private final MetricsPublisher metricsPublisher;

    /**
     * Instantiates a PantryDao object.
     *
     * @param dynamoDbMapper   the {@link DynamoDBMapper} used to interact with the playlists table
     * @param metricsPublisher the {@link MetricsPublisher} used to record metrics.
     */
    @Inject
    public PantryDao(DynamoDBMapper dynamoDbMapper, MetricsPublisher metricsPublisher) {
        this.dynamoDbMapper = dynamoDbMapper;
        this.metricsPublisher = metricsPublisher;
    }

    /**
     * Returns the {@link Pantry} corresponding to the specified id.
     *
     * @param userId the User ID
     * @param pantryId the Pantry ID
     * @return the stored Pantry, or null if none was found.
     */
    public Pantry getPantry(String userId, String pantryId) {
        Pantry pantry = this.dynamoDbMapper.load(Pantry.class, userId, pantryId);

        if (pantry == null) {
            metricsPublisher.addCount(MetricsConstants.GETPANTRY_PANTRYNOTFOUND_COUNT, 1);
            throw new PantryNotFoundException("Could not find pantry with id " + pantryId);

        }
        metricsPublisher.addCount(MetricsConstants.GETPANTRY_PANTRYNOTFOUND_COUNT, 0);
        return pantry;
    }

    /**
     * Saves (creates or updates) the given pantry.
     *
     * @param pantry The pantry to save
     * @return The Pantry object that was saved
     */
    public Pantry savePantry(Pantry pantry) {
        this.dynamoDbMapper.save(pantry);
        return pantry;
    }

    /**
     * Perform a search (via a "scan") of the pantry table for pantries matching the given criteria.

     * @param userId a String containing the UserId.
     * @return a List of Pantry objects that were made by the User.
     */
    public List<Pantry> getUserPantries(String userId) {
        Pantry pantry = new Pantry();
        pantry.setUserId(userId);

        DynamoDBQueryExpression<Pantry> dynamoDBQueryExpression = new DynamoDBQueryExpression<Pantry>()
                .withHashKeyValues(pantry);
        DynamoDBMapper mapper = new DynamoDBMapper(DynamoDbClientProvider.getDynamoDBClient());

        PaginatedQueryList<Pantry> pantryList = mapper.query(Pantry.class, dynamoDBQueryExpression);
        return pantryList;
    }
}
