package com.nashss.se.musicplaylistservice.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = CreateMealPlanRequest.Builder.class)
public class CreateMealPlanRequest {
    private final String mealPlanName;
    private final String userId;

    private CreateMealPlanRequest(String mealPlanName, String userId) {
        this.mealPlanName = mealPlanName;
        this.userId = userId;
    }

    public String getMealPlanName() {
        return mealPlanName;
    }


    public String getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "CreateMealPlanRequest{" +
                "mealPlanName='" + mealPlanName + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String mealPlanName;
        private String userId;

        public Builder withMealPlanName(String mealPlanName) {
            this.mealPlanName = mealPlanName;
            return this;
        }

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }
        public CreateMealPlanRequest build() {
            return new CreateMealPlanRequest(mealPlanName, userId);
        }
    }
}
