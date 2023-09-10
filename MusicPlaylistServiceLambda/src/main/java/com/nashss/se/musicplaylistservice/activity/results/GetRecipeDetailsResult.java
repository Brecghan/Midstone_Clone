package com.nashss.se.musicplaylistservice.activity.results;

import com.nashss.se.musicplaylistservice.models.RecipeModel;


public class GetRecipeDetailsResult {
    private final RecipeModel recipe;

    private GetRecipeDetailsResult(RecipeModel recipe) {
        this.recipe = recipe;
    }

    public RecipeModel getRecipe() {
        return recipe;
    }

    @Override
    public String toString() {
        return "GetRecipeDetailsResult{" +
                "recipe=" + recipe +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private RecipeModel recipe;

        public Builder withRecipe(RecipeModel recipe) {
            this.recipe = recipe;
            return this;
        }

        public GetRecipeDetailsResult build() {
            return new GetRecipeDetailsResult(recipe);
        }
    }
}
