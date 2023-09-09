package com.nashss.se.musicplaylistservice.models;

import com.nashss.se.musicplaylistservice.dynamodb.models.Ingredient;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


public class MealPlanModel {
    private final String mealPlanId;
    private final String mealPlanName;
    private final String userId;
    private final Set<String> recipeSet;

    private MealPlanModel(String mealPlanId, String mealPlanName, String userId,
                          Set<String> recipeSet) {
        this.mealPlanId = mealPlanId;
        this.mealPlanName = mealPlanName;
        this.userId = userId;
        this.recipeSet = recipeSet;
    }

    public String getMealPlanId() {
        return mealPlanId;
    }

    public String getMealPlanName() {
        return mealPlanName;
    }

    public String getUserId() {
        return userId;
    }

    public Set<String> getRecipeSet() {
        return new HashSet<>(recipeSet);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MealPlanModel that = (MealPlanModel) o;

        return  Objects.equals(mealPlanId, that.mealPlanId) && Objects.equals(mealPlanName, that.mealPlanName) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(recipeSet, that.recipeSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mealPlanId, mealPlanName, userId, recipeSet);
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String mealPlanId;
        private String mealPlanName;
        private String userId;
        private Set<String> recipeSet;

        public Builder withMealPlanId(String mealPlanId) {
            this.mealPlanId = mealPlanId;
            return this;
        }

        public Builder withMealPlanName(String mealPlanName) {
            this.mealPlanName = mealPlanName;
            return this;
        }

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder withRecipeSet(Set<String> recipeSet) {
//            this.recipeSet = new HashSet<>(recipeSet);
            if (null == recipeSet) {
                this.recipeSet = null;
            } else {
                this.recipeSet = new HashSet<>(recipeSet);
            }
            return this;
        }

        public MealPlanModel build() {
            return new MealPlanModel(mealPlanId, mealPlanName, userId, recipeSet);
        }
    }
}
