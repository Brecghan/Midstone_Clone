package com.nashss.se.musicplaylistservice.activity.results;

import com.nashss.se.musicplaylistservice.models.MealPlanModel;

public class UpdateMealPlanResult {
    private final MealPlanModel mealPlan;

    private UpdateMealPlanResult(MealPlanModel mealPlan) {
        this.mealPlan = mealPlan;
    }

    public MealPlanModel getMealPlan() {
        return mealPlan;
    }

    @Override
    public String toString() {
        return "UpdatePantryResult{" +
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

        public UpdateMealPlanResult build() {
            return new UpdateMealPlanResult(mealPlan);
        }
    }
}
