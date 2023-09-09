package com.nashss.se.musicplaylistservice.activity.results;

import com.nashss.se.musicplaylistservice.models.RecipeModel;

import java.util.ArrayList;
import java.util.List;

public class GetMealPlanRecipeSetResult {
    private final List<RecipeModel> recipeSet;

    private GetMealPlanRecipeSetResult(List<RecipeModel> recipeSet) {
        this.recipeSet = recipeSet;
    }

    public List<RecipeModel> getRecipeSet() {
        return new ArrayList<>(recipeSet);
    }

    @Override
    public String toString() {
        return "GetMealPlanRecipeSetResult{" +
                "recipeSet=" + recipeSet +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<RecipeModel> recipeSet;

        public Builder withRecipeSet(List<RecipeModel> recipeSet) {
            this.recipeSet = new ArrayList<>(recipeSet);
            return this;
        }

        public GetMealPlanRecipeSetResult build() {
            return new GetMealPlanRecipeSetResult(recipeSet);
        }
    }
}
