package com.nashss.se.musicplaylistservice.activity.requests;

public class GetPantryRequest {
    private final String userId;
    private final String pantryId;

    private GetPantryRequest(String userId, String pantryId) {
        this.userId = userId;
        this.pantryId = pantryId;
    }

    public String getUserId() {
        return userId;
    }

    public String getPantryId() {
        return pantryId;
    }

    @Override
    public String toString() {
        return "GetPantryRequest{" +
                "userId='" + userId + '\'' +
                "pantryId='" + pantryId + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String userId;
        private String pantryId;

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder withPantryId(String pantryId) {
            this.pantryId = pantryId;
            return this;
        }

        public GetPantryRequest build() {
            return new GetPantryRequest(userId, pantryId);
        }
    }
}
