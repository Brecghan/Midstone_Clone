package com.nashss.se.musicplaylistservice.activity.requests;

public class GetPantryListRequest {
    private final String userId;

    private GetPantryListRequest(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "GetPantryListRequest{" +
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

        public GetPantryListRequest build() {
            return new GetPantryListRequest(userId);
        }
    }
}
