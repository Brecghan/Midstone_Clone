package com.nashss.se.musicplaylistservice.models;

import com.nashss.se.musicplaylistservice.dynamodb.models.Ingredient;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


public class RecipeModel {
    private final String recipeId;
    private final String recipeName;
    private final String recipeDescription;
    private final Set<Ingredient> neededIngredients;
    private final String region;
    private final Set<String> dietaryRestrictions;


    private RecipeModel(String recipeId, String recipeName, String recipeDescription,
                        Set<Ingredient> neededIngredients, String region, Set<String> dietaryRestrictions) {
        this.recipeId = recipeId;
        this.recipeName = recipeName;
        this.recipeDescription = recipeDescription;
        this.neededIngredients = neededIngredients;
        this.region = region;
        this.dietaryRestrictions = dietaryRestrictions;
    }

    public String getRecipeId() {
        return recipeId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public String getRecipeDescription() {
        return recipeDescription; }

    public Set<Ingredient> getNeededIngredients() {
        return new HashSet<>(neededIngredients);
    }

    public String getRegion() {
        return region; }

    public Set<String> getDietaryRestrictions() {
        return new HashSet<>(dietaryRestrictions);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RecipeModel that = (RecipeModel) o;

        return  Objects.equals(recipeId, that.recipeId) && Objects.equals(recipeName, that.recipeName) &&
                Objects.equals(recipeDescription, that.recipeDescription) &&
                Objects.equals(neededIngredients, that.neededIngredients) &&
                Objects.equals(region, that.region) &&
                Objects.equals(dietaryRestrictions, that.dietaryRestrictions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipeId, recipeName, recipeDescription, neededIngredients, region, dietaryRestrictions);
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String recipeId;
        private String recipeName;
        private String recipeDescription;
        private Set<Ingredient> neededIngredients;
        private String region;
        private Set<String> dietaryRestrictions;

        public Builder withRecipeId(String recipeId) {
            this.recipeId = recipeId;
            return this;
        }

        public Builder withRecipeName(String recipeName) {
            this.recipeName = recipeName;
            return this;
        }

        public Builder withRecipeDescription(String recipeDescription) {
            this.recipeDescription = recipeDescription;
            return this;
        }

        public Builder withNeededIngredients(Set<Ingredient> neededIngredients) {
//            this.inventory = new HashSet<>(inventory);
            if (null == neededIngredients) {
                this.neededIngredients = null;
            } else {
                this.neededIngredients = new HashSet<>(neededIngredients);
            }
            return this;
        }

        public Builder withRegion(String region) {
            this.region = region;
            return this;
        }

        public Builder withDietaryRestrictions(Set<String> dietaryRestrictions) {
//            this.inventory = new HashSet<>(inventory);
            if (null == dietaryRestrictions) {
                this.dietaryRestrictions = null;
            } else {
                this.dietaryRestrictions = new HashSet<>(dietaryRestrictions);
            }
            return this;
        }

        public RecipeModel build() {
            return new RecipeModel(recipeId, recipeName, recipeDescription, neededIngredients, region, dietaryRestrictions);
        }
    }
}
