package com.nashss.se.musicplaylistservice.activity;

import com.nashss.se.musicplaylistservice.activity.requests.UpdatePantryIngredientRequest;
import com.nashss.se.musicplaylistservice.activity.results.UpdatePantryIngredientResult;
import com.nashss.se.musicplaylistservice.dynamodb.PantryDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.Ingredient;
import com.nashss.se.musicplaylistservice.dynamodb.models.Pantry;
import com.nashss.se.musicplaylistservice.metrics.MetricsPublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class UpdatePantryIngredientActivityTest {
    @Mock
    private PantryDao pantryDao;

    @Mock
    private MetricsPublisher metricsPublisher;

    private UpdatePantryIngredientActivity updatePantryIngredientActivity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        updatePantryIngredientActivity = new UpdatePantryIngredientActivity(pantryDao, metricsPublisher);
    }

    @Test
    public void handleRequest_goodRequest_updatesPantryInventory() {
        // GIVEN
        String pantryId = "id";
        String userId = "id";
        String ingredientName = "flour";
        Double ingredientQuantity = 1.0;
        String unitOfMeasure = "CUP";

        UpdatePantryIngredientRequest request = UpdatePantryIngredientRequest.builder()
                                            .withPantryId(pantryId)
                                            .withIngredientName(ingredientName)
                                            .withUserId(userId)
                                            .withIngredientQuantity(ingredientQuantity)
                                            .withUnitOfMeasure(unitOfMeasure)
                                            .build();

        Pantry startingPantry = new Pantry();
        startingPantry.setPantryId(pantryId);
        startingPantry.setUserId(userId);

        Ingredient ingredient = new Ingredient();
        ingredient.setIngredientName("Sugar");
        ingredient.setQuantity(1.0);
        ingredient.setUnitOfMeasure(Ingredient.UnitOfMeasure.valueOf("CUP"));

        Set<Ingredient> ingredientSet = new HashSet<>();
        ingredientSet.add(ingredient);

        startingPantry.setInventory(ingredientSet);

        when(pantryDao.getPantry(userId, pantryId)).thenReturn(startingPantry);
        when(pantryDao.savePantry(startingPantry)).thenReturn(startingPantry);

        // WHEN
        UpdatePantryIngredientResult result = updatePantryIngredientActivity.handleRequest(request);

        // THEN
        assertEquals(2, startingPantry.getInventory().size());
//        assertNotNull();
//        assertEquals(expectedCustomerId, result.getPantry().getCustomerId());
//        assertEquals(expectedSongCount, result.getPantry().getSongCount());
    }

//    @Test
//    public void handleRequest_invalidName_throwsInvalidAttributeValueException() {
//        // GIVEN
//        UpdatePantryIngredientRequest request = UpdatePantryIngredientRequest.builder()
//                                            .withId("id")
//                                            .withName("I'm illegal")
//                                            .withCustomerId("customerId")
//                                            .build();
//
//        // WHEN + THEN
//        try {
//            updatePantryIngredientActivity.handleRequest(request);
//            fail("Expected InvalidAttributeValueException to be thrown");
//        } catch (InvalidAttributeValueException e) {
//            verify(metricsPublisher).addCount(MetricsConstants.UPDATEPLAYLIST_INVALIDATTRIBUTEVALUE_COUNT, 1);
//            verify(metricsPublisher).addCount(MetricsConstants.UPDATEPLAYLIST_INVALIDATTRIBUTECHANGE_COUNT, 0);
//        }
//    }
//
//    @Test
//    public void handleRequest_playlistDoesNotExist_throwsPlaylistNotFoundException() {
//        // GIVEN
//        String id = "id";
//        UpdatePantryIngredientRequest request = UpdatePantryIngredientRequest.builder()
//                                            .withId(id)
//                                            .withName("name")
//                                            .withCustomerId("customerId")
//                                            .build();
//
//        when(pantryDao.getPantry(id)).thenThrow(new PlaylistNotFoundException());
//
//        // THEN
//        assertThrows(PlaylistNotFoundException.class, () -> updatePantryIngredientActivity.handleRequest(request));
//    }
//
//    @Test
//    public void handleRequest_customerIdNotMatch_throwsSecurityException() {
//        // GIVEN
//        String id = "id";
//        UpdatePantryIngredientRequest request = UpdatePantryIngredientRequest.builder()
//                                            .withId(id)
//                                            .withName("name")
//                                            .withCustomerId("customerId")
//                                            .build();
//
//        Playlist differentCustomerIdPlaylist = new Playlist();
//        differentCustomerIdPlaylist.setCustomerId("different");
//
//        when(pantryDao.getPantry(id)).thenReturn(differentCustomerIdPlaylist);
//
//        // WHEN + THEN
//        try {
//            updatePantryIngredientActivity.handleRequest(request);
//            fail("Expected InvalidAttributeChangeException to be thrown");
//        } catch (SecurityException e) {
//            verify(metricsPublisher).addCount(MetricsConstants.UPDATEPLAYLIST_INVALIDATTRIBUTEVALUE_COUNT, 0);
//            verify(metricsPublisher).addCount(MetricsConstants.UPDATEPLAYLIST_INVALIDATTRIBUTECHANGE_COUNT, 1);
//        }
//    }
}
