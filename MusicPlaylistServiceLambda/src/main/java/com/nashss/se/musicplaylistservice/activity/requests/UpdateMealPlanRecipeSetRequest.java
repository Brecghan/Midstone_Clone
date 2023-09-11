package com.nashss.se.musicplaylistservice.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = UpdateMealPlanRecipeSetRequest.Builder.class)
public class UpdateMealPlanRecipeSetRequest {
    private final String mealPlanId;
    private final String recipeId;
    private final String userId;

    private UpdateMealPlanRecipeSetRequest(String mealPlanId, String recipeId,
                                           String userId) {
        this.mealPlanId = mealPlanId;
        this.recipeId = recipeId;
        this.userId = userId;
    }

    public String getMealPlanId() {
        return mealPlanId;
    }

    public String getRecipeId() {
        return recipeId;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "UpdatePlaylistRequest{" +
                "mealPlanId='" + mealPlanId + '\'' +
                ", recipeId='" + recipeId + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String mealPlanId;
        private String recipeId;
        private String userId;

        public Builder withMealPlanId(String mealPlanId) {
            this.mealPlanId = mealPlanId;
            return this;
        }

        public Builder withRecipeId(String recipeId) {
            this.recipeId = recipeId;
            return this;
        }

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public UpdateMealPlanRecipeSetRequest build() {
            return new UpdateMealPlanRecipeSetRequest(mealPlanId, recipeId, userId);
        }
    }
}
