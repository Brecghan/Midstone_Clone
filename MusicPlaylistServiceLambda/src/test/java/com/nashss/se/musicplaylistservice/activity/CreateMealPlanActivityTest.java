package com.nashss.se.musicplaylistservice.activity;

import com.nashss.se.musicplaylistservice.activity.requests.CreateMealPlanRequest;
import com.nashss.se.musicplaylistservice.activity.results.CreateMealPlanResult;
import com.nashss.se.musicplaylistservice.dynamodb.MealPlanDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.MealPlan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

public class CreateMealPlanActivityTest {
    @Mock
    private MealPlanDao mealPlanDao;

    private CreateMealPlanActivity createMealPlanActivity;

    @BeforeEach
    void setUp() {
        openMocks(this);
        createMealPlanActivity = new CreateMealPlanActivity(mealPlanDao);
    }

    @Test
    public void handleRequest_withGoodData_createsAndSavesPantry() {
        // GIVEN
        String expectedName = "expectedName";
        String expectedUserId = "expectedUserId";

        CreateMealPlanRequest request = CreateMealPlanRequest.builder()
                                            .withMealPlanName(expectedName)
                                            .withUserId(expectedUserId)
                                            .build();

        // WHEN
        CreateMealPlanResult result = createMealPlanActivity.handleRequest(request);

        // THEN
        verify(mealPlanDao).saveMealPlan(any(MealPlan.class));

        assertNotNull(result.getMealPlan().getMealPlanId());
        assertEquals(expectedName, result.getMealPlan().getMealPlanName());
        assertEquals(expectedUserId, result.getMealPlan().getUserId());
//        assertNotNull(result.getPantry().getInventory());
    }
}
