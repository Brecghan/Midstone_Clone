package com.nashss.se.musicplaylistservice.activity.requests;

public class CompareRecipeAndInventoryRequest {
    private final String userId;
    private final String pantryId;
    private final String recipeId;

    private CompareRecipeAndInventoryRequest(String userId, String pantryId, String recipeId) {
        this.userId = userId;
        this.pantryId = pantryId;
        this.recipeId = recipeId;
    }

    public String getUserId() {
        return userId;
    }

    public String getPantryId() {
        return pantryId;
    }

    public String getRecipeId() {
        return recipeId;
    }

    @Override
    public String toString() {
        return "CompareRecipeAndInventoryRequest{" +
                "userId='" + userId + '\'' +
                ", pantryId='" + pantryId + '\'' +
                ", recipeId='" + recipeId + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String userId;
        private String pantryId;
        private String recipeId;

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder withPantryId(String pantryId) {
            this.pantryId = pantryId;
            return this;
        }

        public Builder withRecipeId(String recipeId) {
            this.recipeId = recipeId;
            return this;
        }

        public CompareRecipeAndInventoryRequest build() {
            return new CompareRecipeAndInventoryRequest(userId, pantryId, recipeId);
        }
    }
}
