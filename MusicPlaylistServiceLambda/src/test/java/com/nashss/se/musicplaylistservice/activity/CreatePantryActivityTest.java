package com.nashss.se.musicplaylistservice.activity;

import com.nashss.se.musicplaylistservice.activity.requests.CreatePantryRequest;
import com.nashss.se.musicplaylistservice.activity.results.CreatePantryResult;
import com.nashss.se.musicplaylistservice.dynamodb.PantryDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.Pantry;
import com.nashss.se.musicplaylistservice.exceptions.InvalidAttributeValueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

public class CreatePantryActivityTest {
    @Mock
    private PantryDao pantryDao;

    private CreatePantryActivity createPantryActivity;

    @BeforeEach
    void setUp() {
        openMocks(this);
        createPantryActivity = new CreatePantryActivity(pantryDao);
    }

    @Test
    public void handleRequest_withGoodData_createsAndSavesPantry() {
        // GIVEN
        String expectedName = "expectedName";
        String expectedUserId = "expectedUserId";

        CreatePantryRequest request = CreatePantryRequest.builder()
                                            .withPantryName(expectedName)
                                            .withUserId(expectedUserId)
                                            .build();

        // WHEN
        CreatePantryResult result = createPantryActivity.handleRequest(request);

        // THEN
        verify(pantryDao).savePantry(any(Pantry.class));

        assertNotNull(result.getPantry().getPantryId());
        assertEquals(expectedName, result.getPantry().getPantryName());
        assertEquals(expectedUserId, result.getPantry().getUserId());
        assertNotNull(result.getPantry().getInventory());
    }
}