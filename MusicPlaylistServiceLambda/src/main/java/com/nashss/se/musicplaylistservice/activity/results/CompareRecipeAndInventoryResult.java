package com.nashss.se.musicplaylistservice.activity.results;

import com.nashss.se.musicplaylistservice.models.IngredientModel;

import java.util.ArrayList;
import java.util.List;

public class CompareRecipeAndInventoryResult {
    private final List<IngredientModel> missingIngredients;

    private CompareRecipeAndInventoryResult(List<IngredientModel> missingIngredients) {
        this.missingIngredients = missingIngredients;
    }

    public List<IngredientModel> getMissingIngredients() {
        return new ArrayList<>(missingIngredients);
    }

    @Override
    public String toString() {
        return "CompareRecipeAndInventoryResult{" +
                "missingIngredients=" + missingIngredients +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<IngredientModel> missingIngredients;

        public Builder withMissingIngredients(List<IngredientModel> missingIngredients) {
            this.missingIngredients = new ArrayList<>(missingIngredients);
            return this;
        }

        public CompareRecipeAndInventoryResult build() {
            return new CompareRecipeAndInventoryResult(missingIngredients);
        }
    }
}
