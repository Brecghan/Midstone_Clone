package com.nashss.se.musicplaylistservice.utils;

import org.apache.commons.lang3.RandomStringUtils;

public final class DigitalPantryServiceUtils {
    static final int GENERATED_ID_LENGTH = 5;

    private DigitalPantryServiceUtils() {
    }

    /**
     * Provides a generated pantry ID.
     * @return String of a generated ID.
     */
    public static String generatePantryId() {
        return RandomStringUtils.randomAlphanumeric(GENERATED_ID_LENGTH);
    }

    /**
     * Provides a generated MealPlan ID.
     * @return String of a generated ID.
     */
    public static String generateMealPlanId() {
        return RandomStringUtils.randomAlphanumeric(GENERATED_ID_LENGTH);
    }

}
