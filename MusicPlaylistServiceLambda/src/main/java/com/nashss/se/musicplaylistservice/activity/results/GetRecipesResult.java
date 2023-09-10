package com.nashss.se.musicplaylistservice.activity.results;

import com.nashss.se.musicplaylistservice.models.RecipeModel;

import java.util.ArrayList;
import java.util.List;

public class GetRecipesResult {
    private final List<RecipeModel> recipes;

    private GetRecipesResult(List<RecipeModel> recipes) {
        this.recipes = recipes;
    }

    public List<RecipeModel> getRecipes() {
        return recipes;
    }

    @Override
    public String toString() {
        return "GetRecipesResult{" +
                "recipes=" + recipes +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<RecipeModel> recipes ;

        public Builder withRecipes(List<RecipeModel> recipes) {
            this.recipes = new ArrayList<>(recipes);
            return this;
        }

        public GetRecipesResult build() {
            return new GetRecipesResult(recipes);
        }
    }
}
