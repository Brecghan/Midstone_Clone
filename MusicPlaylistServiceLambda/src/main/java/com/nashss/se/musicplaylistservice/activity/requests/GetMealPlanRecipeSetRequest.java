package com.nashss.se.musicplaylistservice.activity.requests;

public class GetMealPlanRecipeSetRequest {
    private final String userId;
    private final String mealPlanId;

    private GetMealPlanRecipeSetRequest(String userId, String mealPlanId) {
        this.userId = userId;
        this.mealPlanId = mealPlanId;
    }

    public String getUserId() {
        return userId;
    }

    public String getMealPlanId() {
        return mealPlanId;
    }

    @Override
    public String toString() {
        return "GetMealPlanRecipeSetRequest{" +
                "userId='" + userId + '\'' +
                ", mealPlanId='" + mealPlanId + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String userId;
        private String mealPlanId;

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder withMealPlanId(String mealPlanId) {
            this.mealPlanId = mealPlanId;
            return this;
        }

        public GetMealPlanRecipeSetRequest build() {
            return new GetMealPlanRecipeSetRequest(userId, mealPlanId);
        }
    }
}
