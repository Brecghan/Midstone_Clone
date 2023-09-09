package com.nashss.se.musicplaylistservice.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = UpdateMealPlanRequest.Builder.class)
public class UpdateMealPlanRequest {
    private final String userId;
    private final String pantryName;
    private final String pantryId;

    private UpdateMealPlanRequest(String userId, String pantryName, String pantryId) {
        this.userId = userId;
        this.pantryName = pantryName;
        this.pantryId = pantryId;
    }

    public String getUserId() {
        return userId;
    }

    public String getPantryName() {
        return pantryName;
    }

    public String getPantryId() {
        return pantryId;
    }

    @Override
    public String toString() {
        return "UpdatePantryRequest{" +
                "userId='" + userId + '\'' +
                ", pantryName='" + pantryName + '\'' +
                ", pantryId='" + pantryId + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String userId;
        private String pantryName;
        private String pantryId;

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder withPantryName(String pantryName) {
            this.pantryName = pantryName;
            return this;
        }

        public Builder withPantryId(String pantryId) {
            this.pantryId = pantryId;
            return this;
        }

        public UpdateMealPlanRequest build() {
            return new UpdateMealPlanRequest(userId, pantryName, pantryId);
        }
    }
}
