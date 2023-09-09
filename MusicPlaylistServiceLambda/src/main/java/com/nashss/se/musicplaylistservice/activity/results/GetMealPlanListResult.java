package com.nashss.se.musicplaylistservice.activity.results;

import com.nashss.se.musicplaylistservice.models.MealPlanModel;
import com.nashss.se.musicplaylistservice.models.PantryModel;

import java.util.ArrayList;
import java.util.List;

public class GetMealPlanListResult {
    private final List<MealPlanModel> mealPlans;

    private GetMealPlanListResult(List<MealPlanModel> mealPlans) {
        this.mealPlans = mealPlans;
    }

    public List<MealPlanModel> getMealPlans() {
        return mealPlans;
    }

    @Override
    public String toString() {
        return "GetMealPlanListResult{" +
                "mealPlans=" + mealPlans +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<MealPlanModel> mealPlans ;

        public Builder withMealPlans(List<MealPlanModel> mealPlans) {
            this.mealPlans = new ArrayList<>(mealPlans);
            return this;
        }

        public GetMealPlanListResult build() {
            return new GetMealPlanListResult(mealPlans);
        }
    }
}
