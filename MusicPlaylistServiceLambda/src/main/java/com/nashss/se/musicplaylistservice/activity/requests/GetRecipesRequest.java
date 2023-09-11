package com.nashss.se.musicplaylistservice.activity.requests;

public class GetRecipesRequest {
    private final String recipeRegion;

    private GetRecipesRequest(String recipeRegion) {
        this.recipeRegion = recipeRegion;
    }

    public String getRecipeRegion() {
        return recipeRegion;
    }

    @Override
    public String toString() {
        return "GetRecipesRequest{" +
                "recipeRegion='" + recipeRegion + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String recipeRegion;

        public Builder withRecipeRegion(String recipeRegion) {
            this.recipeRegion = recipeRegion;
            return this;
        }

        public GetRecipesRequest build() {
            return new GetRecipesRequest(recipeRegion);
        }
    }
}
