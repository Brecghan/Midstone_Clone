//package com.nashss.se.musicplaylistservice.activity;
//
//import com.nashss.se.musicplaylistservice.activity.requests.CreatePantryRequest;
//import com.nashss.se.musicplaylistservice.activity.results.CreatePantryResult;
//import com.nashss.se.musicplaylistservice.dynamodb.PantryDao;
//import com.nashss.se.musicplaylistservice.dynamodb.models.Pantry;
//import com.nashss.se.musicplaylistservice.exceptions.InvalidAttributeValueException;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.verify;
//import static org.mockito.MockitoAnnotations.openMocks;
//
//public class CreatePantryActivityTest {
//    @Mock
//    private PantryDao pantryDao;
//
//    private CreatePantryActivity createPantryActivity;
//
//    @BeforeEach
//    void setUp() {
//        openMocks(this);
//        createPantryActivity = new CreatePantryActivity(pantryDao);
//    }
//
//    @Test
//    public void handleRequest_withTags_createsAndSavesPantryWithTags() {
//        // GIVEN
//        String expectedName = "expectedName";
//        String expectedCustomerId = "expectedCustomerId";
//        int expectedSongCount = 0;
//        List<String> expectedTags = List.of("tag");
//
//        CreatePantryRequest request = CreatePantryRequest.builder()
//                                            .withName(expectedName)
//                                            .withCustomerId(expectedCustomerId)
//                                            .withTags(expectedTags)
//                                            .build();
//
//        // WHEN
//        CreatePantryResult result = createPantryActivity.handleRequest(request);
//
//        // THEN
//        verify(pantryDao).savePantry(any(Pantry.class));
//
//        assertNotNull(result.getPantry().getId());
//        assertEquals(expectedName, result.getPantry().getName());
//        assertEquals(expectedCustomerId, result.getPantry().getCustomerId());
//        assertEquals(expectedSongCount, result.getPantry().getSongCount());
//        assertEquals(expectedTags, result.getPantry().getTags());
//    }
//
//    @Test
//    public void handleRequest_noTags_createsAndSavesPantryWithoutTags() {
//        // GIVEN
//        String expectedName = "expectedName";
//        String expectedCustomerId = "expectedCustomerId";
//        int expectedSongCount = 0;
//
//        CreatePantryRequest request = CreatePantryRequest.builder()
//                                            .withName(expectedName)
//                                            .withCustomerId(expectedCustomerId)
//                                            .build();
//
//        // WHEN
//        CreatePantryResult result = createPantryActivity.handleRequest(request);
//
//        // THEN
//        verify(pantryDao).savePantry(any(Pantry.class));
//
//        assertNotNull(result.getPantry().getId());
//        assertEquals(expectedName, result.getPantry().getName());
//        assertEquals(expectedCustomerId, result.getPantry().getCustomerId());
//        assertEquals(expectedSongCount, result.getPantry().getSongCount());
//        assertNull(result.getPantry().getTags());
//    }
//
//    @Test
//    public void handleRequest_invalidName_throwsInvalidAttributeValueException() {
//        // GIVEN
//        CreatePantryRequest request = CreatePantryRequest.builder()
//                                            .withName("I'm illegal")
//                                            .withCustomerId("customerId")
//                                            .build();
//
//        // WHEN + THEN
//        assertThrows(InvalidAttributeValueException.class, () -> createPantryActivity.handleRequest(request));
//    }
//
//    @Test
//    public void handleRequest_invalidCustomerId_throwsInvalidAttributeValueException() {
//        // GIVEN
//        CreatePantryRequest request = CreatePantryRequest.builder()
//                                            .withName("AllOK")
//                                            .withCustomerId("Jemma's \"illegal\" customer ID")
//                                            .build();
//
//        // WHEN + THEN
//        assertThrows(InvalidAttributeValueException.class, () -> createPantryActivity.handleRequest(request));
//    }
//}