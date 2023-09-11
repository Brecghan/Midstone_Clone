package com.nashss.se.musicplaylistservice.activity.requests;

public class GetMealPlanListRequest {
    private final String userId;

    private GetMealPlanListRequest(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "GetMealPlanListRequest{" +
                "userId='" + userId + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String userId;

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public GetMealPlanListRequest build() {
            return new GetMealPlanListRequest(userId);
        }
    }
}
