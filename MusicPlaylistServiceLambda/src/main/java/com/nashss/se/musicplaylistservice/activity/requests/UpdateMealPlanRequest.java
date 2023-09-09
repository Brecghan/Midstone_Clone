package com.nashss.se.musicplaylistservice.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = UpdateMealPlanRequest.Builder.class)
public class UpdateMealPlanRequest {
    private final String userId;
    private final String mealPlanName;
    private final String mealPlanId;

    private UpdateMealPlanRequest(String userId, String mealPlanName, String mealPlanId) {
        this.userId = userId;
        this.mealPlanName = mealPlanName;
        this.mealPlanId = mealPlanId;
    }

    public String getUserId() {
        return userId;
    }

    public String getMealPlanName() {
        return mealPlanName;
    }

    public String getMealPlanId() {
        return mealPlanId;
    }

    @Override
    public String toString() {
        return "UpdateMealPlanRequest{" +
                "userId='" + userId + '\'' +
                ", mealPlanName='" + mealPlanName + '\'' +
                ", mealPlanId='" + mealPlanId + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String userId;
        private String mealPlanName;
        private String mealPlanId;

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder withMealPlanName(String mealPlanName) {
            this.mealPlanName = mealPlanName;
            return this;
        }

        public Builder withMealPlanId(String mealPlanId) {
            this.mealPlanId = mealPlanId;
            return this;
        }

        public UpdateMealPlanRequest build() {
            return new UpdateMealPlanRequest(userId, mealPlanName, mealPlanId);
        }
    }
}
