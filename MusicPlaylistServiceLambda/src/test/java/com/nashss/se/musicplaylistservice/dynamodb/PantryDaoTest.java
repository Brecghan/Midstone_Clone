package com.nashss.se.musicplaylistservice.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.nashss.se.musicplaylistservice.dynamodb.models.Pantry;
import com.nashss.se.musicplaylistservice.metrics.MetricsConstants;
import com.nashss.se.musicplaylistservice.metrics.MetricsPublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class PantryDaoTest {
    @Mock
    private DynamoDBMapper dynamoDBMapper;
    @Mock
    private MetricsPublisher metricsPublisher;


    private PantryDao pantryDao;

    @BeforeEach
    public void setup() {
        initMocks(this);
        pantryDao = new PantryDao(dynamoDBMapper, metricsPublisher);
    }

    @Test
    public void getPlaylist_withPlaylistIdAndUserId_callsMapperWithPartitionKeyAndSortKey() {
        // GIVEN
        String pantryId = "pantryId";
        String userId = "userId";
        when(dynamoDBMapper.load(Pantry.class, userId, pantryId)).thenReturn(new Pantry());

        // WHEN
        Pantry pantry = pantryDao.getPantry(userId, pantryId);

        // THEN
        assertNotNull(pantry);
        verify(dynamoDBMapper).load(Pantry.class, userId, pantryId);
//        verify(metricsPublisher).addCount(eq(MetricsConstants.GETPLAYLIST_PLAYLISTNOTFOUND_COUNT), anyDouble());

    }

    @Test
    public void getPantry_pantryIdNotFound_throwsPantryNotFoundException() {
        // GIVEN
        String nonexistentPlaylistId = "NotReal";
        when(dynamoDBMapper.load(Pantry.class, nonexistentPlaylistId)).thenReturn(null);

        // WHEN + THEN
//        assertThrows(PlaylistNotFoundException.class, () -> pantryDao.getPlaylist(nonexistentPlaylistId));
        verify(metricsPublisher).addCount(eq(MetricsConstants.GETPLAYLIST_PLAYLISTNOTFOUND_COUNT), anyDouble());
    }

    @Test
    public void savePantry_callsMapperWithPlaylist() {
        // GIVEN
        Pantry pantry = new Pantry();

        // WHEN
        Pantry result = pantryDao.savePantry(pantry);

        // THEN
        verify(dynamoDBMapper).save(pantry);
        assertEquals(pantry, result);
    }
}
