package com.nashss.se.musicplaylistservice.activity.requests;

public class GetRecipeDetailsRequest {
    private final String recipeId;

    private GetRecipeDetailsRequest(String recipeId) {
        this.recipeId = recipeId;
    }

    public String getRecipeId() {
        return recipeId;
    }


    @Override
    public String toString() {
        return "GetRecipeDetailsRequest{" +
                "recipeId='" + recipeId + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String recipeId;

        public Builder withRecipeId(String recipeId) {
            this.recipeId = recipeId;
            return this;
        }

        public GetRecipeDetailsRequest build() {
            return new GetRecipeDetailsRequest(recipeId);
        }
    }
}
