package com.nashss.se.musicplaylistservice.activity.results;

import com.nashss.se.musicplaylistservice.models.MealPlanModel;

public class UpdateMealPlanRecipeSetResult {
    private final MealPlanModel mealPlan;

    private UpdateMealPlanRecipeSetResult(MealPlanModel mealPlan) {
        this.mealPlan = mealPlan;
    }

    public MealPlanModel getMealPlan() {
        return mealPlan;
    }

    @Override
    public String toString() {
        return "UpdateMealPlanRecipeSetResult{" +
                "mealPlan=" + mealPlan +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private MealPlanModel mealPlan;

        public Builder withMealPlan(MealPlanModel mealPlan) {
            this.mealPlan = mealPlan;
            return this;
        }

        public UpdateMealPlanRecipeSetResult build() {
            return new UpdateMealPlanRecipeSetResult(mealPlan);
        }
    }
}
